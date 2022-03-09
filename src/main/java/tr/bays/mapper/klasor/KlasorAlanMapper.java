package tr.bays.mapper.klasor;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.klasor.KlasorAlanDto;
import tr.bays.entity.klasor.KlasorAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KlasorAlanMapper extends DtoMapper<KlasorAlanDto, KlasorAlan> {
}

