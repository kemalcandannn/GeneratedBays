package tr.bays.mapper.hamEvrak;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.hamEvrak.HamEvrakDto;
import tr.bays.entity.hamEvrak.HamEvrak;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HamEvrakMapper extends DtoMapper<HamEvrakDto, HamEvrak> {
}

