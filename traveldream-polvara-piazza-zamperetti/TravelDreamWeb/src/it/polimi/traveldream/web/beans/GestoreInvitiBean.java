package it.polimi.traveldream.web.beans;

import java.io.IOException;
import java.util.List;

import it.polimi.traveldream.ejb.usermanagement.ClienteMgr;
import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelPersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursionePersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloPersDTO;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;




@ManagedBean(name="gestoreInvitiBean")
@SessionScoped
public class GestoreInvitiBean {

	@EJB
	private ClienteMgr clienteMgr;
	@EJB
	private UserMgr userMgr;
	
	@NotEmpty(message="Il campo non può essere vuoto")
	@Digits(integer=6, fraction=0, message="Codice pacchetto non valido")
	private String idPacchettoAmico;
	
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="Mail non valida")
	private String mailAmico;
	
	private PacchettoPersonalizzatoDTO pacchettoAmico;
	
	private List<EscursionePersDTO> escursioniGFAmico;	
	private List<HotelPersDTO> hotelsGFAmico;	
	private List<VoloPersDTO> voliGFAmico;
	
	public GestoreInvitiBean(){
		
	}
	
	public String paginaPacchettoAmico(){
		pacchettoAmico = clienteMgr.findPacchettoAmico(Integer.parseInt(idPacchettoAmico));
		if (pacchettoAmico != null)
			return"/pacchettoAmico";
		else 
			return"/pacchettoNonTrovato?faces-redirect=true";
	}
	
	public String paginaGiftListAmico(){
		hotelsGFAmico = clienteMgr.getHotelsPersGiftList(mailAmico);
		voliGFAmico = clienteMgr.getVoliPersGiftList(mailAmico);
		escursioniGFAmico = clienteMgr.getEscursioniPersGiftList(mailAmico);
		
		return "/giftListAmico?faces-redirect=true";
	}
	
	public void indietro(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String partecipa(){
		
		clienteMgr.partecipaPacchettoAmico(pacchettoAmico, userMgr.getUserDTO());
		return "/cliente/carrello?faces-redirect=true";
	}
	
	public void regalaEscursione(EscursionePersDTO escursione){
		escursione.setAcquistato(true);
		clienteMgr.aggiornaEscursionePers(escursione);
		escursioniGFAmico = clienteMgr.getEscursioniPersGiftList(mailAmico);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void regalaVolo(VoloPersDTO volo){		
		volo.setAcquistato(true);
		clienteMgr.aggiornaVoloPers(volo);
		voliGFAmico = clienteMgr.getVoliPersGiftList(mailAmico);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void regalaHotel(HotelPersDTO hotel){		
		hotel.setAcquistato(true);
		clienteMgr.aggiornaHotelPers(hotel);
		hotelsGFAmico = clienteMgr.getHotelsPersGiftList(mailAmico);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getIdPacchettoAmico() {
		return idPacchettoAmico;
	}

	public void setIdPacchettoAmico(String idPacchettoAmico) {
		this.idPacchettoAmico = idPacchettoAmico;
	}

	public String getMailAmico() {
		return mailAmico;
	}

	public void setMailAmico(String mailAmico) {
		this.mailAmico = mailAmico;
	}

	public PacchettoPersonalizzatoDTO getPacchettoAmico() {
		//pacchettoAmico = clienteMgr.findPacchettoAmico(idPacchettoAmico);
		return pacchettoAmico;
	}

	public void setPacchettoAmico(PacchettoPersonalizzatoDTO pacchettoAmico) {
		this.pacchettoAmico = pacchettoAmico;
	}

	public List<EscursionePersDTO> getEscursioniGFAmico() {
		return escursioniGFAmico;
	}

	public void setEscursioniGFAmico(List<EscursionePersDTO> escursioniGFAmico) {
		this.escursioniGFAmico = escursioniGFAmico;
	}

	public List<HotelPersDTO> getHotelsGFAmico() {
		return hotelsGFAmico;
	}

	public void setHotelsGFAmico(List<HotelPersDTO> hotelsGFAmico) {
		this.hotelsGFAmico = hotelsGFAmico;
	}

	public List<VoloPersDTO> getVoliGFAmico() {
		return voliGFAmico;
	}

	public void setVoliGFAmico(List<VoloPersDTO> voliGFAmico) {
		this.voliGFAmico = voliGFAmico;
	}
	
}
