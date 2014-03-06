package it.polimi.traveldream.web.beans;

import java.util.List;

import it.polimi.traveldream.ejb.usermanagement.ClienteMgr;
import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="visualizzaStoricoBean")
@RequestScoped
public class VisualizzaStoricoBean {

	@EJB
	private UserMgr userMgr;
	@EJB
	private ClienteMgr clienteMgr;
	
	private List<PacchettoPersonalizzatoDTO> storico;

	
	
	public List<PacchettoPersonalizzatoDTO> getStorico() {
		storico = clienteMgr.getStoricoUtente(userMgr.getUserDTO());
		return storico;
	}

	public void setStorico(List<PacchettoPersonalizzatoDTO> storico) {
		this.storico = storico;
	}
	
	
}
