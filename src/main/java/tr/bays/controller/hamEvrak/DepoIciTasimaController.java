package tr.bays.controller.hamEvrak;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.hamEvrak.DepoIciTasimaDto;
import tr.bays.service.hamEvrak.DepoIciTasimaService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoIciTasimaController extends BaseCrudController<DepoIciTasimaDto> {

	private DepoIciTasimaService depoIciTasimaService;

	public DepoIciTasimaController(DepoIciTasimaService depoIciTasimaService) {
		super(depoIciTasimaService);

		this.depoIciTasimaService = depoIciTasimaService;
	}

	@Override
	protected DepoIciTasimaDto createNewModel() {
		return new DepoIciTasimaDto();
	}

}

