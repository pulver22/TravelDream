package it.polimi.traveldream.ejb.usermanagement;

import java.util.List;

import it.polimi.traveldream.ejb.usermanagement.dto.EscursionePersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelPersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.UserDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloPersDTO;

import javax.ejb.Local;

@Local
public interface ClienteMgr {
	

	void aggiungiAlCarrello(PacchettoPredefinitoDTO pacchettoPredefinitoDTO, UserDTO cliente);

	List<PacchettoPersonalizzatoDTO> getAllPacchetti(UserDTO cliente);

	PacchettoPersonalizzatoDTO findPacchettoAmico(int id);

	void aggiornaPacchettoPersonalizzato(PacchettoPersonalizzatoDTO ppDTO,
			UserDTO cliente);

	void partecipaPacchettoAmico(PacchettoPersonalizzatoDTO ppDTO,
			UserDTO cliente);
	
	void aggiornaHotelPers(HotelPersDTO hpDTO);

	void aggiornaEscursionePers(EscursionePersDTO epDTO);

	void aggiornaVoloPers(VoloPersDTO vpDTO);

	List<EscursionePersDTO> getEscursioniPersGiftList(String mailCliente);

	List<VoloPersDTO> getVoliPersGiftList(String mailCliente);

	List<HotelPersDTO> getHotelsPersGiftList(String mailCliente);

	List<PacchettoPersonalizzatoDTO> getStoricoUtente(UserDTO cliente);

	void rimuoviDalCarrello(PacchettoPersonalizzatoDTO ppDTO, UserDTO cliente);

	

}
