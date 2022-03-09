package tr.bays.mapper.yalin.hamEvrak;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.hamEvrak.HamEvrakYalinDto;
import tr.bays.entity.hamEvrak.HamEvrak;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HamEvrakYalinMapper extends DtoMapper<HamEvrakYalinDto, HamEvrak> {
}

