package com.culticare.information.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED) // 조인테이블 전략
@DiscriminatorColumn(name = "type") // 구분하는 컬럼
@Entity
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "information_id")
    private Long id;
    private String typeName; // 정보 종류명. EDU, RECRUIT, WELFARE
    private Long scrapCount;

    public void plusScrapCount() {
        this.scrapCount += 1;
    }

    public void minusScrapCount() {
        this.scrapCount -= 1;
    }
}
