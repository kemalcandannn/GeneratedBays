package tr.bays.mapper.yalin.depoMuhafaza;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depoMuhafaza.GomlekBakimYalinDto;
import tr.bays.entity.depoMuhafaza.GomlekBakim;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekBakimYalinMapper extends DtoMapper<GomlekBakimYalinDto, GomlekBakim> {
}

