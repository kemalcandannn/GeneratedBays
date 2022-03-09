package tr.bays.controller.metadata;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.metadata.AlanDto;
import tr.bays.service.metadata.AlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class AlanController extends BaseCrudController<AlanDto> {

	private AlanService alanService;

	public AlanController(AlanService alanService) {
		super(alanService);

		this.alanService = alanService;
	}

	@Override
	protected AlanDto createNewModel() {
		return new AlanDto();
	}

}

