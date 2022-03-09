package tr.bays.mapper.yalin.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.teskilat.ArsivKurumuAlanYalinDto;
import tr.bays.entity.teskilat.ArsivKurumuAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivKurumuAlanYalinMapper extends DtoMapper<ArsivKurumuAlanYalinDto, ArsivKurumuAlan> {
}

