package tr.bays.mapper.klasor;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.klasor.KlasorDto;
import tr.bays.entity.klasor.Klasor;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KlasorMapper extends DtoMapper<KlasorDto, Klasor> {
}

