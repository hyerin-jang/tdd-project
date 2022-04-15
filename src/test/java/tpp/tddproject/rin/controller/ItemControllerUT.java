package tpp.tddproject.rin.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tpp.tddproject.rin.dto.ItemDto;
import tpp.tddproject.rin.service.ItemService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ItemController.class)
//@Controller, @ControllerAdvice 사용 가능
//@Service, @Repository 등은 사용 불가
//@MockBean 으로 가짜 객체 등록해서 사용해야 함
//JPA 기능은 동작 x
public class ItemControllerUT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    List<ItemDto> itemDtoList;

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        itemDtoList = Arrays.asList(ItemDto.builder()
                .itemNo(1L)
                .itemComp("nike")
                .itemName("hoodie")
                .itemPrice(10000)
                .itemStock(24).build());

        System.out.println("@BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @Test
    public void findAllItemTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/item"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    public void findItemByIdTest() throws Exception {
        //given
        List<ItemDto> itemDtoList = this.itemDtoList;
        Long itemNo = itemDtoList.get(0).getItemNo();

        given(itemService.findItemByItemNo(any())).willReturn(itemDtoList);

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/item/" + itemNo))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..itemNo").value(1))
                .andExpect(jsonPath("$..itemComp").value("nike"))
                .andExpect(jsonPath("$..itemName").value("hoodie"))
                .andExpect(jsonPath("$..itemPrice").value(10000))
                .andExpect(jsonPath("$..itemStock").value(24))
                .andDo(print());
    }

    @Test
    void addItemTest() throws Exception {
        //given
        willDoNothing().given(itemService).addItem(any());

        //when & then
        mockMvc.perform(post("/item"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateItemTest() throws Exception {
        //given
        List<ItemDto> itemDtoList = this.itemDtoList;
        Long itemNo = itemDtoList.get(0).getItemNo();

        willDoNothing().given(itemService).updateItem(any());

        //when & then
        mockMvc.perform(put("/item/" + itemNo))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testDeleteContent() throws Exception {
        //given
        List<ItemDto> itemDtoList = this.itemDtoList;
        Long itemNo = itemDtoList.get(0).getItemNo();

        willDoNothing().given(itemService).deleteItem(any());

        //when & then
        mockMvc.perform(delete("/item/" + itemNo))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
