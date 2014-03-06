package it.polimi.traveldream.web.beans.converters;

import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=VoloDTO.class,value="voloConverter")
public class VoloDTOConverter implements Converter{

	@Override
	public VoloDTO getAsObject(FacesContext context, UIComponent component, String value) {
		//System.out.print("VoloDTOConverter.getAsObject("+value+")");
		
		if(value == null) return null;
		
		return VoloDTO.fromString(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((VoloDTO)value).toString();
	}
	
}
