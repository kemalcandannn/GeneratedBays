package tr.bays.controller.store;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import tr.bays.BaysJsfController;
import util.enums.AktifEnum;
import util.enums.GizlilikEnum;
import util.enums.DefterSayfaTurEnum;
import util.enums.DepoTalepDurumEnum;
import util.enums.HamEvrakBirimYonEnum;
import util.enums.ArsivMateryaliTurEnum;
import util.enums.OnayEnum;
import util.enums.GomlekDefterTarihTurEnum;
import util.enums.GomlekBakimKlasorDurumEnum;
import util.enums.HamEvrakSaklanmaTuruEnum;
import util.enums.ArsivSahibiustKurumIliskiEnum;
import util.enums.DepoTalepMalzemeTurEnum;
import util.enums.CalismaGozuEnum;
import util.enums.CalismaGrubuDurumEnum;
import util.enums.CrudEnum;
import util.enums.MetadataSetiTurEnum;
import util.enums.DepoTaleptarihceLogTurEnum;
import util.enums.TopluTasimaMalzemeTurEnum;
import util.enums.ArsivSahibiTurEnum;
import util.enums.GomlekRestorasyonIhtiyaciEnum;
import util.enums.OnayRedEnum;
import util.enums.FonTurEnum;
import util.enums.GomlekBakimDurumEnum;
import util.enums.SozlukKullanimSekliEnum;

@BaysJsfController
public class StoreController {

	public List<SelectItem> getAktifStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (AktifEnum enumElement : AktifEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getCrudStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (CrudEnum enumElement : CrudEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getGizlilikStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (GizlilikEnum enumElement : GizlilikEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getDefterSayfaTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (DefterSayfaTurEnum enumElement : DefterSayfaTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getDepoTalepDurumStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (DepoTalepDurumEnum enumElement : DepoTalepDurumEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getHamEvrakBirimYonStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (HamEvrakBirimYonEnum enumElement : HamEvrakBirimYonEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getArsivMateryaliTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (ArsivMateryaliTurEnum enumElement : ArsivMateryaliTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getOnayStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (OnayEnum enumElement : OnayEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getGomlekDefterTarihTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (GomlekDefterTarihTurEnum enumElement : GomlekDefterTarihTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getGomlekBakimKlasorDurumStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (GomlekBakimKlasorDurumEnum enumElement : GomlekBakimKlasorDurumEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getHamEvrakSaklanmaTuruStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (HamEvrakSaklanmaTuruEnum enumElement : HamEvrakSaklanmaTuruEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getArsivSahibiustKurumIliskiStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (ArsivSahibiustKurumIliskiEnum enumElement : ArsivSahibiustKurumIliskiEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getDepoTalepMalzemeTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (DepoTalepMalzemeTurEnum enumElement : DepoTalepMalzemeTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getCalismaGozuStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (CalismaGozuEnum enumElement : CalismaGozuEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getCalismaGrubuDurumStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (CalismaGrubuDurumEnum enumElement : CalismaGrubuDurumEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getMetadataSetiTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (MetadataSetiTurEnum enumElement : MetadataSetiTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getDepoTaleptarihceLogTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (DepoTaleptarihceLogTurEnum enumElement : DepoTaleptarihceLogTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getTopluTasimaMalzemeTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (TopluTasimaMalzemeTurEnum enumElement : TopluTasimaMalzemeTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getArsivSahibiTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (ArsivSahibiTurEnum enumElement : ArsivSahibiTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getGomlekRestorasyonIhtiyaciStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (GomlekRestorasyonIhtiyaciEnum enumElement : GomlekRestorasyonIhtiyaciEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getOnayRedStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (OnayRedEnum enumElement : OnayRedEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getFonTurStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (FonTurEnum enumElement : FonTurEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getGomlekBakimDurumStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (GomlekBakimDurumEnum enumElement : GomlekBakimDurumEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public List<SelectItem> getSozlukKullanimSekliStoreStore() {
		List<SelectItem> store = new ArrayList<SelectItem>();
		for (SozlukKullanimSekliEnum enumElement : SozlukKullanimSekliEnum.values()) {
			store.add(new SelectItem(enumElement.getValue(), enumElement.getLabel()));
		}

		return store;
	}

	public String getLabel(List<SelectItem> store, int value) {
		String label = "";
		try {
			for (SelectItem selectItem : store) {
				if(((Integer) selectItem.getValue()) == value) {
					label = selectItem.getLabel();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return label;
	}

}

