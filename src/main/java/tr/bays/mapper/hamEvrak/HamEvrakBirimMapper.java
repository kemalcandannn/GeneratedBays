package tr.bays.mapper.hamEvrak;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.hamEvrak.HamEvrakBirimDto;
import tr.bays.entity.hamEvrak.HamEvrakBirim;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface HamEvrakBirimMapper extends DtoMapper<HamEvrakBirimDto, HamEvrakBirim> {
}

