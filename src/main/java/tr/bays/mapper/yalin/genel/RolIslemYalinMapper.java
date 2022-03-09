package tr.bays.mapper.yalin.genel;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.genel.RolIslemYalinDto;
import tr.bays.entity.genel.RolIslem;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RolIslemYalinMapper extends DtoMapper<RolIslemYalinDto, RolIslem> {
}

