package com.mrad.UniReport.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ocorrencias")
public class Ocorrencia implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String bloco;
	private String sala;
	
	private String problemas;
	private List<String> imagens = new ArrayList<>();
	private Boolean status;
	private String resolucao;
	private Instant criadoEm;
	private Instant atualizadoEm;
	
	@ManyToOne
	@JoinColumn(name = "atualizadoPor_id")
	private User atualizadoPor;
	
	
	public Ocorrencia() {}


	public Ocorrencia(Long id, String bloco, String sala, String problemas, List<String> imagens,
			Boolean status, String resolucao, Instant criadoEm, Instant atualizadoEm) {
		super();
		this.id = id;
		this.bloco = bloco;
		this.sala = sala;
		this.problemas = problemas;
		this.imagens = imagens;
		this.status = status;
		this.resolucao = resolucao;
		this.criadoEm = criadoEm;
		this.atualizadoEm = atualizadoEm;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBloco() {
		return bloco;
	}


	public void setBloco(String bloco) {
		this.bloco = bloco;
	}


	public String getSala() {
		return sala;
	}


	public void setSala(String sala) {
		this.sala = sala;
	}


	public String getProblemas() {
		return problemas;
	}


	public void setProblemas(String problemas) {
		this.problemas = problemas;
	}


	public List<String> getImagens() {
		return imagens;
	}


	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public String getResolucao() {
		return resolucao;
	}


	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}


	public Instant getCriadoEm() {
		return criadoEm;
	}


	public void setCriadoEm(Instant criadoEm) {
		this.criadoEm = criadoEm;
	}


	public Instant getAtualizadoEm() {
		return atualizadoEm;
	}


	public void setAtualizadoEm(Instant atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	
	public User getAtualizadoPor() {
		return atualizadoPor;
	}

	public void setAtualizadoPor(User atualizadoPor) {
		this.atualizadoPor = atualizadoPor;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ocorrencia other = (Ocorrencia) obj;
		return Objects.equals(id, other.id);
	}


	
	
	
	
	
	

}
