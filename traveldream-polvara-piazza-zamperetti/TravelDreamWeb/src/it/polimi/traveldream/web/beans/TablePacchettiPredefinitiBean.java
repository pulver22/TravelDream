package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="tablePacchettiPredefinitiBean")
@RequestScoped
public class TablePacchettiPredefinitiBean implements Serializable {

	@EJB
	private UserMgr userMgr;
	
	private static final long serialVersionUID = -3571840320432606549L;
	
	private List<PacchettoPredefinitoDTO> pacchettiPredefinitiDTO;
	private List<PacchettoPredefinitoDTO> filteredPacchettiPredefinitiDTO;

	public  TablePacchettiPredefinitiBean(){
		// pacchettiPredefinitiDTO inizializzato nel getter
	}
	
	//***	getters and setters  	***//

	public List<PacchettoPredefinitoDTO> getPacchettiPredefinitiDTO() {
		if(pacchettiPredefinitiDTO == null) pacchettiPredefinitiDTO = userMgr.getAllPacchettiPredefiniti();
		
		return pacchettiPredefinitiDTO;
	}

	public List<PacchettoPredefinitoDTO> getFilteredPacchettiPredefinitiDTO() {
		return filteredPacchettiPredefinitiDTO;
	}

	public void setFilteredPacchettiPredefinitiDTO(
			List<PacchettoPredefinitoDTO> filteredPacchettiPredefinitiDTO) {
		this.filteredPacchettiPredefinitiDTO = filteredPacchettiPredefinitiDTO;
	}
}
