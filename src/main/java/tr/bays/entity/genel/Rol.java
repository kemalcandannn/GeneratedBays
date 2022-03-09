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


@SuppressWarnings("serial")
@Entity(name = "Rol")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "rol")
@Cache(region = "baysRolCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rol extends VersionedEntity {

	@Column(name="ad")
	private String ad;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";

		str += "}";

		return str;
	}

}

