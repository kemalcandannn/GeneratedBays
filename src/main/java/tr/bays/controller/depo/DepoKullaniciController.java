package tr.bays.controller.depo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depo.DepoKullaniciDto;
import tr.bays.service.depo.DepoKullaniciService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoKullaniciController extends BaseCrudController<DepoKullaniciDto> {

	private DepoKullaniciService depoKullaniciService;

	public DepoKullaniciController(DepoKullaniciService depoKullaniciService) {
		super(depoKullaniciService);

		this.depoKullaniciService = depoKullaniciService;
	}

	@Override
	protected DepoKullaniciDto createNewModel() {
		return new DepoKullaniciDto();
	}

}

