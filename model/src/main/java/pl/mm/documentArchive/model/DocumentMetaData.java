package pl.mm.documentArchive.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "document_meta_data")
public class DocumentMetaData extends TransactionInfo {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DocumentId", nullable = false)
	private Document document;
	@Column(name = "Name", nullable = false, length = 500)
	private String name;
	@Column(name = "Value", nullable = false, length = 500)
	private String value;

}
