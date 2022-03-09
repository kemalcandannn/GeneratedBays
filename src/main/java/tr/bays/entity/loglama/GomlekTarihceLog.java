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
import tr.bays.entity.gomlek.Gomlek;

@SuppressWarnings("serial")
@Entity(name = "GomlekTarihceLog")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "gomlek_tarihce_log")
@Cache(region = "baysGomlekTarihceLogCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class GomlekTarihceLog extends VersionedEntity {

	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@Column(name="ip", nullable = false)
	private String ip;
	@Column(name="veri", nullable = false)
	private String veri;
	@Column(name="durum", nullable = false)
	private int durum;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable = false)
	private Kullanici kullanici_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gomlek_id", nullable = false)
	private Gomlek gomlek_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Ip = [" + ip + "], ";
		str += "Veri = [" + veri + "], ";
		str += "Durum = [" + durum + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";
		str += "Gomlek Id = [" + gomlek_id + "], ";

		str += "}";

		return str;
	}

}

