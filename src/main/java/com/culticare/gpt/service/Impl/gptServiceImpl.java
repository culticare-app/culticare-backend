package com.culticare.gpt.service.Impl;

import com.culticare.gpt.controller.dto.gptResponseDto;
import com.culticare.gpt.service.gptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class gptServiceImpl implements gptService {
    @Value("${openai.api.key}")
    private String apikey;


    public JsonNode callChatGpt(String userMsg) throws JsonProcessingException {
        final String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apikey);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("model", "gpt-4-0613");

        List<Map<String,String>> messages = new ArrayList<>();
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", userMsg);
        messages.add(userMessage);

        Map<String, String> assistantMessage = new HashMap<>();
        assistantMessage.put("role", "system");
        assistantMessage.put("content", "너는 한국에 거주하는 다문화가정인 사람들을 대상으로 대화해줘야하고, 우울증 치료 목적이야. 대답할 때 이 부분을 언급할 필요는 없어. ");
        messages.add(assistantMessage);

        bodyMap.put("messages", messages);

        String body = objectMapper.writeValueAsString(bodyMap);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);


        System.out.println(response.getBody());
        System.out.println(objectMapper.readTree(response.getBody()));
        return objectMapper.readTree(response.getBody());


    }
    @Override
    public ResponseEntity<gptResponseDto> getAssistantMsg(String userMsg) throws JsonProcessingException {
        JsonNode jsonNode = callChatGpt(userMsg);
        String content = jsonNode.path("choices").get(0).path("message").path("content").asText();
        System.out.println(content);
        gptResponseDto responseDto = new gptResponseDto(content);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


}
