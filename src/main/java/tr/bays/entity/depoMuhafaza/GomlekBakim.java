package tr.bays.entity.depoMuhafaza;

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

@SuppressWarnings("serial")
@Entity(name = "GomlekBakim")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "gomlek_bakim")
@Cache(region = "baysGomlekBakimCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class GomlekBakim extends VersionedEntity {

	@Column(name="talep_tarih_saat", nullable = false)
	private Date talep_tarih_saat;
	@Column(name="ip", nullable = false)
	private String ip;
	@Column(name="durum", nullable = false)
	private int durum;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable = false)
	private Kullanici kullanici_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Talep Tarih Saat = [" + talep_tarih_saat + "], ";
		str += "Ip = [" + ip + "], ";
		str += "Durum = [" + durum + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";

		str += "}";

		return str;
	}

}

