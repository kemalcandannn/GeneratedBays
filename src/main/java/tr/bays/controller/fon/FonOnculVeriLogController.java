package tr.bays.controller.fon;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.fon.FonOnculVeriLogDto;
import tr.bays.service.fon.FonOnculVeriLogService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class FonOnculVeriLogController extends BaseCrudController<FonOnculVeriLogDto> {

	private FonOnculVeriLogService fonOnculVeriLogService;

	public FonOnculVeriLogController(FonOnculVeriLogService fonOnculVeriLogService) {
		super(fonOnculVeriLogService);

		this.fonOnculVeriLogService = fonOnculVeriLogService;
	}

	@Override
	protected FonOnculVeriLogDto createNewModel() {
		return new FonOnculVeriLogDto();
	}

}

