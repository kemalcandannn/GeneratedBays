package tr.bays.controller.loglama;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.loglama.DefterSayfaTarihceLogDto;
import tr.bays.service.loglama.DefterSayfaTarihceLogService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterSayfaTarihceLogController extends BaseCrudController<DefterSayfaTarihceLogDto> {

	private DefterSayfaTarihceLogService defterSayfaTarihceLogService;

	public DefterSayfaTarihceLogController(DefterSayfaTarihceLogService defterSayfaTarihceLogService) {
		super(defterSayfaTarihceLogService);

		this.defterSayfaTarihceLogService = defterSayfaTarihceLogService;
	}

	@Override
	protected DefterSayfaTarihceLogDto createNewModel() {
		return new DefterSayfaTarihceLogDto();
	}

}

