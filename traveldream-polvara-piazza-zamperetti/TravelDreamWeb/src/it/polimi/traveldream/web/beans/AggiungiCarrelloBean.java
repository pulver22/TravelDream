package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.ClienteMgr;
import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="aggiungiAlCarrelloBean")
@RequestScoped
public class AggiungiCarrelloBean {
	
	@EJB
	private ClienteMgr clienteMgr;
	@EJB
	private UserMgr userMgr;
	
	private PacchettoPredefinitoDTO pacchettoScelto;
	
	//creazione di un pacchetto personalizzato come aggiunta di uno predefinito al carrello di cliente
	public  AggiungiCarrelloBean(){
		// pacchettiPredefinitiDTO inizializzato nel getter
	}
	
	public String aggiungiAlCarrello(){
		clienteMgr.aggiungiAlCarrello(pacchettoScelto, userMgr.getUserDTO());
		
		return "/cliente/carrello?faces-redirect=true";
	}
	
	public void compraPacchetto(PacchettoPersonalizzatoDTO pacchetto){
		if(pacchetto.isAcquistato()== false){
			pacchetto.setAcquistato(true);
			clienteMgr.aggiornaPacchettoPersonalizzato(pacchetto, userMgr.getUserDTO());
		}
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//***	getters and setters  	***//
	
	public PacchettoPredefinitoDTO getPacchettoScelto() {
		return pacchettoScelto;
	}

	public void setPacchettoScelto(PacchettoPredefinitoDTO pacchettoPredefinitoDTO) {
		this.pacchettoScelto = pacchettoPredefinitoDTO;
	}

}
