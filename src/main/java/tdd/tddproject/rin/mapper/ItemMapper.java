package tdd.tddproject.rin.mapper;

import org.mapstruct.Mapper;
import tdd.tddproject.domain.entity.item.Item;
import tdd.tddproject.global.mapper.GenericMapper;
import tdd.tddproject.rin.dto.ItemDto;

@Mapper(componentModel = "spring")
public interface ItemMapper extends GenericMapper<ItemDto, Item> {

}
