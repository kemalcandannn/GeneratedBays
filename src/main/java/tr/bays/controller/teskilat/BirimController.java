package tr.bays.controller.teskilat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.teskilat.BirimDto;
import tr.bays.service.teskilat.BirimService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class BirimController extends BaseCrudController<BirimDto> {

	private BirimService birimService;

	public BirimController(BirimService birimService) {
		super(birimService);

		this.birimService = birimService;
	}

	@Override
	protected BirimDto createNewModel() {
		return new BirimDto();
	}

}

