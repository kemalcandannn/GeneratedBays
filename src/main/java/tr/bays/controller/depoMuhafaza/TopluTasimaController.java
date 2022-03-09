package tr.bays.controller.depoMuhafaza;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.depoMuhafaza.TopluTasimaDto;
import tr.bays.service.depoMuhafaza.TopluTasimaService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class TopluTasimaController extends BaseCrudController<TopluTasimaDto> {

	private TopluTasimaService topluTasimaService;

	public TopluTasimaController(TopluTasimaService topluTasimaService) {
		super(topluTasimaService);

		this.topluTasimaService = topluTasimaService;
	}

	@Override
	protected TopluTasimaDto createNewModel() {
		return new TopluTasimaDto();
	}

}

