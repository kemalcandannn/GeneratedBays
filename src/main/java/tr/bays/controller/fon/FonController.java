package tr.bays.controller.fon;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.fon.FonDto;
import tr.bays.service.fon.FonService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class FonController extends BaseCrudController<FonDto> {

	private FonService fonService;

	public FonController(FonService fonService) {
		super(fonService);

		this.fonService = fonService;
	}

	@Override
	protected FonDto createNewModel() {
		return new FonDto();
	}

}

