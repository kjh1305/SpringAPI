package com.boot.springapi.controller.user;

import com.boot.springapi.domain.user.DeleteUser;
import com.boot.springapi.domain.user.InsertUser;
import com.boot.springapi.domain.user.User;
import com.boot.springapi.domain.user.UserList;
import com.boot.springapi.pagination.Pagination;
import com.boot.springapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements ErrorController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;

//    @Autowired
//    public UserController(UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }

    //회원가입
//    @PostMapping("/join")
//    public Long join(@RequestBody Map<String, String> user) {
//        return userRepository.save(User.builder()
//                .email(user.get("email"))
//                .password(passwordEncoder.encode(user.get("password")))
//                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
//                .build()).getId();
//    }


    //목록 조회
    @GetMapping
    public Object users(@RequestParam(defaultValue = "1") int page){
        int userListCnt = userService.pageCnt();
        Pagination pagination = new Pagination(userListCnt, page);
        log.info("pagination = {}", pagination);
        List<UserList> userList = userService.findAllPaging(pagination);
//        Object object = new Object[]{userList, pagination};

        return userList;
    }

    @GetMapping("/page")
    public Object usersPage(@RequestParam(defaultValue = "1") int page){
        int userListCnt = userService.pageCnt();
        Pagination pagination = new Pagination(userListCnt, page);
        log.info("pagination = {}", pagination);
        return pagination;
    }

    //등록
    @PostMapping
    public Object postUser(@RequestBody @Validated InsertUser insertUser, BindingResult bindingResult){

        log.info("user : {} ", insertUser);

        if(bindingResult.hasErrors()){
            log.info("오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("등록 로직 실행!!");
        User user = new User();
        user.setEmail(insertUser.getEmail());
        user.setPassword(insertUser.getPassword());
        user.setName(insertUser.getName());
//        user.setDet(LocalDateTime.now());
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
//        user.setDet(LocalDateTime.now());
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
