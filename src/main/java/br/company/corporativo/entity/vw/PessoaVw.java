package br.company.corporativo.entity.vw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import br.company.corporativo.converter.TipoPessoaEnumJPAConverter;
import br.company.corporativo.entity.AbstractEntity;
import br.company.corporativo.entity.DependenciaPessoa;
import br.company.corporativo.entity.Municipio;
import br.company.corporativo.enums.TipoPessoaEnum;

@Entity
@Table(name = "PESSOA_VW")
public class PessoaVw extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_MUNICIPIO")
	private Municipio municipio;

	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;
	
	@Convert(converter = TipoPessoaEnumJPAConverter.class)
	@Column(name = "TIPO_PESSOA")
	private TipoPessoaEnum tipoPessoa;
	
	@Transient
	private List<DependenciaPessoa> dependentes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

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
	
	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}
	
	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	public Boolean isPessoaFisica() {
		return TipoPessoaEnum.F.equals(this.tipoPessoa);
	}
	
	public List<DependenciaPessoa> getDependentes() {
		return dependentes;
	}
	
	public void setDependentes(List<DependenciaPessoa> dependentes) {
		this.dependentes = dependentes;
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
