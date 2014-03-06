package it.polimi.traveldream.web.beans.converters;

import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=HotelDTO.class,value="hotelConverter")
public class HotelDTOConverter implements Converter{

	@Override
	public HotelDTO getAsObject(FacesContext context, UIComponent component, String value) {
		//System.out.print("HotelDTOConverter.getAsObject("+value+")");
		
		if(value == null) return null;
		
		return HotelDTO.fromString(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((HotelDTO)value).toString();
	}
	
}
