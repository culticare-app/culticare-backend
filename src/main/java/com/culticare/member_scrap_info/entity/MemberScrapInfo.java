package com.culticare.member_scrap_info.entity;


import com.culticare.BaseTimeEntity;
import com.culticare.information.entity.Information;
import com.culticare.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="member_scrap_info", uniqueConstraints = {@UniqueConstraint(
        name = "member_info_unique",
        columnNames = {"member_id", "information_id"} )})
public class MemberScrapInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_scrap_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    private String typeName; // 정보 종류명. EDU, RECRUIT, WELFARE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Information information;
}
