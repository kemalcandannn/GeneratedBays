package tr.bays.controller.gomlek;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.gomlek.GomlekEnvanterDto;
import tr.bays.service.gomlek.GomlekEnvanterService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekEnvanterController extends BaseCrudController<GomlekEnvanterDto> {

	private GomlekEnvanterService gomlekEnvanterService;

	public GomlekEnvanterController(GomlekEnvanterService gomlekEnvanterService) {
		super(gomlekEnvanterService);

		this.gomlekEnvanterService = gomlekEnvanterService;
	}

	@Override
	protected GomlekEnvanterDto createNewModel() {
		return new GomlekEnvanterDto();
	}

}

