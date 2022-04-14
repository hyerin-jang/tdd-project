package tpp.tddproject.rin.mapper;

import org.mapstruct.Mapper;
import tpp.tddproject.domain.entity.item.Item;
import tpp.tddproject.global.mapper.GenericMapper;
import tpp.tddproject.rin.dto.ItemDto;

@Mapper(componentModel = "spring")
public interface ItemMapper extends GenericMapper<ItemDto, Item> {

}
