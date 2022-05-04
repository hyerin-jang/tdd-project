package tdd.tddproject.hyechan.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.dto.AddressDto;
import tdd.tddproject.hyechan.dto.UserDto;
import tdd.tddproject.hyechan.mapper.AddressMapper;
import tdd.tddproject.hyechan.mapper.UserMapper;
import tdd.tddproject.hyechan.repository.AddressRepository;

import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper mapper = Mappers.getMapper(AddressMapper.class);


    public AddressDto getById(Long id){

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("해당 주소가 존재하지 않습니다."));
        return mapper.toDto(address);
    }

    public List<AddressDto> getList() {
        List<Address> list = addressRepository.findAll();
        return mapper.toDtoList(list);
    }
}
