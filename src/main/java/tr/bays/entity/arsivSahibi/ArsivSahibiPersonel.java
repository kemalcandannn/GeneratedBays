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

import tr.bays.entity.arsivSahibi.ArsivSahibi;

@SuppressWarnings("serial")
@Entity(name = "ArsivSahibiPersonel")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "arsiv_sahibi_personel")
@Cache(region = "baysArsivSahibiPersonelCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArsivSahibiPersonel extends VersionedEntity {

	@Column(name="ad_soyad", nullable = false)
	private String ad_soyad;
	@Column(name="eposta", nullable = false)
	private String eposta;
	@Column(name="kurum_tel_no")
	private String kurum_tel_no;
	@Column(name="cep_tel_no")
	private String cep_tel_no;
	@Column(name="gorev")
	private String gorev;
	@Column(name="aktif", nullable = false)
	private int aktif;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_sahibi_id", nullable = false)
	private ArsivSahibi arsiv_sahibi_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad Soyad = [" + ad_soyad + "], ";
		str += "Eposta = [" + eposta + "], ";
		str += "Kurum Tel No = [" + kurum_tel_no + "], ";
		str += "Cep Tel No = [" + cep_tel_no + "], ";
		str += "Gorev = [" + gorev + "], ";
		str += "Aktif = [" + aktif + "], ";
		str += "Arsiv Sahibi Id = [" + arsiv_sahibi_id + "], ";

		str += "}";

		return str;
	}

}

