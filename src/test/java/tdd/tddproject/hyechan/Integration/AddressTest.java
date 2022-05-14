package tdd.tddproject.hyechan.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.WithUser;
import tdd.tddproject.hyechan.repository.AddressRepository;
import tdd.tddproject.hyechan.util.AddressConstructor;
import tdd.tddproject.vo.user.AddressParam;
import tdd.tddproject.vo.user.AddressUpdateParam;

import javax.persistence.EntityManager;

import java.util.ArrayList;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
    @WithAnonymousUser - 익명유저의 인증정보를 설정하기 위한 어노테이션
    @WithUserDetails - UserDetailsService를 통해서 유저정보를 취득하여 설정하기 위한 어노테이션
    @WithMockUser - 별도의 UserDetailsService와 같은 스텁을 제공하지 않아도 간단하게 인증정보를 설정하기 위한 어노테이션
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class AddressTest extends AddressConstructor {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AddressRepository addressRepository;

    Long id = 1L;
    Long failId = 1000L;

    @BeforeEach
    public void init(){
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
    }


    // @author: hyechan, @since: 2022/04/29 9:01 오후
    @Test
    @WithUser
    void 주소록_단건_조회_성공() throws Exception{
        //given
        Address address = createEntity(createParam());
        addressRepository.save(address);
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/address/{id}", id)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(csrf()));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.addressId").value(id))
                .andExpect(jsonPath("$.result.addressCity").value(ADDRESS_CITY))
                .andExpect(jsonPath("$.result.addressReceiver").value(ADDRESS_RECEIVER))
                .andExpect(jsonPath("$.result.addressPhone").value(ADDRESS_PHONE))
                .andExpect(jsonPath("$.result.addressZip").value(ADDRESS_ZIP))
                .andExpect(jsonPath("$.result.addressStreet").value(ADDRESS_STREET))
                .andDo(MockMvcResultHandlers.print())
                // SPRING REST DOCS
                .andDo(document("address/address_get",
                        pathParameters(parameterWithName("id").description("address 번호"))
                ));

    }


    // @author: hyechan, @since: 2022/05/04 9:15 오후
    @Test
    @WithUser
    void 주소록_단건_조회_실패() throws Exception{
        //given
        addressRepository.save(createEntity(createParam()));
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/address/{id}", failId)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(csrf()))
        //then
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUser
    void 주소록_리스트_조회() throws Exception{
        //given
        ArrayList<Address> list = createEntity(createParam(3));
        addressRepository.saveAll(list);
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/address")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(csrf()))
        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.result.[0].addressCity").value(ADDRESS_CITY+0))
                .andExpect(jsonPath("$.result.[1].addressReceiver").value(ADDRESS_RECEIVER+1))
                .andExpect(jsonPath("$.result.[2].addressPhone").value(ADDRESS_PHONE.substring(0,ADDRESS_PHONE.length() -1)+2))
                .andDo(MockMvcResultHandlers.print());
    }


    // @author: hyechan, @since: 2022/05/05 10:41 오전
    @Test
    @WithUser
    void 주소록_추가() throws Exception{
        //given
        AddressParam param = createParam();
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(param))
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_VALUE))
        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.addressPhone").value(ADDRESS_PHONE))
                .andExpect(jsonPath("$.result.addressCity").value(ADDRESS_CITY))
                .andExpect(jsonPath("$.result.addressStreet").value(ADDRESS_STREET))
                .andExpect(jsonPath("$.result.addressReceiver").value(ADDRESS_RECEIVER))
                .andExpect(jsonPath("$.result.addressZip").value(ADDRESS_ZIP))
                .andDo(MockMvcResultHandlers.print())
                .andDo(document("address/address_add",
                        requestFields(
                            fieldWithPath("addressPhone").type(JsonFieldType.STRING).description("폰번호"),
                            fieldWithPath("addressCity").type(JsonFieldType.STRING).description("시군구"),
                            fieldWithPath("addressStreet").type(JsonFieldType.STRING).description("도로명"),
                            fieldWithPath("addressReceiver").type(JsonFieldType.STRING).description("받는사람"),
                            fieldWithPath("addressZip").type(JsonFieldType.STRING).description("우편번호")
                        )
                ));
    }


    // @author: hyechan, @since: 2022/05/05 11:22 오전
    @Test
    @WithUser
    void 주소록_추가_받는사람_실패_beanValidation() throws Exception{
        //given
        AddressParam param = createParam();
        param.setAddressReceiver(null);
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(param))
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_VALUE));
        //then
        resultActions.andExpect(status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());
    }


    // @author: hyechan, @since: 2022/05/05 11:34 오전
    @Test
    @WithUser
    void 주소록_업데이트() throws Exception{
        //given
        addressRepository.save(createEntity(createParam()));
        AddressUpdateParam param = new AddressUpdateParam();
        param.setAddressPhone(UPDATE_ADDRESS_PHONE);
        param.setAddressReceiver(UPDATE_ADDRESS_RECEIVER);
        param.setAddressStreet(UPDATE_ADDRESS_STREET);
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.put("/address/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(param))
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON_VALUE))
        //then
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    // @author: hyechan, @since: 2022/05/12 2:55 오후
    @Test
    @WithUser
    void 주소록_삭제() throws Exception{
        //given
        addressRepository.save(createEntity(createParam()));
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/address/{id}",id)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(csrf()))
        //then
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    // @author: hyechan, @since: 2022/05/12 3:14 오후
    // 이 테스트 코드를 짜면서 Service 에 () -> new IdNotFoundException(ErrorCode.ADDRESS_NOT_EXIST) 생김
    @Test
    @WithUser
    void 주소록_삭제_중복요청_실패_IdNotFoundException() throws Exception{
        //given
        addressRepository.save(createEntity(createParam()));
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/address/{id}",id)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(csrf()))
        //then
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/address/{id}",id)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .with(csrf()))
                //then
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
