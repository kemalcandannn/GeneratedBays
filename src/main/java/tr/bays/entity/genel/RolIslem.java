package tr.bays.entity.genel;

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
import tr.bays.entity.genel.Islem;

@SuppressWarnings("serial")
@Entity(name = "RolIslem")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "rol_islem")
@Cache(region = "baysRolIslemCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class RolIslem extends VersionedEntity {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rol_id", nullable = false)
	private Rol rol_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="islem_id", nullable = false)
	private Islem islem_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Rol Id = [" + rol_id + "], ";
		str += "Islem Id = [" + islem_id + "], ";

		str += "}";

		return str;
	}

}

