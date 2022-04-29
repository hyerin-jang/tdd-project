package tdd.tddproject.rin.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tdd.tddproject.domain.entity.item.Item;
import tdd.tddproject.rin.item.repository.ItemRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB, Replace.NONE 실제 DB
@DataJpaTest
public class ItemRepositoryUT {

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
