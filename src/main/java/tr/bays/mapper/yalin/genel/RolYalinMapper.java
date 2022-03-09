package tr.bays.mapper.yalin.genel;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.genel.RolYalinDto;
import tr.bays.entity.genel.Rol;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RolYalinMapper extends DtoMapper<RolYalinDto, Rol> {
}

