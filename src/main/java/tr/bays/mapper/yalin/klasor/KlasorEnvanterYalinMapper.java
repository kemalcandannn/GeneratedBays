package tr.bays.mapper.yalin.klasor;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.klasor.KlasorEnvanterYalinDto;
import tr.bays.entity.klasor.KlasorEnvanter;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KlasorEnvanterYalinMapper extends DtoMapper<KlasorEnvanterYalinDto, KlasorEnvanter> {
}

