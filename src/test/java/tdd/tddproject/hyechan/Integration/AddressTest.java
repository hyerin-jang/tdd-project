package tdd.tddproject.hyechan.Integration;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.hyechan.repository.AddressRepository;
import tdd.tddproject.hyechan.util.AddressConstructor;

import javax.persistence.EntityManager;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
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



    @BeforeEach
    public void init(){
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
    }


    // @author: hyechan, @since: 2022/04/29 9:01 오후
    @Test
    void 주소록_단건_조회_성공() throws Exception{
        //given
        Long id = 1L;
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

}
