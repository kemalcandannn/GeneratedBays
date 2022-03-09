package tr.bays.controller.defter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.defter.DefterAlanDto;
import tr.bays.service.defter.DefterAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterAlanController extends BaseCrudController<DefterAlanDto> {

	private DefterAlanService defterAlanService;

	public DefterAlanController(DefterAlanService defterAlanService) {
		super(defterAlanService);

		this.defterAlanService = defterAlanService;
	}

	@Override
	protected DefterAlanDto createNewModel() {
		return new DefterAlanDto();
	}

}

