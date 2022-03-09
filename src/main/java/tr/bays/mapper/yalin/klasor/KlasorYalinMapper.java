package tr.bays.mapper.yalin.klasor;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.klasor.KlasorYalinDto;
import tr.bays.entity.klasor.Klasor;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KlasorYalinMapper extends DtoMapper<KlasorYalinDto, Klasor> {
}

