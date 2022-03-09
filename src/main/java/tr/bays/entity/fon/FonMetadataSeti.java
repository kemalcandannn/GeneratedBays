package tr.bays.entity.fon;

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

import tr.bays.entity.metadata.MetadataSeti;
import tr.bays.entity.fon.Fon;

@SuppressWarnings("serial")
@Entity(name = "FonMetadataSeti")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "fon_metadata_seti")
@Cache(region = "baysFonMetadataSetiCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class FonMetadataSeti extends VersionedEntity {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="metadata_seti_id", nullable = false)
	private MetadataSeti metadata_seti_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fon_id", nullable = false)
	private Fon fon_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Metadata Seti Id = [" + metadata_seti_id + "], ";
		str += "Fon Id = [" + fon_id + "], ";

		str += "}";

		return str;
	}

}

