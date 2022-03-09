package tr.bays.entity.arsivSahibi;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.entity.VersionedEntity;

import tr.bays.entity.arsivSahibi.ArsivSahibiPersonel;

@SuppressWarnings("serial")
@Entity(name = "PersonelHesapBilgileri")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "personel_hesap_bilgileri")
@Cache(region = "baysPersonelHesapBilgileriCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class PersonelHesapBilgileri extends VersionedEntity {

	@Column(name="kullanici_adi", nullable = false, unique = true)
	private String kullanici_adi;
	@Column(name="parola", nullable = false)
	private String parola;
	@Column(name="aktif", nullable = false)
	private int aktif;
	@Column(name="onay_durumu", nullable = false)
	private int onay_durumu;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_sahibi_personel_id", nullable = false)
	private ArsivSahibiPersonel arsiv_sahibi_personel_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Kullanici Adi = [" + kullanici_adi + "], ";
		str += "Parola = [" + parola + "], ";
		str += "Aktif = [" + aktif + "], ";
		str += "Onay Durumu = [" + onay_durumu + "], ";
		str += "Arsiv Sahibi Personel Id = [" + arsiv_sahibi_personel_id + "], ";

		str += "}";

		return str;
	}

}

