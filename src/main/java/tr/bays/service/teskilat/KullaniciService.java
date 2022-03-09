package tr.bays.service.teskilat;


import org.springframework.security.crypto.password.PasswordEncoder;

import tr.bays.dto.teskilat.KullaniciDto;
import tr.bays.service.BaseCrudService;


public interface KullaniciService extends BaseCrudService<KullaniciDto> {

	KullaniciDto kullaniciAdiIleGetir(String kullaniciAdi);
	boolean parolamiUnuttum(PasswordEncoder passwordEncoder, KullaniciDto kullaniciDto);
}

