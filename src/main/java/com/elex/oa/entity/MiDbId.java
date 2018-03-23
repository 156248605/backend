package com.elex.oa.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Table(name="mi_db_id")
public class MiDbId {
	@Id
	@Column(
			name = "ID_"
	)
	private Integer id;
	@Column(
			name = "START_"
	)
	private BigDecimal start;
	@Column(
			name = "MAX_"
	)
	private BigDecimal max;
	@Column(
			name = "MAC_NAME_"
	)
	private String macName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getStart() {
		return start;
	}

	public void setStart(BigDecimal start) {
		this.start = start;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}
}




