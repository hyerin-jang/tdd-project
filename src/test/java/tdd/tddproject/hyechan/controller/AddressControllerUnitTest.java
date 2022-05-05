package tdd.tddproject.hyechan.controller;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.dto.AddressDto;
import tdd.tddproject.hyechan.mapper.AddressMapper;
import tdd.tddproject.hyechan.service.AddressService;
import tdd.tddproject.hyechan.util.AddressConstructor;
import tdd.tddproject.vo.user.AddressParam;

import java.util.ArrayList;
import java.util.Optional;


import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
public class AddressControllerUnitTest extends AddressConstructor {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    AddressMapper mapper = Mappers.getMapper(AddressMapper.class);

    Long id;
    Long failId;

    @BeforeEach
    public void init() {
        id = 1L;
        failId = 1000L;
    }

    // @author: hyechan, @since: 2022/04/30 3:40 오후
    @Test
    void 주소록_단건_조회_성공() throws Exception{
        //given
        Address address = createEntity(createParam());
        given(addressService.getById(id)).willReturn(mapper.toDto(address));
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/address/{id}", id)
                .accept(MediaType.APPLICATION_JSON_VALUE));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.addressId").value(id))
                .andExpect(jsonPath("$.result.addressCity").value(ADDRESS_CITY))
                .andExpect(jsonPath("$.result.addressZip").value(ADDRESS_ZIP))
                .andExpect(jsonPath("$.result.addressPhone").value(ADDRESS_PHONE))
                .andExpect(jsonPath("$.result.addressReceiver").value(ADDRESS_RECEIVER))
                .andExpect(jsonPath("$.result.addressStreet").value(ADDRESS_STREET))
                .andDo(MockMvcResultHandlers.print());

    }

    // @author: hyechan, @since: 2022/05/04 9:15 오후
    @Test
    void 주소록_단건_조회_실패() throws Exception{

        given(addressService.getById(failId))
                .willThrow(new IdNotFoundException("NotFound"+failId));

        mockMvc.perform(MockMvcRequestBuilders.get("/address/{id}", failId)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 주소록_리스트_조회() throws Exception{
        //given
        ArrayList<Address> list = createEntity(createParam(3));
        given(addressService.getList()).willReturn(mapper.toDtoList(list));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/address")
                .accept(MediaType.APPLICATION_JSON_VALUE));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.result", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.result.[0].addressCity").value(ADDRESS_CITY+0))
                .andExpect(jsonPath("$.result.[1].addressReceiver").value(ADDRESS_RECEIVER+1))
                .andExpect(jsonPath("$.result.[2].addressPhone").value(ADDRESS_PHONE.substring(0,ADDRESS_PHONE.length() -1)+2));
    }


    @Test
    void 주소록_추가() throws Exception{
        //given
        AddressParam param = createParam();
        AddressDto dto = mapper.toDto(createEntity(param));
        given(addressService.add(param)).willReturn(dto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(param))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.addressPhone").value(ADDRESS_PHONE))
                .andExpect(jsonPath("$.result.addressCity").value(ADDRESS_CITY))
                .andExpect(jsonPath("$.result.addressStreet").value(ADDRESS_STREET))
                .andExpect(jsonPath("$.result.addressReceiver").value(ADDRESS_RECEIVER))
                .andExpect(jsonPath("$.result.addressZip").value(ADDRESS_ZIP))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 주소록_업데이트() throws Exception{
        //given
        AddressDto dto = mapper.toDto(createEntity(createParam()));
        AddressParam updateParam = updateParam();
        dto.setAddressPhone(updateParam.getAddressPhone());
        dto.setAddressReceiver(updateParam.getAddressReceiver());
        dto.setAddressStreet(updateParam.getAddressStreet());

        willDoNothing().given(addressService).update(updateParam, id);
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.put("/address/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(updateParam)))
                //then
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
