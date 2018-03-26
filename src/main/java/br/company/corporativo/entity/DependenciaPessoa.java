package br.company.corporativo.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "DEPENDENCIA_PESSOA")
@NamedQuery(name="DependenciaPessoa.findAllByDependenteDe", query="SELECT d FROM DependenciaPessoa d where d.dependenteDe = :dependenteDe ORDER BY d.dependente.nome")
public class DependenciaPessoa extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DEPENDENCIA_PESSOA_GEN", sequenceName = "DEPENDENCIA_PESSOA_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEPENDENCIA_PESSOA_GEN")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_DEPENDENCIA")
	private TipoDependencia tipoDependencia;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "ID_DEPENDENTE")
	private PessoaFisica dependente;

	@ManyToOne
	@JoinColumn(name = "ID_DEPENDENTE_DE")
	private PessoaFisica dependenteDe;

	public TipoDependencia getTipoDependencia() {
		return tipoDependencia;
	}

	public void setTipoDependencia(TipoDependencia tipoDependencia) {
		this.tipoDependencia = tipoDependencia;
	}

	public PessoaFisica getDependente() {
		return dependente;
	}

	public void setDependente(PessoaFisica dependente) {
		this.dependente = dependente;
	}

	public PessoaFisica getDependenteDe() {
		return dependenteDe;
	}

	public void setDependenteDe(PessoaFisica dependenteDe) {
		this.dependenteDe = dependenteDe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
