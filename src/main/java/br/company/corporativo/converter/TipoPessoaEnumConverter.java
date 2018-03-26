package br.company.corporativo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.company.corporativo.enums.TipoPessoaEnum;

@FacesConverter(value = "tipoPessoaEnumConverter")
public class TipoPessoaEnumConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return TipoPessoaEnum.findByDescricao(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value != null ? ((TipoPessoaEnum) value).getDescricao() : "";
	}
}