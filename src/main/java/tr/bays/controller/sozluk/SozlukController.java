package tr.bays.controller.sozluk;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.sozluk.SozlukDto;
import tr.bays.service.sozluk.SozlukService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class SozlukController extends BaseCrudController<SozlukDto> {

	private SozlukService sozlukService;

	public SozlukController(SozlukService sozlukService) {
		super(sozlukService);

		this.sozlukService = sozlukService;
	}

	@Override
	protected SozlukDto createNewModel() {
		return new SozlukDto();
	}

}

