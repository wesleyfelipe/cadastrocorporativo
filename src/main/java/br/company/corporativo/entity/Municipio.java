package br.company.corporativo.entity;

import java.io.Serializable;

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
@Table(name = "MUNICIPIO")
@NamedQuery(name="Municipio.findAllByUf", query="SELECT m FROM Municipio m where m.unidadeFederativa = :uf ORDER BY m.nome") 
public class Municipio extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "MUNICIPIO_GEN", sequenceName = "MUNICIPIO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MUNICIPIO_GEN")
	private Long id;

	private String nome;

	@ManyToOne
	@JoinColumn(name = "ID_UNIDADE_FEDERATIVA")
	private UnidadeFederativa unidadeFederativa;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UnidadeFederativa getUnidadeFederativa() {
		return unidadeFederativa;
	}
	
	public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
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
