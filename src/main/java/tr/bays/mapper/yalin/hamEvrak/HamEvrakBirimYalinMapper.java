package tr.bays.mapper.yalin.hamEvrak;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.hamEvrak.HamEvrakBirimYalinDto;
import tr.bays.entity.hamEvrak.HamEvrakBirim;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HamEvrakBirimYalinMapper extends DtoMapper<HamEvrakBirimYalinDto, HamEvrakBirim> {
}

