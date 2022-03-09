package tr.bays.mapper.yalin.arsivMateryali;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.arsivMateryali.IslenmemisKlasorAlanYalinDto;
import tr.bays.entity.arsivMateryali.IslenmemisKlasorAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IslenmemisKlasorAlanYalinMapper extends DtoMapper<IslenmemisKlasorAlanYalinDto, IslenmemisKlasorAlan> {
}

