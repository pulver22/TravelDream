package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.ClienteMgr;
import it.polimi.traveldream.ejb.usermanagement.UserMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="clienteBean")
@SessionScoped
public class ClienteBean {
	
	@EJB
	private UserMgr userMgr;
	@EJB
	private ClienteMgr clienteMgr;
	
	private String info;
	private PacchettoPersonalizzatoDTO pacchettoScelto;
	
	private List<PacchettoPersonalizzatoDTO> carrello;
	
	public ClienteBean(){
		//info = "stringa";
		//System.out.print("dentro costruttore ClienteBean()");
		//carrello = new ArrayList<PacchettoPersonalizzatoDTO>();
	}

	public List<PacchettoPersonalizzatoDTO> loadCarrello(){
		/*System.out.print("dentro loadCarrello()");ajax="false"
		System.out.print("Mail cliente cercato "+userMgr.getPrincipalEmail()==null ? "e' NULL" : userMgr.getPrincipalEmail());*/
		carrello = clienteMgr.getAllPacchetti(userMgr.getUserDTO());
		/*System.out.print("Luogo del pacchetto 0 in carrello "+
				carrello.get(0)!=null ? carrello.get(0).getLuogo() : "non ce n'e'");*/
		return carrello;		
	}

	public void rimuoviDalCarrello(PacchettoPersonalizzatoDTO ppDTO){
		clienteMgr.rimuoviDalCarrello(ppDTO, userMgr.getUserDTO());
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PacchettoPersonalizzatoDTO getPacchettoScelto() {  
		return pacchettoScelto;  
	}  
	 
	public void setPacchettoScelto(PacchettoPersonalizzatoDTO pacchettoScelto) {  
	    this.pacchettoScelto = pacchettoScelto;  
	}  
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<PacchettoPersonalizzatoDTO> getCarrello() {
		loadCarrello();
		return carrello;
	}

	public void setCarrello(List<PacchettoPersonalizzatoDTO> carrello) {
		this.carrello = carrello;
	}
	
	
}
