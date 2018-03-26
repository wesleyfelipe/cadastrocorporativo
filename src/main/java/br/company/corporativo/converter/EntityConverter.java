package br.company.corporativo.converter;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.company.corporativo.entity.AbstractEntity;

@FacesConverter(forClass = AbstractEntity.class, value = "entityConverter")
public class EntityConverter implements Converter {

	private static final String KEY = EntityConverter.class.getCanonicalName();

	private static final String EMPTY = "";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Map<String, Object> getViewMap(FacesContext context) {
		Map<String, Object> viewMap = context.getViewRoot().getViewMap();
		Map<String, Object> idMap = (Map) viewMap.get(KEY);

		if (idMap == null) {
			idMap = new HashMap<>();
			viewMap.put(KEY, idMap);
		}

		return idMap;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent c, String value) {
		if (value.isEmpty()) {
			return null;
		}

		return getViewMap(context).get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent c, Object value) {
		if ((value == null) || ("".equals(value))) {
			return EMPTY;
		}

		AbstractEntity ed = (AbstractEntity) value;

		String id = Integer.toString(ed.hashCode());
		getViewMap(context).put(id, ed);

		return id;
	}
}