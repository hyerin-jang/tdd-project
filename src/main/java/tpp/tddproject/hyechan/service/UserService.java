package tpp.tddproject.hyechan.service;

import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.vo.user.UserVO;

import java.util.List;

public interface UserService {
    Long add(UserVO userVO);
    List<UserDto> findAll();
    UserDto findById(Long userNo);
    void update(Long userNo, UserVO userVO);
    void delete(Long userNo);
}
