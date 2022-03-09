package tr.bays.entity.depo;

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
import tr.bays.entity.depo.Depo;

@SuppressWarnings("serial")
@Entity(name = "Depo")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "depo")
@Cache(region = "baysDepoCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Depo extends VersionedEntity {

	@Column(name="ad", nullable = false)
	private String ad;
	@Column(name="kisaltma", nullable = false)
	private String kisaltma;
	@Column(name="aciklama")
	private String aciklama;
	@Column(name="aktif", nullable = false)
	private int aktif;
	@Column(name="kapasite", nullable = false)
	private double kapasite;
	@Column(name="calisma_gozu", nullable = false)
	private int calisma_gozu;
	@Column(name="tasnifsiz_malzeme_sayisi", nullable = false)
	private int tasnifsiz_malzeme_sayisi;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_kurumu_id", nullable = false)
	private ArsivKurumu arsiv_kurumu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ust_depo_id", nullable = false)
	private Depo ust_depo_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Kisaltma = [" + kisaltma + "], ";
		str += "Aciklama = [" + aciklama + "], ";
		str += "Aktif = [" + aktif + "], ";
		str += "Kapasite = [" + kapasite + "], ";
		str += "Calisma Gozu = [" + calisma_gozu + "], ";
		str += "Tasnifsiz Malzeme Sayisi = [" + tasnifsiz_malzeme_sayisi + "], ";
		str += "Arsiv Kurumu Id = [" + arsiv_kurumu_id + "], ";
		str += "Ust Depo Id = [" + ust_depo_id + "], ";

		str += "}";

		return str;
	}

}

