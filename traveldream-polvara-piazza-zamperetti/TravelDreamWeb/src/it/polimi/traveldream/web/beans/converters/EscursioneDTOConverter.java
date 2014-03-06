package it.polimi.traveldream.web.beans.converters;

import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=EscursioneDTO.class,value="escursioneConverter")
public class EscursioneDTOConverter implements Converter{

	@Override
	public EscursioneDTO getAsObject(FacesContext context, UIComponent component, String value) {
		//System.out.print("EscursioneDTOConverter.getAsObject("+value+")");
		
		if(value == null) return null;
		
		return EscursioneDTO.fromString(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((EscursioneDTO)value).toString();
	}
	
}
