package it.polimi.traveldream.web.beans;

import it.polimi.traveldream.ejb.usermanagement.ComponentiEAO;
import it.polimi.traveldream.ejb.usermanagement.ImpiegatoMgr;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="invalidaComponenteBean")
@RequestScoped
public class InvalidaComponenteBean implements Serializable{
	
	private static final long serialVersionUID = -8029596538203551673L;
	
	public class Componente implements Comparable<Componente>{
		public HotelDTO hotel;
		public VoloDTO volo;
		public EscursioneDTO escursione;
		
		public int id;
		public String tipo;
		public String info;
		
		public Componente(HotelDTO hotel){
			this.hotel = hotel;
			this.id = hotel.getIdHotel();
			this.info = hotel.getNome()+" "+hotel.getLuogo()+" "+hotel.getIndirizzo();
			this.tipo = "Hotel";
		}
		public Componente(VoloDTO volo){
			this.volo = volo;
			this.id = volo.getIdVolo();
			this.info = "da "+volo.getLuogoPartenza()+" a "+volo.getLuogoDestinazione()+" ("+volo.getDataEOraFormattati()+")";
			this.tipo = "Volo";
		}
		public Componente(EscursioneDTO escursione){
			this.escursione = escursione;
			this.id = escursione.getIdEscursione();
			this.info = escursione.getNome()+" "+escursione.getLuogo()+" "+escursione.getDataFormattata();
			this.tipo = "Escursione";
		}
		
		public int		getId(){	return id;}
		public String	getTipo(){	return tipo;}
		public String	getInfo(){	return info; }
		
		@Override
		public int compareTo(Componente o) {			
			return this.id - o.id;
		}
	}
	
	@EJB
	private ImpiegatoMgr impiegatoMgr;
	@EJB
	private ComponentiEAO componentiEAO;
	
	private List<Componente>	componenti;
	
	public InvalidaComponenteBean(){
		// componenti inizializzato nel getter
	}
	
	public String invalidaComponente(Componente componente){
		if(componente.hotel != null)			impiegatoMgr.invalidaComponente(componente.hotel);
		else if(componente.volo != null)		impiegatoMgr.invalidaComponente(componente.volo);
		else if(componente.escursione != null)	impiegatoMgr.invalidaComponente(componente.escursione);
		else throw new IllegalArgumentException("componente must contain an instance of either HotelDTO, VoloDTO or EscursioneDTO");
		
		return "/home?faces-redirect=true";
	}
	
	private void initComponenti(){
		componenti = new ArrayList<Componente>();
		
		List<HotelDTO>		hotels		= componentiEAO.allHotel();
		List<VoloDTO>		voli		= componentiEAO.allVoli();
		List<EscursioneDTO>	escursioni	= componentiEAO.allEscursioni();

		for(HotelDTO hotel: hotels)					componenti.add(new Componente(hotel));
		for(VoloDTO volo: voli)						componenti.add(new Componente(volo));
		for(EscursioneDTO escursione: escursioni)	componenti.add(new Componente(escursione));
		
		Collections.sort(componenti);
	}
	
	public String rimuoviPacchetto(PacchettoPredefinitoDTO ppDTO){
		impiegatoMgr.rimuoviPacchettoPredefinito(ppDTO);
		return "/home?faces-redirect=true";
	}
	
	//** getters and setters   **//	

	public List<Componente> getComponenti() {
		if(componenti == null) initComponenti();
		return componenti;
	}
	
}
