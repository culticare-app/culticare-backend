package com.culticare.information.controller.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WelfareCenterResponseDto {

    private Long id;
    private String name; // 시설명
    private String facilityType; // 시설종류명
    private String typeDetail; // 시설종류 상세명
    private String manager; // 시설장명
    private String regionName; // 시군구명
    private String address; // 시설주소
    private String telephone; // 전화번호
    private String zipCode; // 우편번호
    private boolean isScrapped; // 스크랩 여부
}
