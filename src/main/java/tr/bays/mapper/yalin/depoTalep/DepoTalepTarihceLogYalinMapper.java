package tr.bays.mapper.yalin.depoTalep;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depoTalep.DepoTalepTarihceLogYalinDto;
import tr.bays.entity.depoTalep.DepoTalepTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoTalepTarihceLogYalinMapper extends DtoMapper<DepoTalepTarihceLogYalinDto, DepoTalepTarihceLog> {
}

