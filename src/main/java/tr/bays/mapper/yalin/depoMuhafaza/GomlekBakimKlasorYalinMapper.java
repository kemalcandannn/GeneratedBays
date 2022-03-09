package tr.bays.mapper.yalin.depoMuhafaza;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depoMuhafaza.GomlekBakimKlasorYalinDto;
import tr.bays.entity.depoMuhafaza.GomlekBakimKlasor;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekBakimKlasorYalinMapper extends DtoMapper<GomlekBakimKlasorYalinDto, GomlekBakimKlasor> {
}

