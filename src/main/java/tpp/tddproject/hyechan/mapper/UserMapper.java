package tpp.tddproject.hyechan.mapper;

import org.mapstruct.Mapper;
import tpp.tddproject.domain.entity.item.Item;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.global.mapper.GenericMapper;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.rin.dto.ItemDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, User>  {
}
