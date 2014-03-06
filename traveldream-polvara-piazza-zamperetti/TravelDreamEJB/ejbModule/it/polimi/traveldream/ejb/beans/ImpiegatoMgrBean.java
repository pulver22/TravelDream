package it.polimi.traveldream.ejb.beans;

import it.polimi.traveldream.ejb.entities.Escursione;
import it.polimi.traveldream.ejb.entities.Gruppo;
import it.polimi.traveldream.ejb.entities.Hotel;
import it.polimi.traveldream.ejb.entities.PacchettoPredefinito;
import it.polimi.traveldream.ejb.entities.Volo;
import it.polimi.traveldream.ejb.usermanagement.ImpiegatoMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ImpiegatoBean
 */
@Stateless
@LocalBean
public class ImpiegatoMgrBean implements ImpiegatoMgr{
	
	@PersistenceContext
    private EntityManager em;

    public ImpiegatoMgrBean() {
    }

	@Override
	@RolesAllowed({Gruppo._IMPIEGATO})
	public void creaComponente(HotelDTO hotelDTO) {
		em.persist(new Hotel(hotelDTO));
		em.flush();
	}

	@Override
	@RolesAllowed({Gruppo._IMPIEGATO})
	public void creaComponente(EscursioneDTO escursioneDTO) {
		em.persist(new Escursione(escursioneDTO));
		em.flush();
	}

	@Override
	@RolesAllowed({Gruppo._IMPIEGATO})
	public void creaComponente(VoloDTO voloDTO) {
		em.persist(new Volo(voloDTO));
		em.flush();
	}

	@Override
	@RolesAllowed({Gruppo._IMPIEGATO})
	public void creaPacchettoPredefinito(PacchettoPredefinitoDTO pacchettoPredefinitoDTO) {
		PacchettoPredefinito pp = new PacchettoPredefinito(pacchettoPredefinitoDTO);
		em.persist(pp);
		em.flush();
		em.refresh(pp);
	}
	
	@Override
	@RolesAllowed({Gruppo._IMPIEGATO})
	public void aggiornaPacchettoPredefinito(PacchettoPredefinitoDTO pacchettoPredefinitoDTO) {
		em.merge(new PacchettoPredefinito(pacchettoPredefinitoDTO));
	}

	@Override
	@RolesAllowed({Gruppo._IMPIEGATO})
	public void rimuoviPacchettoPredefinito(PacchettoPredefinitoDTO pacchettoPredefinitoDTO) {
		em.remove(em.merge(new PacchettoPredefinito(pacchettoPredefinitoDTO)));
	}
	
	@Override
	public void invalidaComponente(HotelDTO hotelDTO) {
		Hotel hotel = em.find(Hotel.class, hotelDTO.getIdHotel());
		
		for(PacchettoPredefinito pacchettoPredefinito: hotel.getPacchettoPred()) em.remove(pacchettoPredefinito);
		
		hotel.setValido(false);
		hotel.clearPacchettoPred();
		
		em.merge(hotel);
	}

	@Override
	public void invalidaComponente(EscursioneDTO escursioneDTO) {
		Escursione escursione = em.find(Escursione.class, escursioneDTO.getIdEscursione());
		
		for(PacchettoPredefinito pacchettoPredefinito: escursione.getPacchettoPred()) em.remove(pacchettoPredefinito);
		
		escursione.setValido(false);
		escursione.clearPacchettoPred();
		
		em.merge(escursione);
	}
	
	@Override
	public void invalidaComponente(VoloDTO voloDTO){
		Volo volo = em.find(Volo.class, voloDTO.getIdVolo());
		
		for(PacchettoPredefinito pacchettoPredefinito: volo.getPacchettoPred()) em.remove(pacchettoPredefinito);
		
		volo.setValido(false);
		volo.clearPacchettoPred();
		
		em.merge(volo);
	}
}
