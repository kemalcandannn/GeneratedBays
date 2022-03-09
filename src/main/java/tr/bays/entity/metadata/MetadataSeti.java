package tr.bays.entity.metadata;

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
@Entity(name = "MetadataSeti")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "metadata_seti")
@Cache(region = "baysMetadataSetiCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class MetadataSeti extends VersionedEntity {

	@Column(name="ad", nullable = false)
	private String ad;
	@Column(name="tur", nullable = false)
	private int tur;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Tur = [" + tur + "], ";

		str += "}";

		return str;
	}

}

