package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.EscursionePersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelPersDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPersonalizzatoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.UserDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloPersDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the PacchettoPersonalizzato database table.
 * 
 */
@Entity
@Table(name="PacchettoPersonalizzato")
@NamedQueries({
	@NamedQuery(name="PacchettoPersonalizzato.findById", 
			query="SELECT p FROM PacchettoPersonalizzato p WHERE p.id= :id"),
	@NamedQuery(name="PacchettoPersonalizzato.findAllPacchettiUtente", 
			query="SELECT p FROM PacchettoPersonalizzato p WHERE p.utente.mail= :mail"),
	@NamedQuery(name="PacchettoPersonalizzato.findCarrelloUtente", 
			query="SELECT p FROM PacchettoPersonalizzato p WHERE p.utente.mail= :mail AND p.acquistato=false"),	
	@NamedQuery(name="PacchettoPersonalizzato.findStoricoUtente", 
			query="SELECT p FROM PacchettoPersonalizzato p WHERE p.utente.mail= :mail AND p.acquistato=true"),
	//Query che restituiscono i [Componenti] in GiftList di un utente
	@NamedQuery(name="PacchettoPersonalizzato.findEscursioniGiftList", 
			query="SELECT e "
					+ "FROM Escursione e, PacchettoPersonalizzato p, EscursioniPers ep "
					+ "WHERE p.utente.mail= :mail AND "
					+ "ep.id.pacchetto=p.id AND ep.id.idEscursione=e.idEscursione AND ep.inGiftList=true"),
	@NamedQuery(name="PacchettoPersonalizzato.findHotelsGiftList", 
			query="SELECT h "
					+ "FROM Hotel h, PacchettoPersonalizzato p, HotelsPers hp "
					+ "WHERE p.utente.mail= :mail AND "
					+ "hp.id.pacchetto=p.id AND hp.id.idHotel=h.idHotel AND hp.inGiftList=true"),
	@NamedQuery(name="PacchettoPersonalizzato.findVoliGiftList", 
			query="SELECT v "
					+ "FROM Volo v, PacchettoPersonalizzato p, VoliPers vp "
					+ "WHERE p.utente.mail= :mail AND "
					+ "vp.id.pacchetto=p.id AND vp.id.idVolo=v.idVolo AND vp.inGiftList=true"),
	//Query che restituiscono i [componenti]Pers in GiftList di un Utente
	@NamedQuery(name="PacchettoPersonalizzato.findVoliPersGiftList", 
			query="SELECT vp FROM VoliPers vp, PacchettoPersonalizzato p "
					+ "WHERE p.utente.mail= :mail AND p.acquistato=false AND vp.id.pacchetto=p.id "
					+ "AND vp.inGiftList=true AND vp.acquistato=false"),
	@NamedQuery(name="PacchettoPersonalizzato.findEscursioniPersGiftList", 
			query="SELECT ep FROM EscursioniPers ep, PacchettoPersonalizzato p "
					+ "WHERE p.utente.mail= :mail AND p.acquistato=false AND ep.id.pacchetto=p.id "
					+ "AND ep.inGiftList=true AND ep.acquistato=false"),
	@NamedQuery(name="PacchettoPersonalizzato.findHotelsPersGiftList", 
			query="SELECT hp FROM HotelsPers hp, PacchettoPersonalizzato p "
					+ "WHERE p.utente.mail= :mail AND p.acquistato=false AND hp.id.pacchetto=p.id "
					+ "AND hp.inGiftList=true AND hp.acquistato=false"),
	})
public class PacchettoPersonalizzato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean acquistato;

	private String luogo;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="MailClienteCarrello")
	private Cliente utente;
	
	//private String mailClienteCarrello;

	//bi-directional many-to-one association to EscursioniPers
	@OneToMany(mappedBy="pacchettoPersonalizzato", cascade = CascadeType.ALL)
	private List<EscursioniPers> escursioniPers;

	//bi-directional many-to-one association to HotelsPers
	@OneToMany(mappedBy="pacchettoPersonalizzato", cascade = CascadeType.ALL)
	private List<HotelsPers> hotelsPers;

	//bi-directional many-to-one association to VoliPers
	@OneToMany(mappedBy="pacchettoPersonalizzato", cascade = CascadeType.ALL)
	private List<VoliPers> voliPers;

	public PacchettoPersonalizzato() {
		
	}
	
	//creazione di un pacchetto personalizzato come aggiunta di uno predefinito al carrello di cliente
	public PacchettoPersonalizzato(PacchettoPredefinitoDTO pacchettoPredefinitoDTO, UserDTO cliente){		
		luogo = pacchettoPredefinitoDTO.getLuogo();
		utente = Cliente.fromDTO(cliente);
		acquistato = false;
		voliPers = new ArrayList<VoliPers>();
		hotelsPers = new ArrayList<HotelsPers>();
		escursioniPers = new ArrayList<EscursioniPers>();
		
		VoliPers voloPersAndata = new VoliPers(pacchettoPredefinitoDTO.getVoloAndata(), this, false,false,false);
		voliPers.add(voloPersAndata);
		
		VoliPers voloPersRitorno = new VoliPers(pacchettoPredefinitoDTO.getVoloRitorno(), this, false,false,false);
		voloPersRitorno.setPacchettoPersonalizzato(this);
		voliPers.add(voloPersRitorno);
		
		HotelsPers hotelPers = new HotelsPers(pacchettoPredefinitoDTO.getHotel(), this, false,false,false);
		hotelPers.setPacchettoPersonalizzato(this);
		hotelsPers.add(hotelPers);
		
		for(EscursioneDTO escursioneDTO: pacchettoPredefinitoDTO.getEscursioni()){
			EscursioniPers escursionePers = new EscursioniPers(escursioneDTO, this, false, false, false);
			
			escursioniPers.add(escursionePers);
		}/*
		*/
		/*
		for (int i=0; i< pacchettoPredefinitoDTO.getEscursioni().size();i++){
			EscursioniPers ep = new EscursioniPers(pacchettoPredefinitoDTO.getEscursioni().get(i),false,false,false);
			ep.setPacchettoPersonalizzato(this);
			escursioniPers.add(ep);
		}
		*/
	}
	
	public PacchettoPersonalizzato (PacchettoPersonalizzatoDTO ppDTO, UserDTO cliente){
		utente = Cliente.fromDTO(cliente);
		this.id=ppDTO.getId();
		this.luogo=ppDTO.getLuogo();
		this.acquistato=ppDTO.isAcquistato();
		hotelsPers = new ArrayList<HotelsPers>();
		voliPers = new ArrayList<VoliPers>();
		escursioniPers = new ArrayList<EscursioniPers>();
		
		
		this.hotelsPers.add(new HotelsPers(ppDTO.getHotelPers().getHotel(), this, 
				ppDTO.getHotelPers().isInGiftList(), ppDTO.getHotelPers().isAcquistato(), 
				ppDTO.getHotelPers().isRegalato()));
		this.voliPers.add(new VoliPers(ppDTO.getVoloAndataPers().getVolo(), this,
				ppDTO.getVoloAndataPers().isInGiftList(), ppDTO.getVoloAndataPers().isAcquistato(),
				ppDTO.getVoloAndataPers().isRegalato()));
		this.voliPers.add(new VoliPers(ppDTO.getVoloRitornoPers().getVolo(), this,
				ppDTO.getVoloRitornoPers().isInGiftList(), ppDTO.getVoloRitornoPers().isAcquistato(),
				ppDTO.getVoloRitornoPers().isRegalato()));
		if (ppDTO.getEscursioniPers()!=null && ppDTO.getEscursioniPers().size()>0)
		for (EscursionePersDTO ep: ppDTO.getEscursioniPers()){
			this.escursioniPers.add(new EscursioniPers(ep.getEscursione(), this, ep.isInGiftList(),
					ep.isAcquistato(), ep.isRegalato()));
		}
	}
	
	public PacchettoPersonalizzatoDTO toDTO(){
		PacchettoPersonalizzatoDTO pacchettoPersonalizzatoDTO = new PacchettoPersonalizzatoDTO();
		
		//Con i dati dell pacchettoPersonalizzato p in analisi
		pacchettoPersonalizzatoDTO.setId(id);
		pacchettoPersonalizzatoDTO.setLuogo(luogo);

		//Creo l'hotelPersDTO a partire da quello qui contenuto, col metodo apposito 
		//di HotelPers
		HotelPersDTO hotelPersDTO = hotelsPers.get(0).toDTO();
		hotelPersDTO.setIdPacchetto(id);
		//Lo aggiungo al nuovo pacchettoPersonalizzatoDTO
		pacchettoPersonalizzatoDTO.setHotelPers(hotelPersDTO);
		
		//Qui andra' fatto lo stesso per voli e escursioni...
		//Voli
		VoloPersDTO andataPers, ritornoPers;
		if (voliPers.get(0).getVolo().getData().getTimeInMillis()
				<voliPers.get(1).getVolo().getData().getTimeInMillis()){
			//Caso in cui ho prima l'andata e poi il ritorno nella lista di voli
			andataPers = voliPers.get(0).toDTO();
			andataPers.setIdPacchetto(id);
			ritornoPers = voliPers.get(1).toDTO();
			ritornoPers.setIdPacchetto(id);
			
			pacchettoPersonalizzatoDTO.setVoloAndataPers(andataPers);
			pacchettoPersonalizzatoDTO.setVoloRitornoPers(ritornoPers);
		} else {
			//se ho prima il ritorno e poi l'andata
			andataPers= voliPers.get(1).toDTO();
			andataPers.setIdPacchetto(id);
			ritornoPers= voliPers.get(0).toDTO();
			ritornoPers.setIdPacchetto(id);
			
			pacchettoPersonalizzatoDTO.setVoloAndataPers(andataPers);
			pacchettoPersonalizzatoDTO.setVoloRitornoPers(ritornoPers);
		}
		//Escursioni
		if(escursioniPers!=null){
			List<EscursionePersDTO> listaEscursioniPers = new ArrayList<EscursionePersDTO>();
			EscursionePersDTO tempEPDTO = new EscursionePersDTO();
			for (EscursioniPers ep: escursioniPers){
				tempEPDTO = ep.toDTO();
				tempEPDTO.setIdPacchetto(id);
				listaEscursioniPers.add(tempEPDTO);
			}
			pacchettoPersonalizzatoDTO.setEscursioniPers(listaEscursioniPers);
		}
		
		return pacchettoPersonalizzatoDTO;
	}
	
	public PacchettoPersonalizzatoDTO toDTO1(){
		List<EscursionePersDTO> escursioniPers = new ArrayList<EscursionePersDTO>();
		List<VoloPersDTO> voliPers = new ArrayList<VoloPersDTO>();
		for (VoliPers vp: this.voliPers){
			voliPers.add(vp.toDTO());
		}
		if(this.escursioniPers!=null)
			for (EscursioniPers ep: this.escursioniPers){
				escursioniPers.add(ep.toDTO());
			}
		HotelPersDTO hotelsPers = this.hotelsPers.get(0).toDTO();
		return new PacchettoPersonalizzatoDTO(id,luogo,acquistato, voliPers,hotelsPers,escursioniPers);
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getAcquistato() {
		return this.acquistato;
	}

	public void setAcquistato() {
		this.acquistato = true;
	}

	public String getLuogo() {
		return this.luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public Cliente getMailClienteCarrello() {
		return this.utente;
	}

	public void setMailClienteCarrello(Cliente mailClienteCarrello) {
		this.utente = mailClienteCarrello;
	}

	public List<EscursioniPers> getEscursioniPers() {
		return this.escursioniPers;
	}

	public void setEscursioniPers(List<EscursioniPers> escursioniPers) {
		this.escursioniPers = escursioniPers;
	}

	public EscursioniPers addEscursioniPer(EscursioniPers escursioniPer) {
		getEscursioniPers().add(escursioniPer);
		escursioniPer.setPacchettoPersonalizzato(this);

		return escursioniPer;
	}

	public EscursioniPers removeEscursioniPer(EscursioniPers escursioniPer) {
		getEscursioniPers().remove(escursioniPer);
		escursioniPer.setPacchettoPersonalizzato(null);

		return escursioniPer;
	}

	public List<HotelsPers> getHotelsPers() {
		return this.hotelsPers;
	}

	public void setHotelsPers(List<HotelsPers> hotelsPers) {
		this.hotelsPers = hotelsPers;
	}

	public HotelsPers addHotelsPer(HotelsPers hotelsPer) {
		getHotelsPers().add(hotelsPer);
		hotelsPer.setPacchettoPersonalizzato(this);

		return hotelsPer;
	}

	public HotelsPers removeHotelsPer(HotelsPers hotelsPer) {
		getHotelsPers().remove(hotelsPer);
		hotelsPer.setPacchettoPersonalizzato(null);

		return hotelsPer;
	}

	public List<VoliPers> getVoliPers() {
		return this.voliPers;
	}

	public void setVoliPers(List<VoliPers> voliPers) {
		this.voliPers = voliPers;
	}

	public VoliPers addVoliPer(VoliPers voliPer) {
		getVoliPers().add(voliPer);
		voliPer.setPacchettoPersonalizzato(this);

		return voliPer;
	}

	public VoliPers removeVoliPer(VoliPers voliPer) {
		getVoliPers().remove(voliPer);
		voliPer.setPacchettoPersonalizzato(null);

		return voliPer;
	}

	public Cliente getUtente() {
		return utente;
	}

	public void setUtente(Cliente utente) {
		this.utente = utente;
	}

	public void setAcquistato(boolean acquistato) {
		this.acquistato = acquistato;
	}
	
	

}