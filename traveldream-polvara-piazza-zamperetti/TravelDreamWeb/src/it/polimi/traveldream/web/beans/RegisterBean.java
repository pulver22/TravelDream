package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.UserDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean {
	
	@EJB
	private UserMgr userMgr;

	private UserDTO cliente;
	
	public RegisterBean() {
		cliente = new UserDTO();
	}

	public UserDTO getUser() {
		return cliente;
	}

	public void setUser(UserDTO user) {
		this.cliente = user;
	}
	
	public String register() {
		userMgr.save(cliente);
		return "home?faces-redirect=true";
	}
}
