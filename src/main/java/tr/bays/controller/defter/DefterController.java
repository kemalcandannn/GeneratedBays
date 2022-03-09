package tr.bays.controller.defter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.defter.DefterDto;
import tr.bays.service.defter.DefterService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterController extends BaseCrudController<DefterDto> {

	private DefterService defterService;

	public DefterController(DefterService defterService) {
		super(defterService);

		this.defterService = defterService;
	}

	@Override
	protected DefterDto createNewModel() {
		return new DefterDto();
	}

}

