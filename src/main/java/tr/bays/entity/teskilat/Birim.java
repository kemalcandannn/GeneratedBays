package tr.bays.entity.teskilat;

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

import tr.bays.entity.teskilat.Birim;
import tr.bays.entity.teskilat.ArsivKurumu;
import tr.bays.entity.sozluk.SozlukMadde;
import tr.bays.entity.teskilat.Kullanici;

@SuppressWarnings("serial")
@Entity(name = "Birim")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "birim")
@Cache(region = "baysBirimCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Birim extends VersionedEntity {

	@Column(name="ad", nullable = false)
	private String ad;
	@Column(name="aktif", nullable = false)
	private int aktif;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ust_birim_id")
	private Birim ust_birim_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_kurumu_id")
	private ArsivKurumu arsiv_kurumu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="birim_turu_sozluk_satir_id", nullable = false)
	private SozlukMadde birim_turu_sozluk_satir_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="yonetici_id")
	private Kullanici yonetici_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Aktif = [" + aktif + "], ";
		str += "Ust Birim Id = [" + ust_birim_id + "], ";
		str += "Arsiv Kurumu Id = [" + arsiv_kurumu_id + "], ";
		str += "Birim Turu Sozluk Satir Id = [" + birim_turu_sozluk_satir_id + "], ";
		str += "Yonetici Id = [" + yonetici_id + "], ";

		str += "}";

		return str;
	}

}

