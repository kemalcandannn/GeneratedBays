package tr.bays.mapper.depoMuhafaza;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depoMuhafaza.TopluTasimaMalzemeDto;
import tr.bays.entity.depoMuhafaza.TopluTasimaMalzeme;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TopluTasimaMalzemeMapper extends DtoMapper<TopluTasimaMalzemeDto, TopluTasimaMalzeme> {
}

