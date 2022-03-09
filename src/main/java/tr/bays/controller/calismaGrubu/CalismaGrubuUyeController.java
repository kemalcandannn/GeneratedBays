package tr.bays.controller.calismaGrubu;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.calismaGrubu.CalismaGrubuUyeDto;
import tr.bays.service.calismaGrubu.CalismaGrubuUyeService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class CalismaGrubuUyeController extends BaseCrudController<CalismaGrubuUyeDto> {

	private CalismaGrubuUyeService calismaGrubuUyeService;

	public CalismaGrubuUyeController(CalismaGrubuUyeService calismaGrubuUyeService) {
		super(calismaGrubuUyeService);

		this.calismaGrubuUyeService = calismaGrubuUyeService;
	}

	@Override
	protected CalismaGrubuUyeDto createNewModel() {
		return new CalismaGrubuUyeDto();
	}

}

