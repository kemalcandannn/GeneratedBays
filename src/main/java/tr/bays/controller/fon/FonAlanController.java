package tr.bays.controller.fon;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.fon.FonAlanDto;
import tr.bays.service.fon.FonAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class FonAlanController extends BaseCrudController<FonAlanDto> {

	private FonAlanService fonAlanService;

	public FonAlanController(FonAlanService fonAlanService) {
		super(fonAlanService);

		this.fonAlanService = fonAlanService;
	}

	@Override
	protected FonAlanDto createNewModel() {
		return new FonAlanDto();
	}

}

