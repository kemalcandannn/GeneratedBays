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

import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.fon.Fon;

@SuppressWarnings("serial")
@Entity(name = "FonOnculVeriLog")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "fon_oncul_veri_log")
@Cache(region = "baysFonOnculVeriLogCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class FonOnculVeriLog extends VersionedEntity {

	@Column(name="oncul_veri", nullable = false)
	private String oncul_veri;
	@Column(name="ip", nullable = false)
	private String ip;
	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable = false)
	private Kullanici kullanici_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fon_id", nullable = false)
	private Fon fon_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Oncul Veri = [" + oncul_veri + "], ";
		str += "Ip = [" + ip + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";
		str += "Fon Id = [" + fon_id + "], ";

		str += "}";

		return str;
	}

}

