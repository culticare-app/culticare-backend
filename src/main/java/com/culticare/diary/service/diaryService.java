package com.culticare.diary.service;


import com.culticare.diary.controller.dto.request.diaryRequestDto;
import com.culticare.diary.controller.dto.response.diaryResponseDto;
import com.culticare.diary.entity.Diary;
import com.culticare.diary.repository.diaryRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class diaryService {

    private final diaryRepository diaryRepository;

    @Transactional
    public diaryResponseDto save(diaryRequestDto requestDto, Long memberId){
        // 내용이 비어있는 경우 예외 처리
        if (StringUtils.isBlank(requestDto.getContent())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "일기 내용은 필수입니다. ");
        }
        Diary savedDiary = diaryRepository.save(requestDto.toEntity(memberId));
        return new diaryResponseDto(savedDiary);
    }


    @Transactional(readOnly = true)
    public List<diaryResponseDto> viewAll(Long memberId) {
        List<Diary> diaries = diaryRepository.findByMemberId(memberId);
        return diaries.stream().map(b-> new diaryResponseDto(b)).collect(Collectors.toList());
    }



    @Transactional
    public void deleteById(Long diaryId, Long userId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 일기가 없습니다."));
        diaryRepository.deleteById(diaryId);
    }

    @Transactional
    public List<diaryResponseDto> findByDate(int year,int month, int day, Long memberId) {
        List<Diary> diaries = diaryRepository.findByDate(year,month,day,memberId);
        return diaries.stream().map(b -> new diaryResponseDto(b)).collect(Collectors.toList());
    }


}
