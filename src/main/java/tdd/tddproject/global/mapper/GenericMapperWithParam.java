package tdd.tddproject.global.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface GenericMapperWithParam<D, P, E> {
    D toDto(E e);
    E toEntity(P p);

    List<D> toDtoList(List<E> entityList);
    List<E> toEntityList(List<P> dtoList);

    //기존 생성되어있는 entity를 업데이트하고 싶을 때 null이 아닌 값만 업데이트
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(D dto, @MappingTarget E entity);
}
