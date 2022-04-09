package tpp.tddproject.hyechan.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * fileName    : UserController
 * author      : hyechan
 * date        : 2022/04/09
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/09 3:38 오후  hyechan        최초 생성
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @ApiOperation("회원가입 요청")
    @PostMapping("/addUser")
    public void addUser(UserVO userVO){
        userService.add(userVO);
    }

}
