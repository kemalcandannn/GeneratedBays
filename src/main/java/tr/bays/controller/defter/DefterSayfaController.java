package tr.bays.controller.defter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.defter.DefterSayfaDto;
import tr.bays.service.defter.DefterSayfaService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterSayfaController extends BaseCrudController<DefterSayfaDto> {

	private DefterSayfaService defterSayfaService;

	public DefterSayfaController(DefterSayfaService defterSayfaService) {
		super(defterSayfaService);

		this.defterSayfaService = defterSayfaService;
	}

	@Override
	protected DefterSayfaDto createNewModel() {
		return new DefterSayfaDto();
	}

}

