package tr.bays.controller.loglama;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.loglama.DefterTarihceLogDto;
import tr.bays.service.loglama.DefterTarihceLogService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterTarihceLogController extends BaseCrudController<DefterTarihceLogDto> {

	private DefterTarihceLogService defterTarihceLogService;

	public DefterTarihceLogController(DefterTarihceLogService defterTarihceLogService) {
		super(defterTarihceLogService);

		this.defterTarihceLogService = defterTarihceLogService;
	}

	@Override
	protected DefterTarihceLogDto createNewModel() {
		return new DefterTarihceLogDto();
	}

}

