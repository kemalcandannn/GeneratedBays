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

import tr.bays.entity.genel.Rol;
import tr.bays.entity.teskilat.Kullanici;

@SuppressWarnings("serial")
@Entity(name = "KullaniciRol")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "kullanici_rol")
@Cache(region = "baysKullaniciRolCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class KullaniciRol extends VersionedEntity {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rol_id", nullable = false)
	private Rol rol_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable = false)
	private Kullanici kullanici_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Rol Id = [" + rol_id + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";

		str += "}";

		return str;
	}

}

