package it.polimi.traveldream.ejb.usermanagement;

import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import javax.ejb.Local;

@Local
public interface ImpiegatoMgr {
	void creaComponente(HotelDTO hotelDTO);
	void creaComponente(EscursioneDTO escursioneDTO);
	void creaComponente(VoloDTO voloDTO);
	
	void invalidaComponente(HotelDTO hotelDTO);
	void invalidaComponente(EscursioneDTO escursioneDTO);
	void invalidaComponente(VoloDTO voloDTO);
	
	void creaPacchettoPredefinito(PacchettoPredefinitoDTO pacchettoPredefinitoDTO);
	void aggiornaPacchettoPredefinito(PacchettoPredefinitoDTO pacchettoPredefinitoDTO);
	void rimuoviPacchettoPredefinito(
			PacchettoPredefinitoDTO pacchettoPredefinitoDTO);
}
