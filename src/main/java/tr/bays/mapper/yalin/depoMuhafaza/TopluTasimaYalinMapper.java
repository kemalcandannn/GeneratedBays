package tr.bays.mapper.yalin.depoMuhafaza;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depoMuhafaza.TopluTasimaYalinDto;
import tr.bays.entity.depoMuhafaza.TopluTasima;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TopluTasimaYalinMapper extends DtoMapper<TopluTasimaYalinDto, TopluTasima> {
}

