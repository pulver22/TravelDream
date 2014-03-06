package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.ClienteMgr;
import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursionePersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelPersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloPersDTO;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="gestisciGiftListBean")
@RequestScoped
public class GestisciGiftListBean {

	@EJB
	private ClienteMgr clienteMgr;
	@EJB
	private UserMgr userMgr;
	
	private List<EscursionePersDTO> escursioniGF;	
	private List<HotelPersDTO> hotelsGF;	
	private List<VoloPersDTO> voliGF;
	
	public GestisciGiftListBean(){
		
	}
	
	public void rimuoviEscursioneDaGL(EscursionePersDTO escursione){
		escursione.setInGiftList(false);
		clienteMgr.aggiornaEscursionePers(escursione);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rimuoviVoloDaGL(VoloPersDTO volo){		
		volo.setInGiftList(false);
		clienteMgr.aggiornaVoloPers(volo);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rimuoviHotelDaGL(HotelPersDTO hotel){		
		hotel.setInGiftList(false);
		clienteMgr.aggiornaHotelPers(hotel);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<EscursionePersDTO> getEscursioniGF() {
		escursioniGF = clienteMgr.getEscursioniPersGiftList(userMgr.getPrincipalEmail());
		return escursioniGF;
	}

	public void setEscursioniGF(List<EscursionePersDTO> escursioniGF) {
		this.escursioniGF = escursioniGF;
	}

	public List<HotelPersDTO> getHotelsGF() {
		hotelsGF = clienteMgr.getHotelsPersGiftList(userMgr.getPrincipalEmail());
		return hotelsGF;
	}

	public void setHotelsGF(List<HotelPersDTO> hotelsGF) {
		this.hotelsGF = hotelsGF;
	}

	public List<VoloPersDTO> getVoliGF() {
		voliGF = clienteMgr.getVoliPersGiftList(userMgr.getPrincipalEmail());
		return voliGF;
	}

	public void setVoliGF(List<VoloPersDTO> voliGF) {
		this.voliGF = voliGF;
	}
	
	
}
