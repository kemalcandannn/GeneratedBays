package tr.bays.controller.genel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.genel.RolIslemDto;
import tr.bays.service.genel.RolIslemService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class RolIslemController extends BaseCrudController<RolIslemDto> {

	private RolIslemService rolIslemService;

	public RolIslemController(RolIslemService rolIslemService) {
		super(rolIslemService);

		this.rolIslemService = rolIslemService;
	}

	@Override
	protected RolIslemDto createNewModel() {
		return new RolIslemDto();
	}

}

