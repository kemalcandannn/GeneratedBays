package tr.bays.mapper.yalin.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.teskilat.ArsivKurumuYalinDto;
import tr.bays.entity.teskilat.ArsivKurumu;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivKurumuYalinMapper extends DtoMapper<ArsivKurumuYalinDto, ArsivKurumu> {
}

