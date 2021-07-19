package com.boot.springapi.controller.user;

import com.boot.springapi.domain.user.User;
import com.boot.springapi.exhandler.ErrorResult;
import com.boot.springapi.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController implements ErrorController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //목록 조회
    @GetMapping
    public Object users(){
        List<User> userList = userService.findAll();
        return userList;
    }

    //등록
    @PostMapping
    public Object postUser(@RequestBody @Validated User user, BindingResult bindingResult){

        log.info("user : {} ", user);

        if(bindingResult.hasErrors()){
            log.info("오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 로직 실행!!");
        user.setDet(LocalDateTime.now());
        userService.save(user);

        return user;
    }

    //회원 조회
    @GetMapping("/{userId}")
    public String getUser(){
        return "getUser";
    }

    //회원 수정
    @PatchMapping("/{userId}")
    public String updateUser(){
        return "update";
    }

    //회원 삭제
    @DeleteMapping("/{userId}")
    public String deleteUser(){
        return "delete";
    }


}
