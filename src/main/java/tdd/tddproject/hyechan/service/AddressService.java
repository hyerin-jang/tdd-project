package tdd.tddproject.hyechan.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.exception.ErrorCode;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.dto.AddressDto;
import tdd.tddproject.hyechan.dto.UserDto;
import tdd.tddproject.hyechan.mapper.AddressMapper;
import tdd.tddproject.hyechan.mapper.UserMapper;
import tdd.tddproject.hyechan.repository.AddressRepository;
import tdd.tddproject.vo.user.AddressParam;
import tdd.tddproject.vo.user.AddressUpdateParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper mapper = Mappers.getMapper(AddressMapper.class);

    @PersistenceContext
    EntityManager em;

    public AddressDto getById(Long id){

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ErrorCode.ADDRESS_NOT_EXIST));

        return mapper.toDto(address);
    }

    public List<AddressDto> getList() {
        List<Address> list = addressRepository.findAll();
        return mapper.toDtoList(list);
    }

    public AddressDto add(AddressParam addressParam) {
        Address address = mapper.toEntity(addressParam);
        return mapper.toDto(addressRepository.save(address));
    }

    public void update(AddressUpdateParam updateParam, Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ErrorCode.ADDRESS_NOT_EXIST));
        address.update(updateParam);
    }

    public void delete(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ErrorCode.ADDRESS_NOT_EXIST));
        addressRepository.delete(address);
    }
}
