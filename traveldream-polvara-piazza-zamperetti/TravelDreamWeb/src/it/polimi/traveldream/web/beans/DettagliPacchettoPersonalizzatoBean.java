package it.polimi.traveldream.web.beans;

import java.io.IOException;
import java.io.Serializable;

import it.polimi.traveldream.ejb.usermanagement.ClienteMgr;
import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursionePersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelPersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;




import it.polimi.traveldream.ejb.usermanagement.dto.VoloPersDTO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="dettagliPacchettoPersonalizzatoBean")
@SessionScoped
public class DettagliPacchettoPersonalizzatoBean implements Serializable{
	
	@EJB
	private UserMgr userMgr;
	@EJB
	private ClienteMgr clienteMgr;
	
	private PacchettoPersonalizzatoDTO pacchetto;
	private boolean statoPacchetto;
	
	
	public DettagliPacchettoPersonalizzatoBean(){
	}

	
	public void aggiungiVoloAGiftList(VoloPersDTO volo){
		System.out.println("ID pacchetto del VoloPers"+volo.getIdPacchetto());
		
		volo.setInGiftList(true);
		clienteMgr.aggiornaVoloPers(volo);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void aggiungiHotelAGiftList(HotelPersDTO hotel){
		System.out.println("ID pacchetto del HotelPers"+hotel.getIdPacchetto());
		
		hotel.setInGiftList(true);
		clienteMgr.aggiornaHotelPers(hotel);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void aggiungiEscursioneAGiftList(EscursionePersDTO escursione){
		System.out.println("ID pacchetto dell' EscursionePers"+escursione.getIdPacchetto());
		
		escursione.setInGiftList(true);
		clienteMgr.aggiornaEscursionePers(escursione);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean hotelInGiftListEinStorico(){
		return pacchetto.isAcquistato() || pacchetto.getHotelPers().isInGiftList();	
	}
	
	public boolean voloAndataInGiftListEinStorico(){
		return pacchetto.isAcquistato() || pacchetto.getVoloAndataPers().isInGiftList();
		
	}
	
	public boolean voloRitornoInGiftListEinStorico(){
		return pacchetto.isAcquistato() || pacchetto.getVoloRitornoPers().isInGiftList();
		
	}
	
	public boolean escursioneInGiftListEinStorico(EscursionePersDTO escursione){
		return pacchetto.isAcquistato() || escursione.isInGiftList();

	}
	
	public boolean hotelRegalatoENonStorico(){
		return !pacchetto.isAcquistato() && pacchetto.getHotelPers().isAcquistato();	
	}
	
	public boolean voloAndataRegalatoENonStorico(){
		return !pacchetto.isAcquistato() && pacchetto.getVoloAndataPers().isAcquistato();	
	}
	
	public boolean voloRitornoRegalatoENonStorico(){
		return !pacchetto.isAcquistato() && pacchetto.getVoloRitornoPers().isAcquistato();	
	}
	
	public boolean escursioneRegalatoENonStorico(EscursionePersDTO escursione){
		return !pacchetto.isAcquistato() && escursione.isAcquistato();

	}
	
	public String visualizzaPacchetto(PacchettoPersonalizzatoDTO pacchetto) {
		this.pacchetto = pacchetto;
		return "/cliente/dettagliPacchettoPersonalizzato?faces-redirect=true";
	}
	
	public String visualizzaPacchettoStorico(PacchettoPersonalizzatoDTO pacchetto) {
		this.pacchetto = pacchetto;
		return "/cliente/dettagliPacchettoPersonalizzatoStorico?faces-redirect=true";
	}

	public PacchettoPersonalizzatoDTO getPacchetto() {
		return pacchetto;
	}


	public void setPacchetto(PacchettoPersonalizzatoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}
}
