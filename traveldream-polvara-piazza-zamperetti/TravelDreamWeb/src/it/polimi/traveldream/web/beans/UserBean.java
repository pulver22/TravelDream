package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.UserDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UserBean {
	
	@EJB
	private UserMgr userMgr;
	
	public String getUserNomeCognomeMail(){
		UserDTO uDTO = userMgr.getUserDTO();
		return uDTO.getNome()+" "+uDTO.getCognome()+", ("+
				uDTO.getEmail()+")";
	}
	
	public String getUserRole(){
		return userMgr.getUserDTO().getGruppo().toLowerCase();
	}
	
	
	public boolean isLoggedIn() {
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
	}
	
	public boolean isUtenteImpiegato(){
		return isLoggedIn() && userMgr.getUserDTO().getGruppo().equals("IMPIEGATO");
	}
	
	public boolean isUtenteCliente(){
		return isLoggedIn() && userMgr.getUserDTO().getGruppo().equals("CLIENTE");
	}
	
	public String getNome() {
		return userMgr.getUserDTO().getNome();
	}
}
