package pl.mm.documentArchive.model.db;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "transaction_info")
public abstract class TransactionInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private long id;
	@Column(name = "Uuid", unique = true, length = 36, nullable = false)
	private String uuid;
	@Column(name = "CreatedDateTime", nullable = false, updatable = false)
	private Timestamp createdDateTime;
	@Column(name = "ModificationDateTime")
	private Timestamp modificationDateTime;

}
