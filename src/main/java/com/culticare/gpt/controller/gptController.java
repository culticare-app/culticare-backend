package com.culticare.gpt.controller;


import com.culticare.gpt.controller.dto.gptRequestDto;
import com.culticare.gpt.controller.dto.gptResponseDto;
import com.culticare.gpt.service.gptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
public class gptController {
    private final gptService gptService;

    @Autowired
    public gptController(gptService gptService) {
        this.gptService = gptService;
    }

    @PostMapping("/diary/gpt")
    public ResponseEntity<gptResponseDto> getAssistantMsg(@RequestBody gptRequestDto requestDto) throws JsonProcessingException {
        return gptService.getAssistantMsg(requestDto.getMsg());
    }
}
