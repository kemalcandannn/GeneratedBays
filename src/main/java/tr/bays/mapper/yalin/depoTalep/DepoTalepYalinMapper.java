package tr.bays.mapper.yalin.depoTalep;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depoTalep.DepoTalepYalinDto;
import tr.bays.entity.depoTalep.DepoTalep;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoTalepYalinMapper extends DtoMapper<DepoTalepYalinDto, DepoTalep> {
}

