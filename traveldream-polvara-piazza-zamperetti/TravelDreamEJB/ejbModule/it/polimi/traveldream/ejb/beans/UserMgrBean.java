package it.polimi.traveldream.ejb.beans;

import it.polimi.traveldream.ejb.entities.Cliente;
import it.polimi.traveldream.ejb.entities.Gruppo;
import it.polimi.traveldream.ejb.entities.PacchettoPredefinito;
import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
public class UserMgrBean implements UserMgr {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;


	@Override
	public void save(UserDTO user) {
		Cliente newUser = new Cliente(user);
		List<Gruppo> groups = new ArrayList<Gruppo>();
		groups.add(Gruppo.CLIENTE);
		newUser.setGruppi(groups);
		em.persist(newUser);
	}


	@Override
	@RolesAllowed({Gruppo._CLIENTE,Gruppo._IMPIEGATO})
	public void update(UserDTO user) {
		em.merge(new Cliente(user));
	}


	@Override
	@RolesAllowed({Gruppo._CLIENTE,Gruppo._IMPIEGATO})
	public UserDTO getUserDTO() {
		UserDTO userDTO = convertToDTO(getPrincipalUser());
		return userDTO;
	}


	@Override
	@RolesAllowed({Gruppo._CLIENTE})
	public void unregister() {
		remove(getPrincipalUser());
	}


	public Cliente find(String email) {
		return em.find(Cliente.class, email);
	}

	public List<Cliente> getAllUsers() {
		return em.createNamedQuery(Cliente.FIND_ALL, Cliente.class)
				.getResultList();
	}

	public void remove(String email) {
		Cliente user = find(email);
		em.remove(user);
	}

	public void remove(Cliente user) {
		em.remove(user);
	}

	public Cliente getPrincipalUser() {
		return find(getPrincipalEmail());
	}

	@Override
	public String getPrincipalEmail() {
		return context.getCallerPrincipal().getName();
	}

	private UserDTO convertToDTO(Cliente user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setNome(user.getNome());
		userDTO.setCognome(user.getCognome());
		userDTO.setIndirizzoFatturazione(user.getIndirizzofatturazione());
		userDTO.setNumCartaCredito(user.getNumCartaCredito());
		userDTO.setGruppo(user.getGruppi().get(0).name());
		return userDTO;
	}


	@Override
	public List<PacchettoPredefinitoDTO> getAllPacchettiPredefiniti() {
		List<PacchettoPredefinito> pacchettiPredefiniti = em.createNamedQuery("PacchettoPredefinito.findAll", PacchettoPredefinito.class).getResultList();
		
		List<PacchettoPredefinitoDTO> pacchettiPredefinitiDTO = new ArrayList<PacchettoPredefinitoDTO>();
		
		for (PacchettoPredefinito p: pacchettiPredefiniti) pacchettiPredefinitiDTO.add(p.toDTO());
		
		return pacchettiPredefinitiDTO;
	}
	
	
}
