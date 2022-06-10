package com.example.pro.make.service;

import com.example.pro.make.dto.CreateDev;
import com.example.pro.make.dto.DevDetailDto;
import com.example.pro.make.dto.DevDto;
import com.example.pro.make.entity.Developer;
import com.example.pro.make.entity.StatusCode;
import com.example.pro.make.repository.DevRepository;
import com.example.pro.make.repository.RetiredDevRepository;
import com.example.pro.make.type.DeveloperLevel;
import com.example.pro.make.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.example.pro.make.entity.StatusCode.EMPLOYED;
import static com.example.pro.make.type.DeveloperLevel.SENIOR;
import static com.example.pro.make.type.DeveloperSkillType.FRONT_END;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DevServiceTest {

    @Mock
    private DevRepository devRepository;
    @Mock
    private RetiredDevRepository retiredDevRepository;

    @InjectMocks
    private DevService devService;

    @Test
    public void testS(){
        given(devRepository.findByMemberId(anyString())).willReturn(Optional.of(Developer
                .builder()
                .developerLevel(SENIOR)
                .developerSkillType(FRONT_END)
                .experienceYears(12)
                .statusCode(EMPLOYED)
                .name("name")
                .age(12)
                .build()
        ));
        DevDetailDto devDetailDto = devService.getDevDetail("memberId");
        assertEquals(SENIOR, devDetailDto.getDeveloperLevel());
        assertEquals(SENIOR, devDetailDto.getDeveloperLevel());
        assertEquals(SENIOR, devDetailDto.getDeveloperLevel());
    }
}