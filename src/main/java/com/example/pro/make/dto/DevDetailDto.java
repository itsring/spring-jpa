package com.example.pro.make.dto;

import com.example.pro.make.entity.Developer;
import com.example.pro.make.type.DeveloperLevel;
import com.example.pro.make.type.DeveloperSkillType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DevDetailDto {

    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;

    private  Integer experienceYears;
    private String memberId;
    private String name;
    private Integer age;
    public static DevDetailDto fromEntity(Developer developer){
        return DevDetailDto.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .experienceYears(developer.getExperienceYears())
                .memberId(developer.getMemberId())
                .name(developer.getName())
                .age(developer.getAge())
                .build();
    }
}
