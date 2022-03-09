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
@Entity(name = "TopluTasima")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "toplu_tasima")
@Cache(region = "baysTopluTasimaCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class TopluTasima extends VersionedEntity {

	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@Column(name="ip", nullable = false)
	private String ip;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable = false)
	private Kullanici kullanici_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Ip = [" + ip + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";

		str += "}";

		return str;
	}

}

