package tr.bays.controller.klasor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.klasor.KlasorDto;
import tr.bays.service.klasor.KlasorService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class KlasorController extends BaseCrudController<KlasorDto> {

	private KlasorService klasorService;

	public KlasorController(KlasorService klasorService) {
		super(klasorService);

		this.klasorService = klasorService;
	}

	@Override
	protected KlasorDto createNewModel() {
		return new KlasorDto();
	}

}

