package tr.bays.controller.arsivSahibi;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.arsivSahibi.PersonelHesapBilgileriDto;
import tr.bays.service.arsivSahibi.PersonelHesapBilgileriService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonelHesapBilgileriController extends BaseCrudController<PersonelHesapBilgileriDto> {

	private PersonelHesapBilgileriService personelHesapBilgileriService;

	public PersonelHesapBilgileriController(PersonelHesapBilgileriService personelHesapBilgileriService) {
		super(personelHesapBilgileriService);

		this.personelHesapBilgileriService = personelHesapBilgileriService;
	}

	@Override
	protected PersonelHesapBilgileriDto createNewModel() {
		return new PersonelHesapBilgileriDto();
	}

}

