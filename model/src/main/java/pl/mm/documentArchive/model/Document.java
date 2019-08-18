package pl.mm.documentArchive.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "document")
public class Document extends TransactionInfo {

	@Column(name = "DocumentName", length = 500, nullable = false)
	private String documentName;
	@Column(name = "Extension", length = 10, nullable = false)
	private String extension;
	@Lob
	@Column(name = "DocumentBlob", nullable = false)
	private byte[] documentBlob;
	@ManyToOne
	@JoinColumn(name = "DocumentOwnerId", nullable = false)
	private User documentOwner;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "document")
	private Collection<DocumentMetaData> documentMetaDataCollection;

}
