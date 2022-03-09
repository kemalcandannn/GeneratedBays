package tr.bays.controller.arsivSahibi;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.arsivSahibi.ArsivSahibiDto;
import tr.bays.service.arsivSahibi.ArsivSahibiService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivSahibiController extends BaseCrudController<ArsivSahibiDto> {

	private ArsivSahibiService arsivSahibiService;

	public ArsivSahibiController(ArsivSahibiService arsivSahibiService) {
		super(arsivSahibiService);

		this.arsivSahibiService = arsivSahibiService;
	}

	@Override
	protected ArsivSahibiDto createNewModel() {
		return new ArsivSahibiDto();
	}

}

