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

import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.depo.Depo;

@SuppressWarnings("serial")
@Entity(name = "DepoKullanici")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "depo_kullanici")
@Cache(region = "baysDepoKullaniciCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepoKullanici extends VersionedEntity {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable = false)
	private Kullanici kullanici_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_id", nullable = false)
	private Depo depo_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";
		str += "Depo Id = [" + depo_id + "], ";

		str += "}";

		return str;
	}

}

