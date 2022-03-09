package tr.bays.controller.loglama;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.loglama.ArsivSahibiPersonelOnayLogDto;
import tr.bays.service.loglama.ArsivSahibiPersonelOnayLogService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivSahibiPersonelOnayLogController extends BaseCrudController<ArsivSahibiPersonelOnayLogDto> {

	private ArsivSahibiPersonelOnayLogService arsivSahibiPersonelOnayLogService;

	public ArsivSahibiPersonelOnayLogController(ArsivSahibiPersonelOnayLogService arsivSahibiPersonelOnayLogService) {
		super(arsivSahibiPersonelOnayLogService);

		this.arsivSahibiPersonelOnayLogService = arsivSahibiPersonelOnayLogService;
	}

	@Override
	protected ArsivSahibiPersonelOnayLogDto createNewModel() {
		return new ArsivSahibiPersonelOnayLogDto();
	}

}

