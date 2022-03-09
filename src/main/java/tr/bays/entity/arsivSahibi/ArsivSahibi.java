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
import tr.bays.entity.arsivSahibi.ArsivSahibi;

@SuppressWarnings("serial")
@Entity(name = "ArsivSahibi")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "arsiv_sahibi")
@Cache(region = "baysArsivSahibiCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArsivSahibi extends VersionedEntity {

	@Column(name="ad", nullable = false)
	private String ad;
	@Column(name="tur", nullable = false)
	private int tur;
	@Column(name="aktif", nullable = false)
	private int aktif;
	@Column(name="ust_kurum_iliski", nullable = false)
	private int ust_kurum_iliski;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="oncul_versiyon_id")
	private ArsivSahibi oncul_versiyon_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ust_arsiv_sahibi_id")
	private ArsivSahibi ust_arsiv_sahibi_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Tur = [" + tur + "], ";
		str += "Aktif = [" + aktif + "], ";
		str += "Ust Kurum Iliski = [" + ust_kurum_iliski + "], ";
		str += "Oncul Versiyon Id = [" + oncul_versiyon_id + "], ";
		str += "Ust Arsiv Sahibi Id = [" + ust_arsiv_sahibi_id + "], ";

		str += "}";

		return str;
	}

}

