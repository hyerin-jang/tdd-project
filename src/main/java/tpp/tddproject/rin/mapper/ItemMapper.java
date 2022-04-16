package tpp.tddproject.rin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tpp.tddproject.domain.entity.item.Item;
import tpp.tddproject.global.mapper.GenericMapper;
import tpp.tddproject.rin.dto.ItemDto;

@Mapper(componentModel = "spring")
public interface ItemMapper extends GenericMapper<ItemDto, Item> {


}
