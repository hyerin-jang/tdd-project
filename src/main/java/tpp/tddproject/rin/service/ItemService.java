package tpp.tddproject.rin.service;

import tpp.tddproject.domain.entity.item.Item;
import tpp.tddproject.rin.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAllItem();

    List<ItemDto> findItemById(Long itemNo);

    void addItem(Item item);

    void updateItem(Item item);

    void deleteItem(Long itemNo);

}
