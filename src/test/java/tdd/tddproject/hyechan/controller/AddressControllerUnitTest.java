package tdd.tddproject.hyechan.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.hyechan.mapper.AddressMapper;
import tdd.tddproject.hyechan.service.AddressService;
import tdd.tddproject.hyechan.util.AddressConstructor;

import static org.mockito.BDDMockito.given;
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

    @BeforeEach
    public void init() {
        id = 1L;
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
}
