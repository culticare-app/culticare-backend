package com.culticare.information.service;

import com.culticare.common.exception.CustomException;
import com.culticare.common.exception.ErrorCode;
import com.culticare.information.controller.dto.response.*;
import com.culticare.information.entity.Information;
import com.culticare.information.entity.Education;
import com.culticare.information.entity.Recruitment;
import com.culticare.information.entity.WelfareCenter;
import com.culticare.information.repository.EducationRepository;
import com.culticare.information.repository.InformationRepository;
import com.culticare.information.repository.RecruitmentRepository;
import com.culticare.information.repository.WelfareCenterRepository;
import com.culticare.information.repository.custom.EducationCustomRepositoryImpl;
import com.culticare.information.repository.custom.RecruitmentCustomRepositoryImpl;
import com.culticare.information.repository.custom.WelfareCenterCustomRepositoryImpl;
import com.culticare.member.entity.Member;
import com.culticare.member_scrap_info.entity.MemberScrapInfo;
import com.culticare.member_scrap_info.repository.MemberScrapInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class InformationService {

    private final InformationRepository informationRepository;
    private final EducationCustomRepositoryImpl educationCustomRepositoryImpl;
    private final WelfareCenterCustomRepositoryImpl welfareCenterCustomRepositoryImpl;
    private final RecruitmentCustomRepositoryImpl recruitmentCustomRepositoryImpl;
    private final EducationRepository educationRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final WelfareCenterRepository welfareCenterRepository;

    private final MemberScrapInfoRepository memberScrapInfoRepository;


    public EduListResponseDto findEduList(String typeName, Pageable pageable) {

        List<Education> results = educationCustomRepositoryImpl.findAll(pageable);
        List<EduListResponseDto.EducationDto> eduDtoList = transferToEduListDto(results);

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 클 경우, next =true
        if (eduDtoList.size() > pageable.getPageSize()) {
            hasNext = true;
            eduDtoList.remove(pageable.getPageSize());
        }

        return EduListResponseDto.builder()
                .nextPage(hasNext)
                .educations(eduDtoList)
                .build();
    }

    private List<EduListResponseDto.EducationDto> transferToEduListDto(List<Education> educationList) {

        List<EduListResponseDto.EducationDto> eduDtoList = new ArrayList<>();

        for (Education edu : educationList) {

            EduListResponseDto.EducationDto educationDto = EduListResponseDto.EducationDto.from(edu);
            eduDtoList.add(educationDto);
        }

        return eduDtoList;
    }

    public WelfareCenterListResponseDto findWelfareCenterList(String welfareCenter, Pageable pageable) {

        List<WelfareCenter> results = welfareCenterCustomRepositoryImpl.findAll(pageable);
        List<WelfareCenterListResponseDto.WelfareCenterDto> welfareCenterDtoList = transferToWelfareCenterListDto(results);

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 클 경우, next =true
        if (welfareCenterDtoList.size() > pageable.getPageSize()) {
            hasNext = true;
            welfareCenterDtoList.remove(pageable.getPageSize());
        }

        return WelfareCenterListResponseDto.builder()
                .nextPage(hasNext)
                .welfareCenters(welfareCenterDtoList)
                .build();
    }

    private List<WelfareCenterListResponseDto.WelfareCenterDto> transferToWelfareCenterListDto(List<WelfareCenter> welfareCenterList) {

        List<WelfareCenterListResponseDto.WelfareCenterDto> welfareCenterDtoList = new ArrayList<>();

        for (WelfareCenter wc : welfareCenterList) {

            WelfareCenterListResponseDto.WelfareCenterDto welfareCenterDto = WelfareCenterListResponseDto.WelfareCenterDto.from(wc);
            welfareCenterDtoList.add(welfareCenterDto);
        }

        return welfareCenterDtoList;
    }

    public RecruitmentListResponseDto findRecruitmentList(String recruitment, Pageable pageable) {

        List<Recruitment> results = recruitmentCustomRepositoryImpl.findAll(pageable);
        List<RecruitmentListResponseDto.RecruitmentDto> recruitmentDtoList = transferToRecruitmentListDto(results);

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 클 경우, next =true
        if (recruitmentDtoList.size() > pageable.getPageSize()) {
            hasNext = true;
            recruitmentDtoList.remove(pageable.getPageSize());
        }

        return RecruitmentListResponseDto.builder()
                .nextPage(hasNext)
                .recruitments(recruitmentDtoList)
                .build();
    }

    private List<RecruitmentListResponseDto.RecruitmentDto> transferToRecruitmentListDto(List<Recruitment> recruitmentList) {

        List<RecruitmentListResponseDto.RecruitmentDto> recruitmentDtoList = new ArrayList<>();

        for (Recruitment r : recruitmentList) {

            RecruitmentListResponseDto.RecruitmentDto recruitmentDto = RecruitmentListResponseDto.RecruitmentDto.from(r);
            recruitmentDtoList.add(recruitmentDto);
        }

        return recruitmentDtoList;
    }

    public EducationResponseDto findEducation(Member member, Long informationId) {

        Education findEducation = educationRepository.findById(informationId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INFORMATION));

        boolean isScrapped = false;

        if (memberScrapInfoRepository.existsByMemberAndInformation(member, findEducation)) {
            isScrapped = true;
        }

        return EducationResponseDto.builder()
                .id(findEducation.getId())
                .title(findEducation.getTitle())
                .content(findEducation.getContent())
                .language(findEducation.getLanguage())
                .applyStartDate(findEducation.getApplyStartDate())
                .applyEndDate(findEducation.getApplyEndDate())
                .eduStartDate(findEducation.getEduStartDate())
                .eduEndDate(findEducation.getEduEndDate())
                .qualification(findEducation.getQualification())
                .personnel(findEducation.getPersonnel())
                .isScrapped(isScrapped)
                .build();
    }

    public RecruitmentResponseDto findRecruitment(Member member, Long informationId) {

        Recruitment findRecruitment = recruitmentRepository.findById(informationId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INFORMATION));

        boolean isScrapped = false;

        if (memberScrapInfoRepository.existsByMemberAndInformation(member, findRecruitment)) {
            isScrapped = true;
        }

        return RecruitmentResponseDto.builder()
                .id(findRecruitment.getId())
                .title(findRecruitment.getTitle())
                .facility(findRecruitment.getFacility())
                .language(findRecruitment.getLanguage())
                .countryOfOrigin(findRecruitment.getCountryOfOrigin())
                .workingArea(findRecruitment.getWorkingArea())
                .workingDays(findRecruitment.getWorkingDays())
                .startTime(findRecruitment.getStartTime())
                .endTime(findRecruitment.getEndTime())
                .weekendWorkStatus(findRecruitment.getWeekendWorkStatus())
                .wage(findRecruitment.getWage())
                .recruitNumber(findRecruitment.getRecruitNumber())
                .postDate(findRecruitment.getPostDate())
                .workType(findRecruitment.getWorkType())
                .workWelfare(findRecruitment.getWorkWelfare())
                .isScrapped(isScrapped)
                .build();
    }

    public WelfareCenterResponseDto findWelfareCenter(Member member, Long informationId) {

        WelfareCenter findWelfareCenter = welfareCenterRepository.findById(informationId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INFORMATION));

        boolean isScrapped = false;

        if (memberScrapInfoRepository.existsByMemberAndInformation(member, findWelfareCenter)) {
            isScrapped = true;
        }

        return WelfareCenterResponseDto.builder()
                .id(findWelfareCenter.getId())
                .name(findWelfareCenter.getName())
                .facilityType(findWelfareCenter.getFacilityType())
                .typeDetail(findWelfareCenter.getTypeDetail())
                .manager(findWelfareCenter.getManager())
                .regionName(findWelfareCenter.getRegionName())
                .address(findWelfareCenter.getAddress())
                .telephone(findWelfareCenter.getTelephone())
                .zipCode(findWelfareCenter.getZipCode())
                .isScrapped(isScrapped)
                .build();
    }

    //================= 회원의 정보 스크랩 ==================

    @Transactional
    public void saveScrapInfo(Member member, Long informationId) {

        Information findInformation = informationRepository.findById(informationId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INFORMATION));

        // 이미 스크랩한 정보일 경우
        if (memberScrapInfoRepository.existsByMemberAndInformation(member, findInformation)) {
            throw new CustomException(ErrorCode.EXIST_MEMBER_SCRAP_INFO);
        }

        findInformation.plusScrapCount();

        MemberScrapInfo memberScrapInfo = MemberScrapInfo.builder()
                .member(member)
                .typeName(findInformation.getTypeName())
                .information(findInformation)
                .build();

        memberScrapInfoRepository.save(memberScrapInfo);
    }

    @Transactional
    public void deleteScrapInfo(Member member, Long informationId) {

        Information findInformation = informationRepository.findById(informationId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_INFORMATION));

        // 스크랩한 정보가 아닐 경우
        if (!memberScrapInfoRepository.existsByMemberAndInformation(member, findInformation)) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER_SCRAP_INFO);
        }

        findInformation.minusScrapCount();

        memberScrapInfoRepository.deleteByMemberAndInformation(member, findInformation);
    }

    public EduListResponseDto findScrappedEduList(Member member, Pageable pageable) {

        List<Education> results = educationCustomRepositoryImpl.findScrappedEducation(member, pageable);
        List<EduListResponseDto.EducationDto> eduDtoList = transferToEduListDto(results);

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 클 경우, next =true
        if (eduDtoList.size() > pageable.getPageSize()) {
            hasNext = true;
            eduDtoList.remove(pageable.getPageSize());
        }

        return EduListResponseDto.builder()
                .nextPage(hasNext)
                .educations(eduDtoList)
                .build();
    }

    public RecruitmentListResponseDto findScrappedRecruitmentList(Member member, Pageable pageable) {

        List<Recruitment> results = recruitmentCustomRepositoryImpl.findScrappedRecruitment(member, pageable);
        List<RecruitmentListResponseDto.RecruitmentDto> recruitmentDtoList = transferToRecruitmentListDto(results);

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 클 경우, next =true
        if (recruitmentDtoList.size() > pageable.getPageSize()) {
            hasNext = true;
            recruitmentDtoList.remove(pageable.getPageSize());
        }

        return RecruitmentListResponseDto.builder()
                .nextPage(hasNext)
                .recruitments(recruitmentDtoList)
                .build();
    }

    public WelfareCenterListResponseDto findScrappedWelfareCenterList(Member member, Pageable pageable) {

        List<WelfareCenter> results = welfareCenterCustomRepositoryImpl.findScrappedWelfareCenter(member, pageable);
        List<WelfareCenterListResponseDto.WelfareCenterDto> welfareCenterDtoList = transferToWelfareCenterListDto(results);

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 클 경우, next =true
        if (welfareCenterDtoList.size() > pageable.getPageSize()) {
            hasNext = true;
            welfareCenterDtoList.remove(pageable.getPageSize());
        }

        return WelfareCenterListResponseDto.builder()
                .nextPage(hasNext)
                .welfareCenters(welfareCenterDtoList)
                .build();
    }
}
