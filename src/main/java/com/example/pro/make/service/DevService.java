package com.example.pro.make.service;

import com.example.pro.make.dto.*;
import com.example.pro.make.entity.Developer;
import com.example.pro.make.entity.RetiredDeveloper;
import com.example.pro.make.entity.StatusCode;
import com.example.pro.make.exception.DevException;
import com.example.pro.make.repository.DevRepository;
import com.example.pro.make.repository.RetiredDevRepository;
import com.example.pro.make.type.DeveloperLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.pro.make.exception.DevErrorCode.*;
import static java.util.Arrays.stream;
@Slf4j
@Service
@RequiredArgsConstructor
public class DevService {
    private final DevRepository devRepository;
    private final RetiredDevRepository retiredDevRepository;



    @Transactional
    public CreateDev.Response createdDeveloper(CreateDev.Request request){
            validateCreateDevRequest(request);

            Developer developer = Developer.builder()
                    .developerLevel(request.getDeveloperLevel())
                    .developerSkillType(request.getDeveloperSkillType())
                    .experienceYears(request.getExperienceYears())
                    .memberId(request.getMemberId())
                    .statusCode(StatusCode.EMPLOYED)
                    .name(request.getName())
                    .age(request.getAge())
                    .build();

            devRepository.save(developer);
        return CreateDev.Response.fromEntity(developer);


    }
    private void validateCreateDevRequest(CreateDev.Request request){
      DevValidationDto devValidationDto = null;
//        Integer experienceYears = request.getExperienceYears();
//        DeveloperLevel developerLevel = request.getDeveloperLevel();
//        validateDevLevel(experienceYears, developerLevel);
        validateDevLevel(
                request.getExperienceYears()
                , request.getDeveloperLevel()
        );

        devRepository.findByMemberId(request.getMemberId()).ifPresent((developer -> {
            throw new DevException(DUPLICATED_MEMBER_ID);
        }));



    }



    public List<DevDto> getAllEmployedDevs() {
        return devRepository.findDevelopersStatusCodeEqaulsBy(StatusCode.EMPLOYED)
                .stream().map(DevDto::fromEntity)
                .collect(Collectors.toList());
    }


    public DevDetailDto getDevDetail(String memberId) {
         return devRepository.findByMemberId(memberId)
                 .map(DevDetailDto::fromEntity)
                 .orElseThrow(()->new DevException(NO_DEVELOPER)
                 );
    }

    @Transactional
    public DevDetailDto editDeveloper(String memberId, EditDev.Request request) {
        validateEditDevRequest(request, memberId);
        Developer developer = devRepository.findByMemberId(memberId).orElseThrow(
                ()-> new DevException(NO_DEVELOPER)
        );
        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return DevDetailDto.fromEntity(developer);
    }

    private void validateEditDevRequest(EditDev.Request request
            , String memberId
    ) {
        validateDevLevel(
                request.getExperienceYears()
                , request.getDeveloperLevel()
        );

    }

    private void validateDevLevel(Integer experienceYears, DeveloperLevel developerLevel) {
        if(developerLevel ==DeveloperLevel.SENIOR
                && experienceYears < 10){
//            return new DevValidationDto(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED
//                    ,LEVEL_EXPERIENCE_YEARS_NOT_MATCHED.getMessage());
           throw  new DevException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if(developerLevel ==DeveloperLevel.JUNGNIOR
                && (experienceYears < 4
                || experienceYears >10)){
            throw new DevException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if(developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4){
            throw new DevException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
//        return
    }

    @Transactional
    public DevDetailDto deleteDev(String memberId) {
        //1. EMPLOYED->RETIRED
        Developer developer = devRepository.findByMemberId(memberId)
                .orElseThrow(
                        ()->new DevException(NO_DEVELOPER)
                );
        developer.setStatusCode(StatusCode.RETIRED);
        //2. save into Retired
        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();

        retiredDevRepository.save(retiredDeveloper);
        return DevDetailDto.fromEntity(developer);
    }
}
