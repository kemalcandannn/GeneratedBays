package tr.bays.controller.teskilat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.teskilat.KullaniciHesapDto;
import tr.bays.service.teskilat.KullaniciHesapService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class KullaniciHesapController extends BaseCrudController<KullaniciHesapDto> {

	private KullaniciHesapService kullaniciHesapService;

	public KullaniciHesapController(KullaniciHesapService kullaniciHesapService) {
		super(kullaniciHesapService);

		this.kullaniciHesapService = kullaniciHesapService;
	}

	@Override
	protected KullaniciHesapDto createNewModel() {
		return new KullaniciHesapDto();
	}

}

