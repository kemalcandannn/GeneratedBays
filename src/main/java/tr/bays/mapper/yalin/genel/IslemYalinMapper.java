package tr.bays.mapper.yalin.genel;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.genel.IslemYalinDto;
import tr.bays.entity.genel.Islem;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IslemYalinMapper extends DtoMapper<IslemYalinDto, Islem> {
}

