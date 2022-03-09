package tr.bays.controller.hamEvrak;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.hamEvrak.HamEvrakBirimDto;
import tr.bays.service.hamEvrak.HamEvrakBirimService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class HamEvrakBirimController extends BaseCrudController<HamEvrakBirimDto> {

	private HamEvrakBirimService hamEvrakBirimService;

	public HamEvrakBirimController(HamEvrakBirimService hamEvrakBirimService) {
		super(hamEvrakBirimService);

		this.hamEvrakBirimService = hamEvrakBirimService;
	}

	@Override
	protected HamEvrakBirimDto createNewModel() {
		return new HamEvrakBirimDto();
	}

}

