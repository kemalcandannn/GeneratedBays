package tr.bays.controller.metadata;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.metadata.MetadataSetiDto;
import tr.bays.service.metadata.MetadataSetiService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class MetadataSetiController extends BaseCrudController<MetadataSetiDto> {

	private MetadataSetiService metadataSetiService;

	public MetadataSetiController(MetadataSetiService metadataSetiService) {
		super(metadataSetiService);

		this.metadataSetiService = metadataSetiService;
	}

	@Override
	protected MetadataSetiDto createNewModel() {
		return new MetadataSetiDto();
	}

}

