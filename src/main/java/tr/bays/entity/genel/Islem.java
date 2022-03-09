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

import tr.bays.entity.genel.Islem;

@SuppressWarnings("serial")
@Entity(name = "Islem")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "islem")
@Cache(region = "baysIslemCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Islem extends VersionedEntity {

	@Column(name="ad", nullable = false, unique = true)
	private String ad;
	@Column(name="aciklama")
	private String aciklama;
	@Column(name="agacta_goster")
	private boolean agacta_goster;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ust_islem_id")
	private Islem ust_islem_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Aciklama = [" + aciklama + "], ";
		str += "Agacta Goster = [" + agacta_goster + "], ";
		str += "Ust Islem Id = [" + ust_islem_id + "], ";

		str += "}";

		return str;
	}

}

