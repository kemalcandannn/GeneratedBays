package tr.bays.controller.genel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.genel.RolDto;
import tr.bays.service.genel.RolService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class RolController extends BaseCrudController<RolDto> {

	private RolService rolService;

	public RolController(RolService rolService) {
		super(rolService);

		this.rolService = rolService;
	}

	@Override
	protected RolDto createNewModel() {
		return new RolDto();
	}

}

