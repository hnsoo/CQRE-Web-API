package sch.cqre.api.model.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supply", schema = "main")
public class SupplyEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "supply_id")
	private int supplyId;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "amount")
	private Byte amount;

	public int getSupplyId() {
		return supplyId;
	}

	public void setSupplyId(int supplyId) {
		this.supplyId = supplyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getAmount() {
		return amount;
	}

	public void setAmount(Byte amount) {
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
