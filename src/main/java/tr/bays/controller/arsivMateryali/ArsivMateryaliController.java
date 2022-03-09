package tr.bays.controller.arsivMateryali;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.arsivMateryali.ArsivMateryaliDto;
import tr.bays.service.arsivMateryali.ArsivMateryaliService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivMateryaliController extends BaseCrudController<ArsivMateryaliDto> {

	private ArsivMateryaliService arsivMateryaliService;

	public ArsivMateryaliController(ArsivMateryaliService arsivMateryaliService) {
		super(arsivMateryaliService);

		this.arsivMateryaliService = arsivMateryaliService;
	}

	@Override
	protected ArsivMateryaliDto createNewModel() {
		return new ArsivMateryaliDto();
	}

}

