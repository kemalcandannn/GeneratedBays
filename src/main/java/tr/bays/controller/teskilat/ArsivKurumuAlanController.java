package tr.bays.controller.teskilat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.teskilat.ArsivKurumuAlanDto;
import tr.bays.service.teskilat.ArsivKurumuAlanService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivKurumuAlanController extends BaseCrudController<ArsivKurumuAlanDto> {

	private ArsivKurumuAlanService arsivKurumuAlanService;

	public ArsivKurumuAlanController(ArsivKurumuAlanService arsivKurumuAlanService) {
		super(arsivKurumuAlanService);

		this.arsivKurumuAlanService = arsivKurumuAlanService;
	}

	@Override
	protected ArsivKurumuAlanDto createNewModel() {
		return new ArsivKurumuAlanDto();
	}

}

