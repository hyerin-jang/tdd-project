package tdd.tddproject.hyechan.mapper;

import org.mapstruct.Mapper;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.mapper.GenericMapperWithParam;
import tdd.tddproject.hyechan.dto.AddressDto;
import tdd.tddproject.vo.user.AddressParam;
import tdd.tddproject.vo.user.AddressUpdateParam;

@Mapper(componentModel = "spring")
public interface AddressUpdateMapper extends GenericMapperWithParam<AddressDto, AddressUpdateParam, Address> {
}
