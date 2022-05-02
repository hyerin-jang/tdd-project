package tdd.tddproject.hyechan.mapper;

import org.mapstruct.Mapper;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.mapper.GenericMapperWithParam;
import tdd.tddproject.hyechan.dto.AddressDto;
import tdd.tddproject.vo.user.AddressParam;

@Mapper(componentModel = "spring")
public interface AddressMapper extends GenericMapperWithParam<AddressDto, AddressParam, Address> {
}
