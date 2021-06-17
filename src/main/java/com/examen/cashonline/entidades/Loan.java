package com.examen.cashonline.entidades;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity(name = "loan")
public class Loan {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private BigDecimal total;

	@ManyToOne//(cascade = CascadeType.DETACH)
	@JoinColumn(name="userId")
	private User userId;

	@JsonProperty("userId")
	public Long userId() {
		return userId.getId();
	}

	public Loan() {
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
