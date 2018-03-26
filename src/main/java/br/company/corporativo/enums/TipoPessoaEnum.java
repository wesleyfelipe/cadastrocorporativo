package br.company.corporativo.enums;

public enum TipoPessoaEnum {
	
	F("Física"), J("Jurídica");
	
	private String descricao;
	
	private TipoPessoaEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPessoaEnum findByDescricao(String descricao) {
		for (TipoPessoaEnum tipoPessoa : TipoPessoaEnum.values()) {
			if(tipoPessoa.getDescricao().equals(descricao))
				return tipoPessoa;
		}
		return null;
	}
}
