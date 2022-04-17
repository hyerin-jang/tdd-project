package tdd.tddproject.hyechan.mapper;

import org.mapstruct.Mapper;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.global.mapper.GenericMapperWithParam;
import tdd.tddproject.hyechan.dto.UserDto;
import tdd.tddproject.vo.user.UserParam;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapperWithParam<UserDto, UserParam, User> {
}
