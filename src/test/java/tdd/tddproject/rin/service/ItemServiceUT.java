package tdd.tddproject.rin.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.tddproject.domain.entity.item.Item;
import tdd.tddproject.rin.dto.ItemDto;
import tdd.tddproject.rin.mapper.ItemMapper;
import tdd.tddproject.rin.repository.ItemRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ItemServiceUT {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    ItemDto itemDto;

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        this.itemDto = ItemDto.builder()
                .itemComp("nike")
                .itemName("hoodie")
                .itemPrice(10000)
                .itemStock(24).build();

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
    public void findAllItemTest() {
        //given
        List<ItemDto> itemDtoList = Arrays.asList(this.itemDto);

        List<Item> itemList = itemMapper.toEntityList(itemDtoList);
        itemRepository.saveAll(itemList);
        given(itemRepository.findAll()).willReturn(itemList);

        //when
        List<ItemDto> itemServiceResult = itemService.findAllItem();

        //then
        assertThat(itemList.get(0).getItemComp()).isEqualTo(itemServiceResult.get(0).getItemComp());
        assertThat(itemList.get(0).getItemName()).isEqualTo(itemServiceResult.get(0).getItemName());
        assertThat(itemList.get(0).getItemPrice()).isEqualTo(itemServiceResult.get(0).getItemPrice());
        assertThat(itemList.get(0).getItemStock()).isEqualTo(itemServiceResult.get(0).getItemStock());
    }

    @Test
    public void findItemItemNameTest() {
        //given
        Item item = itemMapper.toEntity(this.itemDto);
        itemRepository.save(item);
        given(itemRepository.findByItemName("nike")).willReturn(item);

        //when
        ItemDto itemServiceResult = itemService.findItemByItemName("nike");

        //then
        assertThat(item.getItemComp()).isEqualTo(itemServiceResult.getItemComp());
        assertThat(item.getItemName()).isEqualTo(itemServiceResult.getItemName());
        assertThat(item.getItemPrice()).isEqualTo(itemServiceResult.getItemPrice());
        assertThat(item.getItemStock()).isEqualTo(itemServiceResult.getItemStock());
    }

    @Test
    public void addItem() {

    }

    @Test
    public void updateItem() {

    }

    @Test
    public void deleteItem() {

    }

}
