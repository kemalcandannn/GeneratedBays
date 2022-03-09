package tr.bays.controller.loglama;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.loglama.KlasorTarihceLogDto;
import tr.bays.service.loglama.KlasorTarihceLogService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class KlasorTarihceLogController extends BaseCrudController<KlasorTarihceLogDto> {

	private KlasorTarihceLogService klasorTarihceLogService;

	public KlasorTarihceLogController(KlasorTarihceLogService klasorTarihceLogService) {
		super(klasorTarihceLogService);

		this.klasorTarihceLogService = klasorTarihceLogService;
	}

	@Override
	protected KlasorTarihceLogDto createNewModel() {
		return new KlasorTarihceLogDto();
	}

}

