package br.company.corporativo.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.company.corporativo.enums.TipoPessoaEnum;

@Converter
public class TipoPessoaEnumJPAConverter implements AttributeConverter<TipoPessoaEnum, String> {

	@Override
	public String convertToDatabaseColumn(TipoPessoaEnum attribute) {
		return attribute.name();
	}

	@Override
	public TipoPessoaEnum convertToEntityAttribute(String dbData) {
		return TipoPessoaEnum.valueOf(dbData);
	}
}