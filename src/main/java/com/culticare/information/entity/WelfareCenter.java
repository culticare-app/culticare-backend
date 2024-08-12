package com.culticare.information.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("welfare_center")
@Entity
public class WelfareCenter extends Information{

    private String name; // 시설명
    private String facilityType; // 시설종류명
    private String typeDetail; // 시설종류 상세명
    private String manager; // 시설장명
    private String regionName; // 시군구명
    private String address; // 시설주소
    private String telephone; // 전화번호
    private String zipCode; // 우편번호
}
