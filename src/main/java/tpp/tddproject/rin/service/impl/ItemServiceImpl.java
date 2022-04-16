package tpp.tddproject.rin.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tpp.tddproject.domain.entity.item.Item;
import tpp.tddproject.rin.dto.ItemDto;
import tpp.tddproject.rin.mapper.ItemMapper;
import tpp.tddproject.rin.repository.ItemRepository;
import tpp.tddproject.rin.service.ItemService;

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
    public ItemDto findItemByItemNo(Long itemNo) {

        Item itemList = itemRepository.findByItemNo(itemNo);
        ItemDto result = itemMapper.toDto(itemList);

        return result;
    }

    @Override
    @Transactional
    public void addItem(List<ItemDto> itemDto) {
        List<Item> itemList = itemMapper.toEntityList(itemDto);
        for (Item item : itemList) {
            itemRepository.save(item);
        }
    }

    @Override
    @Transactional
    public void updateItem(List<ItemDto> itemDto) {
        List<Item> itemList = itemMapper.toEntityList(itemDto);
        for (Item item : itemList) {
            itemRepository.save(item);
        }
    }

    @Override
    @Transactional
    public void deleteItem(Long itemNo) {
        itemRepository.deleteById(itemNo);
    }
}
