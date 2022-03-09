package tr.bays.controller.teskilat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.teskilat.KullaniciRolDto;
import tr.bays.service.teskilat.KullaniciRolService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class KullaniciRolController extends BaseCrudController<KullaniciRolDto> {

	private KullaniciRolService kullaniciRolService;

	public KullaniciRolController(KullaniciRolService kullaniciRolService) {
		super(kullaniciRolService);

		this.kullaniciRolService = kullaniciRolService;
	}

	@Override
	protected KullaniciRolDto createNewModel() {
		return new KullaniciRolDto();
	}

}

