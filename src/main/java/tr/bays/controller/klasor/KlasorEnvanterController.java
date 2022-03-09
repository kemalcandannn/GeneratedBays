package tr.bays.controller.klasor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.klasor.KlasorEnvanterDto;
import tr.bays.service.klasor.KlasorEnvanterService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class KlasorEnvanterController extends BaseCrudController<KlasorEnvanterDto> {

	private KlasorEnvanterService klasorEnvanterService;

	public KlasorEnvanterController(KlasorEnvanterService klasorEnvanterService) {
		super(klasorEnvanterService);

		this.klasorEnvanterService = klasorEnvanterService;
	}

	@Override
	protected KlasorEnvanterDto createNewModel() {
		return new KlasorEnvanterDto();
	}

}

