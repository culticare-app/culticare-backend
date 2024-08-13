package com.culticare.gpt.service;

import com.culticare.gpt.controller.dto.gptResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;

public interface gptService {
    ResponseEntity<gptResponseDto> getAssistantMsg(String userMsg) throws JsonProcessingException;

}
