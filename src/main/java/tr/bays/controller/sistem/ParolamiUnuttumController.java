package tr.bays.controller.sistem;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.KullaniciDto;
import tr.bays.service.KullaniciService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class ParolamiUnuttumController extends BaseCrudController<KullaniciDto> {

	private String kullanici_adi;
	private String cep_telefonu;
	
	private KullaniciService kullaniciService;
	private PasswordEncoder passwordEncoder;
	private SessionController sessionController;

	@Override
	protected KullaniciDto createNewModel() {
		return new KullaniciDto();
	}
	
	public ParolamiUnuttumController(KullaniciService kullaniciService, PasswordEncoder passwordEncoder, SessionController sessionController) {
		super(kullaniciService);
		this.kullaniciService = kullaniciService;
		this.passwordEncoder = passwordEncoder;
		this.sessionController = sessionController;
	}

	public void parolamiUnuttum() {
		System.out.println(kullanici_adi);
		System.out.println(cep_telefonu);
		KullaniciDto kullaniciDto = kullaniciService.kullaniciAdiIleGetir(kullanici_adi);
		
		if(kullaniciDto == null) {
			hataKodlariService.hataKoduGetir(12);
			return;
		}

		if(kullaniciService.parolamiUnuttum(passwordEncoder, kullaniciDto)) {
			sessionController.setKullaniciDto(kullaniciDto);
			hataKodlariService.hataKoduGetir(10);
		}else {
			hataKodlariService.hataKoduGetir(11);
		}

	}
}
