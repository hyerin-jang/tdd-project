package tpp.tddproject.hyechan.mapper;

import org.mapstruct.Mapper;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.global.mapper.GenericMapper;
import tpp.tddproject.vo.user.UserParam;

@Mapper(componentModel = "spring")
public interface UserParamMapper extends GenericMapper<UserParam, User>  {
}
