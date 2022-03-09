package tr.bays.mapper.klasor;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.klasor.KlasorEnvanterDto;
import tr.bays.entity.klasor.KlasorEnvanter;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KlasorEnvanterMapper extends DtoMapper<KlasorEnvanterDto, KlasorEnvanter> {
}

