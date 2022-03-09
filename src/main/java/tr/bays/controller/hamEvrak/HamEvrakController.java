package tr.bays.controller.hamEvrak;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.hamEvrak.HamEvrakDto;
import tr.bays.service.hamEvrak.HamEvrakService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class HamEvrakController extends BaseCrudController<HamEvrakDto> {

	private HamEvrakService hamEvrakService;

	public HamEvrakController(HamEvrakService hamEvrakService) {
		super(hamEvrakService);

		this.hamEvrakService = hamEvrakService;
	}

	@Override
	protected HamEvrakDto createNewModel() {
		return new HamEvrakDto();
	}

}

