package tr.bays.mapper.yalin.arsivMateryali;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.arsivMateryali.IslenmemisKlasorYalinDto;
import tr.bays.entity.arsivMateryali.IslenmemisKlasor;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IslenmemisKlasorYalinMapper extends DtoMapper<IslenmemisKlasorYalinDto, IslenmemisKlasor> {
}

