package tr.bays.controller.sozluk;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.sozluk.SozlukMaddeDto;
import tr.bays.service.sozluk.SozlukMaddeService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class SozlukMaddeController extends BaseCrudController<SozlukMaddeDto> {

	private SozlukMaddeService sozlukMaddeService;

	public SozlukMaddeController(SozlukMaddeService sozlukMaddeService) {
		super(sozlukMaddeService);

		this.sozlukMaddeService = sozlukMaddeService;
	}

	@Override
	protected SozlukMaddeDto createNewModel() {
		return new SozlukMaddeDto();
	}

}

