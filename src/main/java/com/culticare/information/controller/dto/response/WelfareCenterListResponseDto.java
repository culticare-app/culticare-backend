package com.culticare.information.controller.dto.response;

import com.culticare.information.entity.WelfareCenter;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WelfareCenterListResponseDto {

    private boolean nextPage;
    private List<WelfareCenterDto> welfareCenters;

    @Data
    @Builder
    public static class WelfareCenterDto {

        private Long id;
        private String name; // 시설명
        private String facilityType; // 시설종류명
        private String typeDetail; // 시설종류 상세명
        private String manager; // 시설장명
        private String regionName; // 시군구명
        private String address; // 시설주소
        private String telephone; // 전화번호
        private String zipCode; // 우편번호

        public static WelfareCenterDto from(WelfareCenter welfareCenter) {
            return WelfareCenterDto.builder()
                    .id(welfareCenter.getId())
                    .name(welfareCenter.getName())
                    .facilityType(welfareCenter.getFacilityType())
                    .typeDetail(welfareCenter.getTypeDetail())
                    .manager(welfareCenter.getManager())
                    .regionName(welfareCenter.getRegionName())
                    .address(welfareCenter.getAddress())
                    .telephone(welfareCenter.getTelephone())
                    .zipCode(welfareCenter.getZipCode())
                    .build();
        }
    }
}
