package tr.bays.entity.klasor;

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

import tr.bays.entity.klasor.Klasor;

@SuppressWarnings("serial")
@Entity(name = "KlasorEnvanter")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "klasor_envanter")
@Cache(region = "baysKlasorEnvanterCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class KlasorEnvanter extends VersionedEntity {

	@Column(name="tespit_edilmis_alt_klasor_sayisi", nullable = false)
	private int tespit_edilmis_alt_klasor_sayisi;
	@Column(name="tespit_edilmis_gomlek_sayisi", nullable = false)
	private int tespit_edilmis_gomlek_sayisi;
	@Column(name="kayitli_alt_klasor_sayisi", nullable = false)
	private int kayitli_alt_klasor_sayisi;
	@Column(name="kayitli_gomlek_sayisi", nullable = false)
	private int kayitli_gomlek_sayisi;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="klasor_id", nullable = false)
	private Klasor klasor_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tespit Edilmis Alt Klasor Sayisi = [" + tespit_edilmis_alt_klasor_sayisi + "], ";
		str += "Tespit Edilmis Gomlek Sayisi = [" + tespit_edilmis_gomlek_sayisi + "], ";
		str += "Kayitli Alt Klasor Sayisi = [" + kayitli_alt_klasor_sayisi + "], ";
		str += "Kayitli Gomlek Sayisi = [" + kayitli_gomlek_sayisi + "], ";
		str += "Klasor Id = [" + klasor_id + "], ";

		str += "}";

		return str;
	}

}

