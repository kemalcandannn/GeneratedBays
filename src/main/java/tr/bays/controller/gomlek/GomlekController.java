package tr.bays.controller.gomlek;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.gomlek.GomlekDto;
import tr.bays.service.gomlek.GomlekService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekController extends BaseCrudController<GomlekDto> {

	private GomlekService gomlekService;

	public GomlekController(GomlekService gomlekService) {
		super(gomlekService);

		this.gomlekService = gomlekService;
	}

	@Override
	protected GomlekDto createNewModel() {
		return new GomlekDto();
	}

}

