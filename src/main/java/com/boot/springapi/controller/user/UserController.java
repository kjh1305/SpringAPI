package com.boot.springapi.controller.user;

import com.boot.springapi.domain.user.DeleteUser;
import com.boot.springapi.domain.user.User;
import com.boot.springapi.pagination.Pagination;
import com.boot.springapi.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public Object users(@RequestParam(defaultValue = "1") int page){
        int userListCnt = userService.pageCnt();
        Pagination pagination = new Pagination(userListCnt, page);
        log.info("pagination = {}", pagination);
//        List<User> userList = userService.findAll();
        List<User> userList = userService.findAllPaging(pagination);
        Object object = new Object[]{userList, pagination};

        return object;
    }

    //등록
    @PostMapping
    public Object postUser(@RequestBody @Validated User user, BindingResult bindingResult){

        log.info("user : {} ", user);

        if(bindingResult.hasErrors()){
            log.info("오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("등록 로직 실행!!");
        user.setDet(LocalDateTime.now());
        userService.save(user);

        return user;
    }

    //회원 ID 조회
    @GetMapping("/{userId}")
    public Object getUserId(@PathVariable long userId){
        return userService.findById(userId);
    }

    //회원 Name 조회
//    @GetMapping("/{userName}")
//    public Object getUserName(@PathVariable String userName){
//        return userService.findByName(userName);
//    }

    //회원 수정
    @PatchMapping("/{userId}")
    public Object updateUser(@RequestBody @Validated User user, BindingResult bindingResult, @PathVariable long userId){

        if(bindingResult.hasErrors()){
            log.info("오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("수정 로직 실행!!");
        user.setId(userId);
        user.setDet(LocalDateTime.now());
        log.info("user : {} ", user);
        userService.update(user);

        return user;
    }

    //회원 삭제
    @DeleteMapping("/{userId}")
    public Object deleteUser(@PathVariable long userId){
        log.info("id={}", userId);
        userService.delete(userId);
        DeleteUser user = new DeleteUser(userId, LocalDateTime.now());
        return user;
    }


}
