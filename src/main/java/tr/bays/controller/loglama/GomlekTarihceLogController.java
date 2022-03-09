package tr.bays.controller.loglama;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.loglama.GomlekTarihceLogDto;
import tr.bays.service.loglama.GomlekTarihceLogService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekTarihceLogController extends BaseCrudController<GomlekTarihceLogDto> {

	private GomlekTarihceLogService gomlekTarihceLogService;

	public GomlekTarihceLogController(GomlekTarihceLogService gomlekTarihceLogService) {
		super(gomlekTarihceLogService);

		this.gomlekTarihceLogService = gomlekTarihceLogService;
	}

	@Override
	protected GomlekTarihceLogDto createNewModel() {
		return new GomlekTarihceLogDto();
	}

}

