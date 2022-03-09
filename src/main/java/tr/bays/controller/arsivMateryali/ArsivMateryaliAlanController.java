package tr.bays.controller.arsivMateryali;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.arsivMateryali.ArsivMateryaliAlanDto;
import tr.bays.service.arsivMateryali.ArsivMateryaliAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivMateryaliAlanController extends BaseCrudController<ArsivMateryaliAlanDto> {

	private ArsivMateryaliAlanService arsivMateryaliAlanService;

	public ArsivMateryaliAlanController(ArsivMateryaliAlanService arsivMateryaliAlanService) {
		super(arsivMateryaliAlanService);

		this.arsivMateryaliAlanService = arsivMateryaliAlanService;
	}

	@Override
	protected ArsivMateryaliAlanDto createNewModel() {
		return new ArsivMateryaliAlanDto();
	}

}

