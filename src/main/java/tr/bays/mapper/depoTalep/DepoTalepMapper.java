package tr.bays.mapper.depoTalep;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depoTalep.DepoTalepDto;
import tr.bays.entity.depoTalep.DepoTalep;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoTalepMapper extends DtoMapper<DepoTalepDto, DepoTalep> {
}

