package tr.bays.controller.gomlek;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.gomlek.GomlekAlanDto;
import tr.bays.service.gomlek.GomlekAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekAlanController extends BaseCrudController<GomlekAlanDto> {

	private GomlekAlanService gomlekAlanService;

	public GomlekAlanController(GomlekAlanService gomlekAlanService) {
		super(gomlekAlanService);

		this.gomlekAlanService = gomlekAlanService;
	}

	@Override
	protected GomlekAlanDto createNewModel() {
		return new GomlekAlanDto();
	}

}

