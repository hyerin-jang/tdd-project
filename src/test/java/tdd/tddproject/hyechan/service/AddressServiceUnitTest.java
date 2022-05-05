package tdd.tddproject.hyechan.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.dto.AddressDto;
import tdd.tddproject.hyechan.repository.AddressRepository;
import tdd.tddproject.hyechan.util.AddressConstructor;
import tdd.tddproject.vo.user.AddressParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // springboot x, Mockito 따로 띄움
class AddressServiceUnitTest extends AddressConstructor {

    @InjectMocks // AddressService 객체 만들 때 @Mock 으로 등록된 모든 것을 주입받는다.
    AddressService addressService;

    @Mock
    AddressRepository addressRepository;

    Long id;
    Long failId;

    @BeforeEach
    public void init(){
        id = 1L;
        failId = 1000L;
    }

    @Test
    void 주소록_단건_조회_성공() throws Exception{
        //given
        given(addressRepository.findById(id)).willReturn(Optional.ofNullable(createEntity(createParam())));
        //when
        AddressDto dto = addressService.getById(id);
        //then
        assertThat(dto.getAddressId()).isEqualTo(id);
    }

    // @author: hyechan, @since: 2022/05/04 9:15 오후
    @Test
    void 주소록_단건_조회_실패() throws Exception{
        //given
        given(addressRepository.findById(failId))
                .willThrow(new IdNotFoundException());

        Assertions.assertThatThrownBy(()->
        //when
                addressService.getById(failId))
        //then
                .isInstanceOf(IdNotFoundException.class);
    }


    // @author: hyechan, @since: 2022/05/04 10:50 오후
    @Test
    void 주소록_리스트_조회() throws Exception{
        //given
        ArrayList<Address> list = createEntity(createParam(3));
        given(addressRepository.findAll()).willReturn(list);
        //when
        List<AddressDto> dtoList = addressService.getList();
        //then
        assertThat(list.get(0).getAddressCity()).isEqualTo(dtoList.get(0).getAddressCity());
        assertThat(dtoList).hasSize(list.size());
        // --
        assertThat(dtoList)
                .extracting(AddressDto::getAddressCity)
                .containsExactly(ADDRESS_CITY+0, ADDRESS_CITY+1, ADDRESS_CITY+2);
//                .containsExactly(list.get(0).getAddressCity(), list.get(1).getAddressCity(), list.get(2).getAddressCity());
        assertThat(dtoList)
                .extracting(AddressDto::getAddressPhone,AddressDto::getAddressZip)
                .contains(tuple("010-0000-0000","000000"))
                .contains(tuple("010-0000-0001","000001"))
                .contains(tuple("010-0000-0002","000002"));
    }


    // @author: hyechan, @since: 2022/05/05 10:46 오전
    @Test
    void 리스트_추가() throws Exception{
        //given
        AddressParam param = createParam();
        Address entity = createEntity(param);
        given(addressRepository.save(any())).willReturn(entity);
        //when
        AddressDto dto = addressService.add(param);
        //then
        assertThat(dto.getAddressCity()).isEqualTo(ADDRESS_CITY);
        assertThat(dto.getAddressPhone()).isEqualTo(ADDRESS_PHONE);
        assertThat(dto.getAddressZip()).isEqualTo(ADDRESS_ZIP);
        assertThat(dto.getAddressStreet()).isEqualTo(ADDRESS_STREET);
        assertThat(dto.getAddressReceiver()).isEqualTo(ADDRESS_RECEIVER);
    }


    // @author: hyechan, @since: 2022/05/05 11:51 오전
    @Test
    void 주소록_업데이트() throws Exception{
        //given
        Address entity = createEntity(createParam());
        given(addressRepository.findById(id)).willReturn(Optional.ofNullable(entity));
        //when
        addressService.update(updateParam(), id);
        //then
        verify(addressRepository, times(1)).findById(id);
    }
}