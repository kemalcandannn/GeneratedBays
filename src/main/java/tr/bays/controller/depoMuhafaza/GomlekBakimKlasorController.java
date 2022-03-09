package tr.bays.controller.depoMuhafaza;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depoMuhafaza.GomlekBakimKlasorDto;
import tr.bays.service.depoMuhafaza.GomlekBakimKlasorService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekBakimKlasorController extends BaseCrudController<GomlekBakimKlasorDto> {

	private GomlekBakimKlasorService gomlekBakimKlasorService;

	public GomlekBakimKlasorController(GomlekBakimKlasorService gomlekBakimKlasorService) {
		super(gomlekBakimKlasorService);

		this.gomlekBakimKlasorService = gomlekBakimKlasorService;
	}

	@Override
	protected GomlekBakimKlasorDto createNewModel() {
		return new GomlekBakimKlasorDto();
	}

}

