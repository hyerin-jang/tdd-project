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
    public List<ItemDto> findItemByItemNo(Long itemNo) {

        List<Item> itemList = itemRepository.findByItemNo(itemNo);
        List<ItemDto> result = itemMapper.toDtoList(itemList);

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
