package tpp.tddproject.hyechan.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpp.tddproject.global.util.Util;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.vo.user.UserVO;

import static tpp.tddproject.global.util.Util.*;

/**
 * fileName    : UserController
 * author      : hyechan
 * date        : 2022/04/09
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 */
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserVO userVO){
        return ResponseEntity.ok()
                .body(getMap(userService.add(userVO)));
    }

    @GetMapping
    public ResponseEntity<?> getList(){
        return ResponseEntity.ok()
                .body(getMap(userService.findAll()));
    }

    @GetMapping("/{userNo}")
    public ResponseEntity<?> get(@PathVariable Long userNo){
        return ResponseEntity.ok()
                .body(getMap(userService.findById(userNo)));
    }

    @PutMapping("/{userNo}")
    public ResponseEntity<Void> update(@PathVariable Long userNo, @RequestBody UserVO userVO) {
        userService.update(userNo, userVO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userNo}")
    public ResponseEntity<Void> delete(@PathVariable Long userNo){
        userService.delete(userNo);
        return ResponseEntity.noContent().build();
    }

}

