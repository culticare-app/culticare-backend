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
        try {
            // FastAPI로 요청 보내기
            String jsonRequest = String.format(
                    "{\"diary_id\": %d, \"user_id\": %d, \"text\": \"%s\"}",
                    0,
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
                int averageDepressionPercent = response.getBody().getAverage_depression_percent();
                Diary diary = requestDto.toEntity(member,averageDepressionPercent);
                diary = diaryRepository.save(diary);
                System.out.println("호출 성공: " + averageDepressionPercent);
                return new diaryResponseDto(diary);
            } else {
                Diary diary = requestDto.toEntity(member,0);
                diary = diaryRepository.save(diary);
                System.out.println("우울도 분석 실패: 0으로 설정");
                return new diaryResponseDto(diary);
            }
        } catch (RestClientException e) {
            Diary diary = requestDto.toEntity(member,0);
            diary = diaryRepository.save(diary);
            diary.setDepressionPercent(0);  // 예외 발생 시 우울도 값을 0으로 설정
            System.out.println("우울도 분석 요청 실패: 예외 발생");
            return new diaryResponseDto(diary);
        }

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
