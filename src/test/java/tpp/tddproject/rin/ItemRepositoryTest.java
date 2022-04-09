package tpp.tddproject.rin;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tpp.tddproject.domain.entity.item.Item;
import tpp.tddproject.rin.repository.ItemRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("save 테스트")
    public void saveTest() {
        //given
        Item item = Item.builder()
                .itemComp("nike")
                .itemName("swoosh hoodie")
                .itemPrice(10000)
                .itemStock(100).build();

        //when
        Item result = itemRepository.save(item);

        //then
        assertThat(item).isEqualTo(result);
    }

    @Test
    @DisplayName("findAll 테스트")
    public void findAllTest() {

        //given
        Item item = Item.builder()
                .itemComp("nike")
                .itemName("swoosh hoodie")
                .itemPrice(10000)
                .itemStock(100).build();

        Item saveItem = itemRepository.save(item);

        //when
        List<Item> findItem = itemRepository.findAll();

        //then
        assertThat(saveItem)
                .extracting("itemComp", "itemName", "itemPrice", "itemStock")
                .containsExactly("nike", "swoosh hoodie", 10000, 100);
    }

}
