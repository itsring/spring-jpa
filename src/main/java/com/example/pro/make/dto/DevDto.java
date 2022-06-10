package com.example.pro.make.dto;


import com.example.pro.make.entity.Developer;
import com.example.pro.make.type.DeveloperLevel;
import com.example.pro.make.type.DeveloperSkillType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DevDto {
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private String memberId;

    public static DevDto fromEntity(Developer developer){
        return DevDto.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .memberId(developer.getMemberId())
                .build();
    }
}
