package tr.bays.mapper.depoTalep;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depoTalep.DepoTalepTarihceLogDto;
import tr.bays.entity.depoTalep.DepoTalepTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoTalepTarihceLogMapper extends DtoMapper<DepoTalepTarihceLogDto, DepoTalepTarihceLog> {
}

