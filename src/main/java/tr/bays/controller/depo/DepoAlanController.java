package tr.bays.controller.depo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depo.DepoAlanDto;
import tr.bays.service.depo.DepoAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoAlanController extends BaseCrudController<DepoAlanDto> {

	private DepoAlanService depoAlanService;

	public DepoAlanController(DepoAlanService depoAlanService) {
		super(depoAlanService);

		this.depoAlanService = depoAlanService;
	}

	@Override
	protected DepoAlanDto createNewModel() {
		return new DepoAlanDto();
	}

}

