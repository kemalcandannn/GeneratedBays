package tr.bays.mapper.depo;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depo.DepoKullaniciDto;
import tr.bays.entity.depo.DepoKullanici;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoKullaniciMapper extends DtoMapper<DepoKullaniciDto, DepoKullanici> {
}

