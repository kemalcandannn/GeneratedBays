package tr.bays.mapper.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.loglama.KlasorTarihceLogDto;
import tr.bays.entity.loglama.KlasorTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KlasorTarihceLogMapper extends DtoMapper<KlasorTarihceLogDto, KlasorTarihceLog> {
}

