package tdd.tddproject.rin.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.item.Item;
import tdd.tddproject.rin.dto.ItemDto;
import tdd.tddproject.rin.mapper.ItemMapper;
import tdd.tddproject.rin.repository.ItemRepository;
import tdd.tddproject.rin.service.ItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    @Override
    public List<ItemDto> findAllItem() {

        List<Item> itemList = itemRepository.findAll();
        List<ItemDto> result = itemMapper.toDtoList(itemList);

        return result;
    }

    @Override
    public ItemDto findItemByItemName(String itemName) {

        Item itemList = itemRepository.findByItemName(itemName);
        ItemDto result = itemMapper.toDto(itemList);

        return result;
    }

    @Override
    @Transactional
    public void addItem(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void updateItem(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void deleteItem(Long itemNo) {
        itemRepository.deleteById(itemNo);
    }
}
