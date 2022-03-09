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

import tr.bays.entity.sozluk.SozlukMadde;
import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.depoTalep.DepoTalep;

@SuppressWarnings("serial")
@Entity(name = "DepoTalepTarihceLog")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "depo_talep_tarihce_log")
@Cache(region = "baysDepoTalepTarihceLogCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepoTalepTarihceLog extends VersionedEntity {

	@Column(name="tur", nullable = false)
	private int tur;
	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@Column(name="ip", nullable = false)
	private String ip;
	@Column(name="sebep")
	private String sebep;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iptal_red_sebebi_id")
	private SozlukMadde iptal_red_sebebi_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_sorumlusu_id")
	private Kullanici depo_sorumlusu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="birim_sorumlusu_id", nullable = false)
	private Kullanici birim_sorumlusu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_talep_id", nullable = false)
	private DepoTalep depo_talep_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tur = [" + tur + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Ip = [" + ip + "], ";
		str += "Sebep = [" + sebep + "], ";
		str += "Iptal Red Sebebi Id = [" + iptal_red_sebebi_id + "], ";
		str += "Depo Sorumlusu Id = [" + depo_sorumlusu_id + "], ";
		str += "Birim Sorumlusu Id = [" + birim_sorumlusu_id + "], ";
		str += "Depo Talep Id = [" + depo_talep_id + "], ";

		str += "}";

		return str;
	}

}

