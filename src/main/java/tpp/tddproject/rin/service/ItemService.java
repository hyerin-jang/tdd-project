package tpp.tddproject.rin.service;

import tpp.tddproject.rin.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAllItem();

    List<ItemDto> findItemByItemNo(Long itemNo);

    void addItem(ItemDto itemDto);

    void updateItem(ItemDto itemDto);

    void deleteItem(Long itemNo);

}
