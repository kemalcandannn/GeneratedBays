package tr.bays.controller.teskilat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.teskilat.ArsivKurumuDto;
import tr.bays.service.teskilat.ArsivKurumuService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivKurumuController extends BaseCrudController<ArsivKurumuDto> {

	private ArsivKurumuService arsivKurumuService;

	public ArsivKurumuController(ArsivKurumuService arsivKurumuService) {
		super(arsivKurumuService);

		this.arsivKurumuService = arsivKurumuService;
	}

	@Override
	protected ArsivKurumuDto createNewModel() {
		return new ArsivKurumuDto();
	}

}

