package tr.bays.controller.kullanici;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.kullanici.KullaniciDto;
import tr.bays.dto.kullanici.KullaniciIletisimDto;
import tr.bays.service.kullanici.KullaniciIletisimService;
import util.enums.AktifEnum;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class KullaniciIletisimController extends BaseCrudController<KullaniciIletisimDto> {
	
	private boolean panel;
	
	private KullaniciIletisimService kullaniciIletisimService;

	public KullaniciIletisimController(KullaniciIletisimService kullaniciIletisimService) {
		super(kullaniciIletisimService);

		this.kullaniciIletisimService = kullaniciIletisimService;
	}

	@Override
	protected KullaniciIletisimDto createNewModel() {
		return new KullaniciIletisimDto();
	}

	@Override
	public void ekleFormAc() {
		super.ekleFormAc();
		
		getSecilen().setKullanici_id(getSorguKriteri().getKullanici_id());
	}
	
	@Override
	public void guncelleFormAc() {
		super.guncelleFormAc();
		
		getSecilen().setKullanici_id(getSorguKriteri().getKullanici_id());
	}
	
	public void tabloFormAc(KullaniciDto kullaniciDto) {
		panel = true;
		setSecilen(createNewModel());
		KullaniciIletisimDto kullaniciIletisimDto = createNewModel();
		kullaniciIletisimDto.setKullanici_id(kullaniciDto);
		setSorguKriteri(kullaniciIletisimDto);
	}

	public void geri() {
		panel = false;
		setSecilen(null);
		setSorguKriteri(null);
	}

	@Override
	protected void afterEkle(String dialogName) {
		super.afterEkle(dialogName);
		
		if(getSecilen().getAktif() == AktifEnum.AKTIF.getValue()) {
			kullaniciIletisimService.aktifHaleGetir(getSecilen());
		}
	}
	
	@Override
	protected void afterGuncelle(String dialogName) {
		super.afterGuncelle(dialogName);

		if(getSecilen().getAktif() == AktifEnum.AKTIF.getValue()) {
			kullaniciIletisimService.aktifHaleGetir(getSecilen());
		}
	}
}
