package com.culticare.diary.service;


import com.culticare.diary.controller.dto.request.diaryRequestDto;
import com.culticare.diary.controller.dto.response.AnalyzeResponseDto;
import com.culticare.diary.controller.dto.response.diaryResponseDto;
import com.culticare.diary.entity.Diary;
import com.culticare.diary.repository.diaryRepository;
import com.culticare.member.entity.Member;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class diaryService {

    private final diaryRepository diaryRepository;

    private final RestTemplate restTemplate;
    private final String FASTAPI_URL = "http://ec2-52-79-34-197.ap-northeast-2.compute.amazonaws.com:8000/analyze/";

    public diaryService(diaryRepository diaryRepository, RestTemplate restTemplate) {
        this.diaryRepository = diaryRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public diaryResponseDto save(diaryRequestDto requestDto, Member member) {
        Diary diary = requestDto.toEntity(member);
        diary = diaryRepository.save(diary);

        try {
            // FastAPI로 요청 보내기
            String jsonRequest = String.format(
                    "{\"diary_id\": %d, \"user_id\": %d, \"text\": \"%s\"}",
                    diary.getId(),  // 생성된 diaryId 사용
                    member.getId(),
                    requestDto.getContent().replace("\"", "\\\"")
            );

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

            ResponseEntity<AnalyzeResponseDto> response = restTemplate.exchange(
                    FASTAPI_URL,
                    HttpMethod.POST,
                    entity,
                    AnalyzeResponseDto.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                int averageDepressionPercent = response.getBody().getAverageDepressionPercent();
                diary.setDepressionPercent(averageDepressionPercent);
            } else {
                diary.setDepressionPercent(0);
            }

            diaryRepository.save(diary);  // 우울도 값을 업데이트한 후 다시 저장
        } catch (RestClientException e) {
            diary.setDepressionPercent(0);
            diaryRepository.save(diary);  // 예외 발생 시에도 다시 저장
        }

        return new diaryResponseDto(diary);
    }



    @Transactional(readOnly = true)
    public List<diaryResponseDto> viewAll(Member member) {
        List<Diary> diaries = diaryRepository.findByMember(member);
        return diaries.stream().map(b-> new diaryResponseDto(b)).collect(Collectors.toList());
    }



    @Transactional
    public void deleteById(Long diaryId, Member member) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 일기가 없습니다."));
        diaryRepository.deleteById(diaryId);
    }

    @Transactional
    public List<diaryResponseDto> findByDate(int year,int month, int day, Member member) {
        List<Diary> diaries = diaryRepository.findByDate(year,month,day,member);
        return diaries.stream().map(b -> new diaryResponseDto(b)).collect(Collectors.toList());
    }

    @Transactional
    public diaryResponseDto findById(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일기가 없습니다."));
        return new diaryResponseDto(diary);
    }


}
