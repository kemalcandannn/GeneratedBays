package tr.bays.entity.calismaGrubu;

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
import tr.bays.entity.arsivSahibi.ArsivSahibiPersonel;
import tr.bays.entity.calismaGrubu.CalismaGrubu;

@SuppressWarnings("serial")
@Entity(name = "CalismaGrubuUye")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "calisma_grubu_uye")
@Cache(region = "baysCalismaGrubuUyeCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class CalismaGrubuUye extends VersionedEntity {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id")
	private Kullanici kullanici_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_sahibi_personel_id")
	private ArsivSahibiPersonel arsiv_sahibi_personel_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="calisma_grubu_id", nullable = false)
	private CalismaGrubu calisma_grubu_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";
		str += "Arsiv Sahibi Personel Id = [" + arsiv_sahibi_personel_id + "], ";
		str += "Calisma Grubu Id = [" + calisma_grubu_id + "], ";

		str += "}";

		return str;
	}

}

