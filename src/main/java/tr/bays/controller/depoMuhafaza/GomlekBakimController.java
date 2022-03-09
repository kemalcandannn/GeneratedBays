package tr.bays.controller.depoMuhafaza;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depoMuhafaza.GomlekBakimDto;
import tr.bays.service.depoMuhafaza.GomlekBakimService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekBakimController extends BaseCrudController<GomlekBakimDto> {

	private GomlekBakimService gomlekBakimService;

	public GomlekBakimController(GomlekBakimService gomlekBakimService) {
		super(gomlekBakimService);

		this.gomlekBakimService = gomlekBakimService;
	}

	@Override
	protected GomlekBakimDto createNewModel() {
		return new GomlekBakimDto();
	}

}

