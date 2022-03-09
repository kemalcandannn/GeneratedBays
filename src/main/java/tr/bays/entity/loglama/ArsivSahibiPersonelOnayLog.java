package tr.bays.entity.loglama;

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

import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.arsivSahibi.ArsivSahibiPersonel;

@SuppressWarnings("serial")
@Entity(name = "ArsivSahibiPersonelOnayLog")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "arsiv_sahibi_personel_onay_log")
@Cache(region = "baysArsivSahibiPersonelOnayLogCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArsivSahibiPersonelOnayLog extends VersionedEntity {

	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@Column(name="islem", nullable = false)
	private int islem;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="onaylayan_kullanici_id", nullable = false)
	private Kullanici onaylayan_kullanici_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="onaylanan_personel_id", nullable = false)
	private ArsivSahibiPersonel onaylanan_personel_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Islem = [" + islem + "], ";
		str += "Onaylayan Kullanici Id = [" + onaylayan_kullanici_id + "], ";
		str += "Onaylanan Personel Id = [" + onaylanan_personel_id + "], ";

		str += "}";

		return str;
	}

}

