package tr.bays.service.kullanici;

import org.springframework.security.crypto.password.PasswordEncoder;

import tr.bays.dto.kullanici.KullaniciDto;
import tr.bays.service.BaseCrudService;

public interface KullaniciService extends BaseCrudService<KullaniciDto> {

	KullaniciDto kullaniciAdiIleGetir(String kullaniciAdi);

	KullaniciDto parolaGuncelle(KullaniciDto kullaniciDto, String encode);

	boolean parolamiUnuttum(PasswordEncoder passwordEncoder, KullaniciDto kullaniciDto);

}
