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

import tr.bays.entity.klasor.Klasor;
import tr.bays.entity.defter.Defter;
import tr.bays.entity.arsivMateryali.ArsivMateryali;
import tr.bays.entity.arsivMateryali.IslenmemisKlasor;
import tr.bays.entity.depoMuhafaza.TopluTasima;

@SuppressWarnings("serial")
@Entity(name = "TopluTasimaMalzeme")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "toplu_tasima_malzeme")
@Cache(region = "baysTopluTasimaMalzemeCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class TopluTasimaMalzeme extends VersionedEntity {

	@Column(name="eski_konum", nullable = false)
	private String eski_konum;
	@Column(name="yeni_konum", nullable = false)
	private String yeni_konum;
	@Column(name="tur", nullable = false)
	private int tur;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="klasor_id")
	private Klasor klasor_id;
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
	@JoinColumn(name="toplu_tasima_id", nullable = false)
	private TopluTasima toplu_tasima_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Eski Konum = [" + eski_konum + "], ";
		str += "Yeni Konum = [" + yeni_konum + "], ";
		str += "Tur = [" + tur + "], ";
		str += "Klasor Id = [" + klasor_id + "], ";
		str += "Defter Id = [" + defter_id + "], ";
		str += "Arsiv Materyali Id = [" + arsiv_materyali_id + "], ";
		str += "Islenmemis Klasor Id = [" + islenmemis_klasor_id + "], ";
		str += "Toplu Tasima Id = [" + toplu_tasima_id + "], ";

		str += "}";

		return str;
	}

}

