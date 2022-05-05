package tdd.tddproject.hyechan.Integration;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.hyechan.repository.AddressRepository;
import tdd.tddproject.hyechan.util.AddressConstructor;
import tdd.tddproject.vo.user.AddressParam;

import javax.persistence.EntityManager;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    Long id;
    Long failId;

    @BeforeEach
    public void init(){
        id = 1L;
        failId = 1000L;
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
    }


    // @author: hyechan, @since: 2022/04/29 9:01 오후
    @Test
    void 주소록_단건_조회_성공() throws Exception{
        //given
        Address address = createEntity(createParam());
        addressRepository.save(address);
        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/address/{id}", id)
                .accept(MediaType.APPLICATION_JSON_VALUE));
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
    void 주소록_단건_조회_실패() throws Exception{
        //given
        addressRepository.save(createEntity(createParam()));
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/address/{id}", failId)
                .accept(MediaType.APPLICATION_JSON_VALUE))
        //then
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 주소록_리스트_조회() throws Exception{
        //given
        ArrayList<Address> list = createEntity(createParam(3));
        addressRepository.saveAll(list);
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/address")
                .accept(MediaType.APPLICATION_JSON_VALUE))
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
    void 주소록_추가() throws Exception{
        //given
        AddressParam param = createParam();
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.post("/address")
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
}
