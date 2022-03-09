package tr.bays.controller.depoMuhafaza;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depoMuhafaza.TopluTasimaMalzemeDto;
import tr.bays.service.depoMuhafaza.TopluTasimaMalzemeService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class TopluTasimaMalzemeController extends BaseCrudController<TopluTasimaMalzemeDto> {

	private TopluTasimaMalzemeService topluTasimaMalzemeService;

	public TopluTasimaMalzemeController(TopluTasimaMalzemeService topluTasimaMalzemeService) {
		super(topluTasimaMalzemeService);

		this.topluTasimaMalzemeService = topluTasimaMalzemeService;
	}

	@Override
	protected TopluTasimaMalzemeDto createNewModel() {
		return new TopluTasimaMalzemeDto();
	}

}

