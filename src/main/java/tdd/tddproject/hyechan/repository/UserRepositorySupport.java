package tdd.tddproject.hyechan.repository;

import tdd.tddproject.vo.user.UserParam;

public interface UserRepositorySupport{
    void updateUser(Long userNo, UserParam userParam);
}
