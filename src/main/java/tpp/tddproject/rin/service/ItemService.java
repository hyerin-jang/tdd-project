package tpp.tddproject.rin.service;

import tpp.tddproject.rin.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAllItem();

    ItemDto findItemByItemNo(Long itemNo);

    void addItem(List<ItemDto> itemDto);

    void updateItem(List<ItemDto> itemDto);

    void deleteItem(Long itemNo);

}
