package tr.bays.controller.genel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.genel.IslemDto;
import tr.bays.service.genel.IslemService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class IslemController extends BaseCrudController<IslemDto> {

	private IslemService islemService;

	public IslemController(IslemService islemService) {
		super(islemService);

		this.islemService = islemService;
	}

	@Override
	protected IslemDto createNewModel() {
		return new IslemDto();
	}

}

