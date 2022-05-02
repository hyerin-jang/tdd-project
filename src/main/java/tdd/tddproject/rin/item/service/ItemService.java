package tdd.tddproject.rin.item.service;

import tdd.tddproject.rin.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAllItem();

    ItemDto findItemByItemName(String itemName);

    void addItem(ItemDto itemDto);

    void updateItem(ItemDto itemDto);

    void deleteItem(Long itemNo);

}
