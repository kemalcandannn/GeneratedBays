package tr.bays.controller.depo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depo.DepoDto;
import tr.bays.service.depo.DepoService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoController extends BaseCrudController<DepoDto> {

	private DepoService depoService;

	public DepoController(DepoService depoService) {
		super(depoService);

		this.depoService = depoService;
	}

	@Override
	protected DepoDto createNewModel() {
		return new DepoDto();
	}

}

