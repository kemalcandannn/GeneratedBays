package tr.bays.mapper.genel;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.genel.RolDto;
import tr.bays.entity.genel.Rol;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RolMapper extends DtoMapper<RolDto, Rol> {
}

