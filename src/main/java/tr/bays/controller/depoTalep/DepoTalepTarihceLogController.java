package tr.bays.controller.depoTalep;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depoTalep.DepoTalepTarihceLogDto;
import tr.bays.service.depoTalep.DepoTalepTarihceLogService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoTalepTarihceLogController extends BaseCrudController<DepoTalepTarihceLogDto> {

	private DepoTalepTarihceLogService depoTalepTarihceLogService;

	public DepoTalepTarihceLogController(DepoTalepTarihceLogService depoTalepTarihceLogService) {
		super(depoTalepTarihceLogService);

		this.depoTalepTarihceLogService = depoTalepTarihceLogService;
	}

	@Override
	protected DepoTalepTarihceLogDto createNewModel() {
		return new DepoTalepTarihceLogDto();
	}

}

