package tr.bays.controller.teskilat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.teskilat.KullaniciDto;
import tr.bays.service.teskilat.KullaniciService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class KullaniciController extends BaseCrudController<KullaniciDto> {

	private KullaniciService kullaniciService;

	public KullaniciController(KullaniciService kullaniciService) {
		super(kullaniciService);

		this.kullaniciService = kullaniciService;
	}

	@Override
	protected KullaniciDto createNewModel() {
		return new KullaniciDto();
	}

}

