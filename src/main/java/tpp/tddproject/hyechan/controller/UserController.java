package tpp.tddproject.hyechan.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @ApiOperation("회원가입 요청")
    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> add(@RequestBody UserVO userVO){
        return ResponseEntity.ok(getStringObjectMap(userService.add(userVO)));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getList(){
        return ResponseEntity.ok(getStringObjectMap(userService.findAll()));
    }

    @GetMapping("/{userNo}")
    public ResponseEntity<Map<String, Object>> get(@PathVariable Long userNo){
        return ResponseEntity.ok(getStringObjectMap(userService.findById(userNo)));
    }

    @PutMapping("/{userNo}")
    public ResponseEntity<Void> update(@PathVariable Long userNo, UserVO userVO) {
        userService.update(userNo, userVO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userNo}")
    public ResponseEntity<Void> delete(@PathVariable Long userNo){
        userService.delete(userNo);
        return ResponseEntity.noContent().build();
    }


    private Map<String, Object> getStringObjectMap(Object obj) {
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("result", obj);
        return responseMap;
    }

}
