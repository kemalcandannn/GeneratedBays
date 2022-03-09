package tr.bays.controller.depoTalep;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depoTalep.DepoTalepMalzemeDto;
import tr.bays.service.depoTalep.DepoTalepMalzemeService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoTalepMalzemeController extends BaseCrudController<DepoTalepMalzemeDto> {

	private DepoTalepMalzemeService depoTalepMalzemeService;

	public DepoTalepMalzemeController(DepoTalepMalzemeService depoTalepMalzemeService) {
		super(depoTalepMalzemeService);

		this.depoTalepMalzemeService = depoTalepMalzemeService;
	}

	@Override
	protected DepoTalepMalzemeDto createNewModel() {
		return new DepoTalepMalzemeDto();
	}

}

