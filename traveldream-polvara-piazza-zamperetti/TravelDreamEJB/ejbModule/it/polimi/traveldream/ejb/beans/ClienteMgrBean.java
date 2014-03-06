package it.polimi.traveldream.ejb.beans;


import it.polimi.traveldream.ejb.entities.Cliente;
import it.polimi.traveldream.ejb.entities.EscursioniPers;
import it.polimi.traveldream.ejb.entities.Gruppo;
import it.polimi.traveldream.ejb.entities.HotelsPers;
import it.polimi.traveldream.ejb.entities.PacchettoPersonalizzato;
import it.polimi.traveldream.ejb.entities.VoliPers;
import it.polimi.traveldream.ejb.usermanagement.ClienteMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursionePersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelPersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.UserDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloPersDTO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ClienteMgrBean implements ClienteMgr{
	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	@Override
	public void aggiungiAlCarrello(PacchettoPredefinitoDTO pacchettoPredefinitoDTO, UserDTO cliente) {
		PacchettoPersonalizzato pp = new PacchettoPersonalizzato(pacchettoPredefinitoDTO, cliente);
		em.persist(pp);
		em.flush();
		em.refresh(pp);
	}
	
	@Override
	@RolesAllowed({Gruppo._CLIENTE})
	public List<PacchettoPersonalizzatoDTO> getAllPacchetti(UserDTO cliente){
		
		//Cerco tutti pacchetti di un utente
		List<PacchettoPersonalizzato> pacchettiUtente = em.createNamedQuery("PacchettoPersonalizzato.findCarrelloUtente", PacchettoPersonalizzato.class)
		.setParameter("mail",cliente.getEmail())
		.getResultList();
		
		List<PacchettoPersonalizzatoDTO> pacchettiDTO = new ArrayList<PacchettoPersonalizzatoDTO>();
		
		for (PacchettoPersonalizzato p: pacchettiUtente){
			//Aggiungo alla lista di PacchettiDTO la conversione dell'attuale p
			pacchettiDTO.add(p.toDTO());
		}
		
		return pacchettiDTO;
	}
	
	@Override
	@RolesAllowed({Gruppo._CLIENTE})
	public void rimuoviDalCarrello(PacchettoPersonalizzatoDTO ppDTO, UserDTO cliente){
		em.remove(em.merge(new PacchettoPersonalizzato(ppDTO,cliente)));
	}
	
	@Override
	@RolesAllowed({Gruppo._CLIENTE})
	public List<PacchettoPersonalizzatoDTO> getStoricoUtente(UserDTO cliente){
		
		//Cerco tutti pacchetti di un utente
		List<PacchettoPersonalizzato> pacchettiUtente = em.createNamedQuery("PacchettoPersonalizzato.findStoricoUtente", PacchettoPersonalizzato.class)
		.setParameter("mail",cliente.getEmail())
		.getResultList();
		
		List<PacchettoPersonalizzatoDTO> pacchettiDTO = new ArrayList<PacchettoPersonalizzatoDTO>();
		
		for (PacchettoPersonalizzato p: pacchettiUtente){
			//Aggiungo alla lista di PacchettiDTO la conversione dell'attuale p
			pacchettiDTO.add(p.toDTO());
		}
		
		return pacchettiDTO;
	}
	
	@Override
	@RolesAllowed({Gruppo._CLIENTE})
	public void aggiornaPacchettoPersonalizzato(PacchettoPersonalizzatoDTO ppDTO, UserDTO cliente){
		PacchettoPersonalizzato pp = new PacchettoPersonalizzato(ppDTO,cliente);
		em.merge(pp);
	}
	
	@Override
	public List<EscursionePersDTO> getEscursioniPersGiftList(String mailCliente){
		List<EscursioniPers> escursioniPersGL = em.createNamedQuery("PacchettoPersonalizzato.findEscursioniPersGiftList", EscursioniPers.class)
				.setParameter("mail",mailCliente)
				.getResultList();
		List<EscursionePersDTO> escursioniPersGLDTO = new ArrayList<EscursionePersDTO>();
		if (escursioniPersGL!=null)
		for (EscursioniPers e: escursioniPersGL){
			escursioniPersGLDTO.add(e.toDTO());
		}
		return escursioniPersGLDTO;
	}
	
	@Override
	public List<VoloPersDTO> getVoliPersGiftList(String mailCliente){
		List<VoliPers> voliPersGL = em.createNamedQuery("PacchettoPersonalizzato.findVoliPersGiftList", VoliPers.class)
				.setParameter("mail",mailCliente)
				.getResultList();
		List<VoloPersDTO> voliPersGLDTO = new ArrayList<VoloPersDTO>();
		if (voliPersGL!=null)
		for (VoliPers v: voliPersGL){
			voliPersGLDTO.add(v.toDTO());
		}
		return voliPersGLDTO;
	}

	@Override
	public List<HotelPersDTO> getHotelsPersGiftList(String mailCliente){
		List<HotelsPers> hotelsPersGL = em.createNamedQuery("PacchettoPersonalizzato.findHotelsPersGiftList", HotelsPers.class)
				.setParameter("mail",mailCliente)
				.getResultList();
		List<HotelPersDTO> hotelsPersGLDTO = new ArrayList<HotelPersDTO>();
		if (hotelsPersGL!=null)
		for (HotelsPers h: hotelsPersGL){
			hotelsPersGLDTO.add(h.toDTO());
		}
		return hotelsPersGLDTO;
	}
	
	@Override
	@RolesAllowed({Gruppo._CLIENTE})
	public void partecipaPacchettoAmico(PacchettoPersonalizzatoDTO ppDTO, UserDTO cliente) {
		PacchettoPersonalizzato pp = new PacchettoPersonalizzato(ppDTO, cliente);
		em.persist(pp);
		em.flush();
		em.refresh(pp);
	}
	
	@Override
	public void aggiornaHotelPers(HotelPersDTO hpDTO){
		HotelsPers hp = new HotelsPers(hpDTO);
		hp.setPacchettoPersonalizzato(em.find(PacchettoPersonalizzato.class, hpDTO.getIdPacchetto()));
		em.merge(hp);
		em.flush();
	}
	
	@Override
	public void aggiornaEscursionePers(EscursionePersDTO epDTO){
		EscursioniPers ep = new EscursioniPers(epDTO);
		ep.setPacchettoPersonalizzato(em.find(PacchettoPersonalizzato.class, epDTO.getIdPacchetto()));
		em.merge(ep);
		em.flush();
	}
	
	@Override
	public void aggiornaVoloPers(VoloPersDTO vpDTO){
		VoliPers vp = new VoliPers(vpDTO);
		System.out.println(vpDTO.getIdPacchetto());
		vp.setPacchettoPersonalizzato(em.find(PacchettoPersonalizzato.class, vpDTO.getIdPacchetto()));
		em.merge(vp);
		em.flush();
	}
	
	@Override
	public PacchettoPersonalizzatoDTO findPacchettoAmico(int id) {
		PacchettoPersonalizzato pp = em.find(PacchettoPersonalizzato.class, id);
		if (pp!=null)
			return pp.toDTO();
		else
			return null;
	}


}
