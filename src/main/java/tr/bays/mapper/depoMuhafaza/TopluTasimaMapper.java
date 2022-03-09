package tr.bays.mapper.depoMuhafaza;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depoMuhafaza.TopluTasimaDto;
import tr.bays.entity.depoMuhafaza.TopluTasima;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TopluTasimaMapper extends DtoMapper<TopluTasimaDto, TopluTasima> {
}

