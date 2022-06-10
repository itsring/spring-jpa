package com.example.pro.make.controller;

import com.example.pro.make.dto.DevDto;
import com.example.pro.make.service.DevService;
import com.example.pro.make.type.DeveloperLevel;
import com.example.pro.make.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(makeController.class)
class makeControllerTest {
        @Autowired
        private MockMvc mockMvc;


        @MockBean
        private DevService devService;

        @Test
        void getAllDevelop() throws Exception{
                DevDto juniorDevDto = DevDto.builder()
                        .developerSkillType(DeveloperSkillType.BACK_END)
                        .developerLevel(DeveloperLevel.JUNIOR)
                        .memberId("memberId1")
                        .build();

                DevDto seniorDevDto = DevDto.builder()
                        .developerSkillType(DeveloperSkillType.BACK_END)
                        .developerLevel(DeveloperLevel.SENIOR)
                        .memberId("memberId2")
                        .build();
                given(DevService.getAllEmployedDevs()).willReturn(Arrays.asList(juniorDevDto, seniorDevDto));



        }
}