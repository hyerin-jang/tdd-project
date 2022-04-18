package tdd.tddproject.rin.service;

import tdd.tddproject.rin.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAllItem();

    ItemDto findItemByItemName(String itemName);

    void addItem(ItemDto itemDto);

    void updateItem(ItemDto itemDto);

    void deleteItem(Long itemNo);

}
