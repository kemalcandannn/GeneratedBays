package tr.bays.controller.defter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.defter.DefterSayfaAlanDto;
import tr.bays.service.defter.DefterSayfaAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterSayfaAlanController extends BaseCrudController<DefterSayfaAlanDto> {

	private DefterSayfaAlanService defterSayfaAlanService;

	public DefterSayfaAlanController(DefterSayfaAlanService defterSayfaAlanService) {
		super(defterSayfaAlanService);

		this.defterSayfaAlanService = defterSayfaAlanService;
	}

	@Override
	protected DefterSayfaAlanDto createNewModel() {
		return new DefterSayfaAlanDto();
	}

}

