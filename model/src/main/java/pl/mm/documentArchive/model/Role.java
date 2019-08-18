package pl.mm.documentArchive.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "role")
public class Role extends TransactionInfo {

	@Column(name = "RoleName", length = 10, nullable = false, unique = true)
	private String roleName;

}
