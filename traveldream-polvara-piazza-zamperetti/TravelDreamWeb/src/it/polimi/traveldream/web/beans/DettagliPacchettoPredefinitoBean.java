package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="dettagliPacchettoPredefinitoBean")
@RequestScoped
public class DettagliPacchettoPredefinitoBean {
	
	@EJB
	private UserMgr userMgr;
	
	private PacchettoPredefinitoDTO pacchetto;
	
	
	public DettagliPacchettoPredefinitoBean(){
	}

	

	public String visualizzaPacchetto(PacchettoPredefinitoDTO pacchetto) {
		this.pacchetto = pacchetto;
		
		return "/dettagliPacchettoPredefinito";
	}

	public PacchettoPredefinitoDTO getPacchetto() {
		return pacchetto;
	}


	public void setPacchetto(PacchettoPredefinitoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}
}
