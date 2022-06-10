package com.example.pro.make.controller;

import com.example.pro.make.dto.*;
import com.example.pro.make.exception.DevErrorCode;
import com.example.pro.make.exception.DevException;
import com.example.pro.make.service.DevService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class makeController {
    private final DevService devService;

    @GetMapping("/devs")
    public List<DevDto> getAllDev() {
        log.info("GET / dev HTTP/1.1");
        return devService.getAllEmployedDevs();
    }
    @GetMapping("/dev/{memberId}")
    public DevDetailDto getDevDetail(@PathVariable String memberId) {
        log.info("GET / dev HTTP/1.1");
        return devService.getDevDetail(memberId);
    }

    @PostMapping("/create-devs")
    public CreateDev.Response CreateDevs(@Valid @RequestBody CreateDev.Request request) {
        log.info("request : {}", request);




        return devService.createdDeveloper(request);
    }

    @PutMapping("/dev/{memberId}")
    public DevDetailDto editDevs(@PathVariable String memberId, @Valid @RequestBody EditDev.Request request) {
        log.info("request : {}", request);

        return devService.editDeveloper(memberId, request);
    }
    @DeleteMapping("dev/{memberId}")
    public DevDetailDto deleteDev(
        @PathVariable String memberId
    ){
        return devService.deleteDev(memberId);
    }



}

