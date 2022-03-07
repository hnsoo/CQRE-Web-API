package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Supply", schema = "main")
public class SupplyEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "supply_id")
	private Integer supplyId;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "amount")
	private Byte amount;

	@Builder
	public SupplyEntity(String name, Byte amount) {
		this.name = name;
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SupplyEntity that = (SupplyEntity)o;
		return supplyId == that.supplyId && Objects.equals(name, that.name) && Objects.equals(amount,
			that.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(supplyId, name, amount);
	}
}
