package tr.bays.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import tr.bays.dto.KullaniciDto;

public interface KullaniciService extends BaseCrudService<KullaniciDto> {

	KullaniciDto kullaniciAdiIleGetir(String kullaniciAdi);
	boolean parolamiUnuttum(PasswordEncoder passwordEncoder, KullaniciDto kullaniciDto);
}

