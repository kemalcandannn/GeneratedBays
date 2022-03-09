package tr.bays.mapper.yalin.klasor;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.klasor.KlasorAlanYalinDto;
import tr.bays.entity.klasor.KlasorAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KlasorAlanYalinMapper extends DtoMapper<KlasorAlanYalinDto, KlasorAlan> {
}

