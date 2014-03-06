package it.polimi.traveldream.ejb.beans;

import it.polimi.traveldream.ejb.entities.Escursione;
import it.polimi.traveldream.ejb.entities.Gruppo;
import it.polimi.traveldream.ejb.entities.Hotel;
import it.polimi.traveldream.ejb.entities.Volo;
import it.polimi.traveldream.ejb.usermanagement.ComponentiEAO;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import java.util.ArrayList;
import java.util.List;

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
public class ComponentiEAOBean implements ComponentiEAO{

	@PersistenceContext
    private EntityManager em;

	public ComponentiEAOBean() {
	}

	@Override
	@RolesAllowed({Gruppo._CLIENTE,Gruppo._IMPIEGATO})
	public List<HotelDTO> allHotel() {
		List<Hotel> hotels = em.createNamedQuery("Hotel.findAllValidi", Hotel.class)
				.getResultList();
		
		ArrayList<HotelDTO> hotelDTOs = new ArrayList<HotelDTO>();
		for(Hotel hotel: hotels) hotelDTOs.add(hotel.toDTO());
		
		return hotelDTOs;
	}
	
	@Override
	@RolesAllowed({Gruppo._CLIENTE,Gruppo._IMPIEGATO})
	public List<HotelDTO> allHotel(String luogo) {
		List<Hotel> hotels = em.createNamedQuery("Hotel.findByLuogoEValidi", Hotel.class)
				.setParameter("luogo", luogo)
				.getResultList();
		
		ArrayList<HotelDTO> hotelDTOs = new ArrayList<HotelDTO>();
		for(Hotel hotel: hotels) hotelDTOs.add(hotel.toDTO());
		
		return hotelDTOs;
	}
	
	

	@Override
	public List<VoloDTO> allVoli() {
		List<Volo> voli = em.createNamedQuery("Volo.findAllValidi", Volo.class)
				.getResultList();
		
		ArrayList<VoloDTO> voloDTOs = new ArrayList<VoloDTO>();
		for(Volo volo: voli) voloDTOs.add(volo.toDTO());
		
		return voloDTOs;
	}

	@Override
	public List<VoloDTO> allVoliAndata(String luogo) {
		List<Volo> voli = em.createNamedQuery("Volo.findByDestinazioneEValidi", Volo.class)
				.setParameter("destinazione", luogo)
				.getResultList();
		
		ArrayList<VoloDTO> voloDTOs = new ArrayList<VoloDTO>();
		for(Volo volo: voli) voloDTOs.add(volo.toDTO());
		
		return voloDTOs;
	}
	
	@Override
	public List<VoloDTO> allVoliRitorno(String luogo) {
		List<Volo> voli = em.createNamedQuery("Volo.findByPartenzaEValidi", Volo.class)
				.setParameter("partenza", luogo)
				.getResultList();
		
		ArrayList<VoloDTO> voloDTOs = new ArrayList<VoloDTO>();
		for(Volo volo: voli) voloDTOs.add(volo.toDTO());
		
		return voloDTOs;
	}
	
	

	@Override
	public List<EscursioneDTO> allEscursioni() {
		List<Escursione> escursioni = em.createNamedQuery("Escursione.findAllValidi", Escursione.class)
				.getResultList();
		
		ArrayList<EscursioneDTO> escursioneDTOs = new ArrayList<EscursioneDTO>();
		for(Escursione escursione: escursioni) escursioneDTOs.add(escursione.toDTO());
		
		return escursioneDTOs;
	}

	@Override
	public List<EscursioneDTO> allEscursioni(String luogo) {
		List<Escursione> escursioni = em.createNamedQuery("Escursione.findByLuogoEValidi", Escursione.class)
				.setParameter("luogo", luogo)
				.getResultList();
		
		ArrayList<EscursioneDTO> escursioneDTOs = new ArrayList<EscursioneDTO>();
		for(Escursione escursione: escursioni) escursioneDTOs.add(escursione.toDTO());
		
		return escursioneDTOs;
	}

	
}
