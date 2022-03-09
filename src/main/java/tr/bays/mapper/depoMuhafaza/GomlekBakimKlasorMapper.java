package tr.bays.mapper.depoMuhafaza;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depoMuhafaza.GomlekBakimKlasorDto;
import tr.bays.entity.depoMuhafaza.GomlekBakimKlasor;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekBakimKlasorMapper extends DtoMapper<GomlekBakimKlasorDto, GomlekBakimKlasor> {
}

