package tr.bays.controller.calismaGrubu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.calismaGrubu.CalismaGrubuDto;
import tr.bays.service.calismaGrubu.CalismaGrubuService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class CalismaGrubuController extends BaseCrudController<CalismaGrubuDto> {

	private CalismaGrubuService calismaGrubuService;

	public CalismaGrubuController(CalismaGrubuService calismaGrubuService) {
		super(calismaGrubuService);

		this.calismaGrubuService = calismaGrubuService;
	}

	@Override
	protected CalismaGrubuDto createNewModel() {
		return new CalismaGrubuDto();
	}

}

