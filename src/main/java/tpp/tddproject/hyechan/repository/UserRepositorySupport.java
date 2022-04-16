package tpp.tddproject.hyechan.repository;

import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.vo.user.UserParam;

public interface UserRepositorySupport{
    void updateUser(Long userNo, UserParam userParam);
}
