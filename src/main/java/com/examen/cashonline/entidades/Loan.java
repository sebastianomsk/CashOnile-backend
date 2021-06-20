package com.examen.cashonline.entidades;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "loan")
public class Loan {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private BigDecimal total;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userId")
	private User user;

	
	public Loan() {
	}

	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("userId")
	public Long userId() {
		return user.getId();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
