package br.company.corporativo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "PESSOA_FISICA")
public class PessoaFisica extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NOME")
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;

	@OneToMany(mappedBy = "dependenteDe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<DependenciaPessoa> dependentes = new ArrayList<>();

	@OneToMany(mappedBy = "dependente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DependenciaPessoa> dependencias;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<DependenciaPessoa> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<DependenciaPessoa> dependentes) {
		this.dependentes = dependentes;
	}

	public List<DependenciaPessoa> getDependencias() {
		return dependencias;
	}

	public void setDependencias(List<DependenciaPessoa> dependencias) {
		this.dependencias = dependencias;
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
