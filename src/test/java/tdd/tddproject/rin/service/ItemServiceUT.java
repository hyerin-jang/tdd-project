package tdd.tddproject.rin.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tdd.tddproject.domain.entity.item.Item;
import tdd.tddproject.rin.item.dto.ItemDto;
import tdd.tddproject.rin.mapper.ItemMapper;
import tdd.tddproject.rin.item.repository.ItemRepository;
import tdd.tddproject.rin.item.service.impl.ItemServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ItemServiceUT {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemServiceImpl itemServiceImpl;

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
        given(itemRepository.findAll()).willReturn(itemList);

        //when
        List<ItemDto> itemServiceResult = itemServiceImpl.findAllItem();

        //then
        verify(itemRepository, times(1)).findAll();

    }

    @Test
    public void findItemItemNameTest() {
        //given
        Item item = itemMapper.toEntity(this.itemDto);
        given(itemRepository.findByItemName("nike")).willReturn(item);

        //when
        ItemDto itemServiceResult = itemServiceImpl.findItemByItemName("nike");

        //then
        verify(itemRepository, times(1)).findByItemName("nike");

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
