package com.culticare.diary.controller;

import com.culticare.diary.controller.dto.request.diaryRequestDto;
import com.culticare.diary.controller.dto.response.diaryResponseDto;
import com.culticare.diary.entity.Diary;
import com.culticare.diary.service.diaryService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class diaryController {


    @Autowired
    private final diaryService diaryService;

    // 일기 작성
    @PostMapping("/diary/write")
    public ResponseEntity<diaryResponseDto> save(@RequestBody diaryRequestDto requestDto, @RequestHeader("memberId") Long loginMemberId) {
        diaryResponseDto responseDto = diaryService.save(requestDto, loginMemberId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 일기 삭제
    @DeleteMapping("/diary/delete/{diaryId}")
    public ResponseEntity<String> delete(@RequestParam Long diaryId, @RequestHeader("memberId") Long memberId) {
        diaryService.deleteById(diaryId,memberId);
        return ResponseEntity.ok("일기가 성공적으로 삭제되었습니다.");
    }

    // 일기 전체 조회, 페이징
    @GetMapping("/diary/view")
    public ResponseEntity<List<diaryResponseDto>> viewAll(@RequestHeader("memberId") Long memberId){
        List<diaryResponseDto> diaries = diaryService.viewAll(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(diaries);
    }


    // 날짜별 일기 조회
    @GetMapping("/diary/date")
    public ResponseEntity<List<diaryResponseDto>> findByDate(@RequestHeader("memberId") Long memberId, @RequestParam int year, @RequestParam int month, @RequestParam int day){
        List<diaryResponseDto> responseDto = diaryService.findByDate(year,month,day,memberId);
        return ResponseEntity.ok(responseDto);
    }


}
