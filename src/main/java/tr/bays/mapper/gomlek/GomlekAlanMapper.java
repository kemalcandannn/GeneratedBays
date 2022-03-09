package tr.bays.mapper.gomlek;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.gomlek.GomlekAlanDto;
import tr.bays.entity.gomlek.GomlekAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekAlanMapper extends DtoMapper<GomlekAlanDto, GomlekAlan> {
}

