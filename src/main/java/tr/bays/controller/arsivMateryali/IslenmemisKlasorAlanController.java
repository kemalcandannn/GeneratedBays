package tr.bays.controller.arsivMateryali;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.arsivMateryali.IslenmemisKlasorAlanDto;
import tr.bays.service.arsivMateryali.IslenmemisKlasorAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class IslenmemisKlasorAlanController extends BaseCrudController<IslenmemisKlasorAlanDto> {

	private IslenmemisKlasorAlanService islenmemisKlasorAlanService;

	public IslenmemisKlasorAlanController(IslenmemisKlasorAlanService islenmemisKlasorAlanService) {
		super(islenmemisKlasorAlanService);

		this.islenmemisKlasorAlanService = islenmemisKlasorAlanService;
	}

	@Override
	protected IslenmemisKlasorAlanDto createNewModel() {
		return new IslenmemisKlasorAlanDto();
	}

}

