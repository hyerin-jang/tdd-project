package tdd.tddproject.hyechan.Integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.hyechan.service.AddressService;
import tdd.tddproject.hyechan.util.AddressConstructor;

import javax.persistence.EntityManager;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
public class AddressUnitTest extends AddressConstructor {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    @MockBean
    private AddressService addressService;

//    @Autowired
//    private AddressService addressService;

    @BeforeEach
    public void init(){
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
    }


    // @author: hyechan, @since: 2022/04/29 9:01 오후
    @Test
    void 주소록_단건_조회_성공() throws Exception{
        //given

        //when
        ResultActions resultActions = mockMvc.perform(RestDocumentationRequestBuilders.get("/address/1")
                .accept(MediaType.APPLICATION_JSON_VALUE));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.addressZip").value("000000"))
                .andExpect(jsonPath("$.result.addressCity").value("경상북도"))
                .andExpect(jsonPath("$.result.addressStreet").value("진평동 에이파크 501호"))
                .andExpect(jsonPath("$.result.addressReceiver").value("test"))
                .andExpect(jsonPath("$.result.addressPhone").value("000-0000-0000"))
                .andDo(MockMvcResultHandlers.print());


    }

}
