package tr.bays.controller.fon;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.fon.FonMetadataSetiDto;
import tr.bays.service.fon.FonMetadataSetiService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class FonMetadataSetiController extends BaseCrudController<FonMetadataSetiDto> {

	private FonMetadataSetiService fonMetadataSetiService;

	public FonMetadataSetiController(FonMetadataSetiService fonMetadataSetiService) {
		super(fonMetadataSetiService);

		this.fonMetadataSetiService = fonMetadataSetiService;
	}

	@Override
	protected FonMetadataSetiDto createNewModel() {
		return new FonMetadataSetiDto();
	}

}

