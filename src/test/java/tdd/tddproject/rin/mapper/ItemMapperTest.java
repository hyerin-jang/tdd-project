package tdd.tddproject.rin.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.item.Item;
import tdd.tddproject.rin.dto.ItemDto;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class ItemMapperTest {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Test
    public void toDtoTest() {
        //given
        Item item = Item.builder().itemComp("nike").itemName("hoodie").build();

        //when
        ItemDto itemDto = INSTANCE.toDto(item);

        //then
        assertThat(itemDto)
                .extracting("itemComp", "itemName")
                .containsExactly("nike", "hoodie");
    }

    @Test
    public void toEntityTest() {
        //given
        ItemDto itemDto = ItemDto.builder().itemComp("nike").itemName("hoodie").build();

        //when
        Item item = INSTANCE.toEntity(itemDto);

        //then
        assertThat(item)
                .extracting("itemComp", "itemName")
                .containsExactly("nike", "hoodie");
    }

}
