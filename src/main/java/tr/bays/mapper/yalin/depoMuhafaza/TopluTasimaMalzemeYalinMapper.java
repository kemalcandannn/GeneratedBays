package tr.bays.mapper.yalin.depoMuhafaza;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depoMuhafaza.TopluTasimaMalzemeYalinDto;
import tr.bays.entity.depoMuhafaza.TopluTasimaMalzeme;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TopluTasimaMalzemeYalinMapper extends DtoMapper<TopluTasimaMalzemeYalinDto, TopluTasimaMalzeme> {
}

