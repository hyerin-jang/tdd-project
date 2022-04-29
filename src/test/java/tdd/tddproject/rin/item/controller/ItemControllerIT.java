package tdd.tddproject.rin.item.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.item.Item;
import tdd.tddproject.rin.item.dto.ItemDto;
import tdd.tddproject.rin.mapper.ItemMapper;
import tdd.tddproject.rin.item.repository.ItemRepository;

import javax.persistence.EntityManager;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ItemControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private EntityManager entityManager;

    ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        //h2 pk generatedValue 초기화
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
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
    void findAllItemTest() throws Exception {
        //given
        ItemDto itemDto = ItemDto.builder()
                .itemComp("nike")
                .itemName("hoodie")
                .itemPrice(10000)
                .itemStock(24).build();

        Item item = itemMapper.toEntity(itemDto);
        itemRepository.save(item);

        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/item"))
                .andExpect(status().isOk());

        //then
        resultActions
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..itemComp").value("nike"))
                .andExpect(jsonPath("$..itemName").value("hoodie"))
                .andExpect(jsonPath("$..itemPrice").value(10000))
                .andExpect(jsonPath("$..itemStock").value(24))
                .andDo(print());

    }

    @Test
    void findItemByItemNameTest() throws Exception {
        //given
        ItemDto itemDto = ItemDto.builder()
                .itemComp("nike")
                .itemName("jacket")
                .itemPrice(40000)
                .itemStock(43).build();

        Item item = itemMapper.toEntity(itemDto);
        String itemName = itemRepository.save(item).getItemName();

        //when
        ResultActions resultActions = mockMvc.perform(get("/item/" + itemName))
                .andExpect(status().isOk());

        //then
        resultActions
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..itemComp").value("nike"))
                .andExpect(jsonPath("$..itemName").value("jacket"))
                .andExpect(jsonPath("$..itemPrice").value(40000))
                .andExpect(jsonPath("$..itemStock").value(43))
                .andDo(print());

    }

    @Test
    void addItemTest() throws Exception {
        //given
        ItemDto itemDto = ItemDto.builder()
                .itemComp("nike")
                .itemName("hat")
                .itemPrice(20000)
                .itemStock(3).build();

        String content = new ObjectMapper().writeValueAsString(itemDto);

        //when
        ResultActions resultActions = mockMvc.perform(post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(print());

    }

    // TODO: update test
    @Test
    public void updateItemTest() throws Exception {
        //given
        Long itemNo = 1L;
        ItemDto itemDto = ItemDto.builder()
                .itemComp("nike")
                .itemName("hat")
                .itemPrice(20000)
                .itemStock(3).build();

        String content = new ObjectMapper().writeValueAsString(itemDto);

        //when
        ResultActions resultActions = mockMvc.perform((put("/item/{itemNo}", itemNo)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)));

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteItemTest() throws Exception {
        //given
        ItemDto itemDto = ItemDto.builder()
                .itemComp("nike")
                .itemName("shoes")
                .itemPrice(70000)
                .itemStock(1).build();

        Item item = itemMapper.toEntity(itemDto);
        Long itemNo = itemRepository.save(item).getItemNo();

        //when
        ResultActions resultActions = mockMvc.perform(delete("/item/" + itemNo));

        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

}
