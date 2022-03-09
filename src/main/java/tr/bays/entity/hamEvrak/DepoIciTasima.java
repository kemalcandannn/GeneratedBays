package tr.bays.entity.hamEvrak;

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
import tr.bays.entity.hamEvrak.HamEvrak;
import tr.bays.entity.hamEvrak.HamEvrak;

@SuppressWarnings("serial")
@Entity(name = "DepoIciTasima")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "depo_ici_tasima")
@Cache(region = "baysDepoIciTasimaCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepoIciTasima extends VersionedEntity {

	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@Column(name="adet", nullable = false)
	private int adet;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable = false)
	private Kullanici kullanici_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kaynak_id", nullable = false)
	private HamEvrak kaynak_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hedef_id", nullable = false)
	private HamEvrak hedef_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Adet = [" + adet + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";
		str += "Kaynak Id = [" + kaynak_id + "], ";
		str += "Hedef Id = [" + hedef_id + "], ";

		str += "}";

		return str;
	}

}

