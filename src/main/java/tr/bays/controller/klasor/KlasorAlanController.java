package tr.bays.controller.klasor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.klasor.KlasorAlanDto;
import tr.bays.service.klasor.KlasorAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class KlasorAlanController extends BaseCrudController<KlasorAlanDto> {

	private KlasorAlanService klasorAlanService;

	public KlasorAlanController(KlasorAlanService klasorAlanService) {
		super(klasorAlanService);

		this.klasorAlanService = klasorAlanService;
	}

	@Override
	protected KlasorAlanDto createNewModel() {
		return new KlasorAlanDto();
	}

}

