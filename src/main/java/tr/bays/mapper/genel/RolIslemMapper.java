package tr.bays.mapper.genel;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.genel.RolIslemDto;
import tr.bays.entity.genel.RolIslem;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RolIslemMapper extends DtoMapper<RolIslemDto, RolIslem> {
}

