package tr.bays.entity.fon;

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

import tr.bays.entity.teskilat.ArsivKurumu;
import tr.bays.entity.arsivSahibi.ArsivSahibi;
import tr.bays.entity.fon.Fon;

@SuppressWarnings("serial")
@Entity(name = "Fon")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "fon")
@Cache(region = "baysFonCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Fon extends VersionedEntity {

	@Column(name="ad", nullable = false)
	private String ad;
	@Column(name="gizlilik", nullable = false)
	private int gizlilik;
	@Column(name="gomlek_defter_tarih_tur", nullable = false)
	private int gomlek_defter_tarih_tur;
	@Column(name="tarih")
	private Date tarih;
	@Column(name="onay_tarihi")
	private Date onay_tarihi;
	@Column(name="tur", nullable = false)
	private int tur;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_kurumu_id", nullable = false)
	private ArsivKurumu arsiv_kurumu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_sahibi_id")
	private ArsivSahibi arsiv_sahibi_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ust_fon_id")
	private Fon ust_fon_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Gizlilik = [" + gizlilik + "], ";
		str += "Gomlek Defter Tarih Tur = [" + gomlek_defter_tarih_tur + "], ";
		str += "Tarih = [" + tarih + "], ";
		str += "Onay Tarihi = [" + onay_tarihi + "], ";
		str += "Tur = [" + tur + "], ";
		str += "Arsiv Kurumu Id = [" + arsiv_kurumu_id + "], ";
		str += "Arsiv Sahibi Id = [" + arsiv_sahibi_id + "], ";
		str += "Ust Fon Id = [" + ust_fon_id + "], ";

		str += "}";

		return str;
	}

}

