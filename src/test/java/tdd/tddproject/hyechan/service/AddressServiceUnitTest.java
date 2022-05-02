package tdd.tddproject.hyechan.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.tddproject.hyechan.dto.AddressDto;
import tdd.tddproject.hyechan.repository.AddressRepository;
import tdd.tddproject.hyechan.util.AddressConstructor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class) // springboot x, Mockito 따로 띄움
class AddressServiceUnitTest extends AddressConstructor {

    @InjectMocks // AddressService 객체 만들 때 @Mock 으로 등록된 모든 것을 주입받는다.
    AddressService addressService;

    @Mock
    AddressRepository addressRepository;

    @Test
    void 주소록_단건_조회_성공() throws Exception{
        //given
        given(addressRepository.findById(1L)).willReturn(Optional.ofNullable(createEntity(createParam())));
        //when
        AddressDto dto = addressService.getById(1L);
        //then
        assertEquals(dto.getAddressId(), 1L);
    }

}