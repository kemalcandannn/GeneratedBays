package tr.bays.mapper.depoMuhafaza;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depoMuhafaza.GomlekBakimDto;
import tr.bays.entity.depoMuhafaza.GomlekBakim;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekBakimMapper extends DtoMapper<GomlekBakimDto, GomlekBakim> {
}

