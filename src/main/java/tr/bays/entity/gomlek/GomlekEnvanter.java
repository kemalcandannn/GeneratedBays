package tr.bays.entity.gomlek;

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

import tr.bays.entity.gomlek.Gomlek;

@SuppressWarnings("serial")
@Entity(name = "GomlekEnvanter")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "gomlek_envanter")
@Cache(region = "baysGomlekEnvanterCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class GomlekEnvanter extends VersionedEntity {

	@Column(name="tespit_edilmis_belge_sayisi", nullable = false)
	private int tespit_edilmis_belge_sayisi;
	@Column(name="tespit_edilmis_sayfa_sayisi", nullable = false)
	private int tespit_edilmis_sayfa_sayisi;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gomlek_id", nullable = false)
	private Gomlek gomlek_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tespit Edilmis Belge Sayisi = [" + tespit_edilmis_belge_sayisi + "], ";
		str += "Tespit Edilmis Sayfa Sayisi = [" + tespit_edilmis_sayfa_sayisi + "], ";
		str += "Gomlek Id = [" + gomlek_id + "], ";

		str += "}";

		return str;
	}

}

