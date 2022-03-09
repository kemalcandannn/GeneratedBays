package tr.bays.entity.depoTalep;

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
import tr.bays.entity.gomlek.Gomlek;
import tr.bays.entity.defter.Defter;
import tr.bays.entity.arsivMateryali.ArsivMateryali;
import tr.bays.entity.arsivMateryali.IslenmemisKlasor;
import tr.bays.entity.depoTalep.DepoTalep;

@SuppressWarnings("serial")
@Entity(name = "DepoTalepMalzeme")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "depo_talep_malzeme")
@Cache(region = "baysDepoTalepMalzemeCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepoTalepMalzeme extends VersionedEntity {

	@Column(name="tur", nullable = false)
	private int tur;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="klasor_id")
	private Klasor klasor_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="gomlek_id")
	private Gomlek gomlek_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="defter_id")
	private Defter defter_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_materyali_id")
	private ArsivMateryali arsiv_materyali_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="islenmemis_klasor_id")
	private IslenmemisKlasor islenmemis_klasor_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_talep_id", nullable = false)
	private DepoTalep depo_talep_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tur = [" + tur + "], ";
		str += "Klasor Id = [" + klasor_id + "], ";
		str += "Gomlek Id = [" + gomlek_id + "], ";
		str += "Defter Id = [" + defter_id + "], ";
		str += "Arsiv Materyali Id = [" + arsiv_materyali_id + "], ";
		str += "Islenmemis Klasor Id = [" + islenmemis_klasor_id + "], ";
		str += "Depo Talep Id = [" + depo_talep_id + "], ";

		str += "}";

		return str;
	}

}

