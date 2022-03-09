package tr.bays.controller.arsivSahibi;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.arsivSahibi.ArsivSahibiPersonelDto;
import tr.bays.service.arsivSahibi.ArsivSahibiPersonelService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivSahibiPersonelController extends BaseCrudController<ArsivSahibiPersonelDto> {

	private ArsivSahibiPersonelService arsivSahibiPersonelService;

	public ArsivSahibiPersonelController(ArsivSahibiPersonelService arsivSahibiPersonelService) {
		super(arsivSahibiPersonelService);

		this.arsivSahibiPersonelService = arsivSahibiPersonelService;
	}

	@Override
	protected ArsivSahibiPersonelDto createNewModel() {
		return new ArsivSahibiPersonelDto();
	}

}

