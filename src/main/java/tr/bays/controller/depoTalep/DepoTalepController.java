package tr.bays.controller.depoTalep;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depoTalep.DepoTalepDto;
import tr.bays.service.depoTalep.DepoTalepService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoTalepController extends BaseCrudController<DepoTalepDto> {

	private DepoTalepService depoTalepService;

	public DepoTalepController(DepoTalepService depoTalepService) {
		super(depoTalepService);

		this.depoTalepService = depoTalepService;
	}

	@Override
	protected DepoTalepDto createNewModel() {
		return new DepoTalepDto();
	}

}

