package tr.bays.controller.arsivMateryali;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.arsivMateryali.IslenmemisKlasorDto;
import tr.bays.service.arsivMateryali.IslenmemisKlasorService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class IslenmemisKlasorController extends BaseCrudController<IslenmemisKlasorDto> {

	private IslenmemisKlasorService islenmemisKlasorService;

	public IslenmemisKlasorController(IslenmemisKlasorService islenmemisKlasorService) {
		super(islenmemisKlasorService);

		this.islenmemisKlasorService = islenmemisKlasorService;
	}

	@Override
	protected IslenmemisKlasorDto createNewModel() {
		return new IslenmemisKlasorDto();
	}

}

