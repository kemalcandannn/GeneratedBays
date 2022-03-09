package tr.bays.mapper.yalin.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.loglama.KlasorTarihceLogYalinDto;
import tr.bays.entity.loglama.KlasorTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KlasorTarihceLogYalinMapper extends DtoMapper<KlasorTarihceLogYalinDto, KlasorTarihceLog> {
}

