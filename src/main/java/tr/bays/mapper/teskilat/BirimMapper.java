package tr.bays.mapper.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.teskilat.BirimDto;
import tr.bays.entity.teskilat.Birim;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BirimMapper extends DtoMapper<BirimDto, Birim> {
}

