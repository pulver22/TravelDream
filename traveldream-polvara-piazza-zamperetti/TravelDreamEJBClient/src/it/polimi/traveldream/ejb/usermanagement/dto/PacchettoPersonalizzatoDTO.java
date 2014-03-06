package it.polimi.traveldream.ejb.usermanagement.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

public class PacchettoPersonalizzatoDTO {


	@Id
	private int id;

	private boolean acquistato;

	private String luogo;
	private UserDTO utente;
	private List<EscursionePersDTO> escursioniPers;
	private HotelPersDTO hotelPers;
	private VoloPersDTO voloAndataPers,voloRitornoPers;
	
	public PacchettoPersonalizzatoDTO(){
		
	}
	
	/**
	 * Costruttore che presuppone si sappia gia' quale volo sia di andata e quale di ritorno.
	 * Ovviamente con tutti i componenti gia' convertiti in [componente]PersDTO.
	 * @param idPacchetto
	 * @param Luogo
	 * @param Acquistato
	 * @param voloAndata (singolo)
	 * @param hotel (singolo)
	 * @param listaEscursioni
	 * @param voloRitorno (singolo)
	 * */
	public PacchettoPersonalizzatoDTO(int id, String luogo, boolean acquistato, VoloPersDTO voloAndataPers,
			HotelPersDTO hotelPers, List<EscursionePersDTO> escursioniPers, VoloPersDTO voloRitornoPers){
		this.id=id;
		this.luogo=luogo;
		this.acquistato=acquistato;
		this.escursioniPers=escursioniPers;
		this.hotelPers=hotelPers;
		this.voloAndataPers=voloAndataPers;
		this.voloRitornoPers=voloRitornoPers;
	}
	
	/**
	 * Costruttore che presuppone NON si sappia gia' quale volo sia di andata e quale di ritorno
	 * Ovviamente con tutti i componenti gia' convertiti in [componente]PersDTO.
	 * @param idPacchetto
	 * @param Luogo
	 * @param Acquistato
	 * @param listaVoli
	 * @param hotel (singolo)
	 * @param listaEscursioni
	 * */
	public PacchettoPersonalizzatoDTO(int id, String luogo, boolean acquistato, List<VoloPersDTO> voliPers,
			HotelPersDTO hotelPers, List<EscursionePersDTO> escursioniPers){
		this.id=id;
		this.luogo=luogo;
		this.acquistato=acquistato;
		this.escursioniPers=escursioniPers;
		this.hotelPers=hotelPers;
		Date giorno0 = voliPers.get(0).getVolo().getGiorno();
		Date giorno1 = voliPers.get(1).getVolo().getGiorno();
		if (giorno0.before(giorno1)){
			this.voloAndataPers=voliPers.get(0);
			this.voloRitornoPers=voliPers.get(1);
		} else {
			this.voloAndataPers=voliPers.get(1);
			this.voloRitornoPers=voliPers.get(0);
		}
	}
	
	/**
	 * Metodo per il calcolo del prezzo complessivo del pacchetto. 
	 * Tiene conto della possibilita' che alcuni componenti possano non esser inizializzati.
	 * Se tra i componenti non inizializzati c'e' almeno uno dei due voli, il delta per il calcolo
	 * delle notti in hotel e' posto a 1 di default.
	 * */
	public float getPrezzo(){
		float prezzo = 0;
		float prezzoVoloAndata=0,prezzoVoloRitorno=0,prezzoHotel=0;
		if (voloAndataPers!=null && !voloAndataPers.isAcquistato())
			prezzoVoloAndata = voloAndataPers.getVolo().getPrezzo();
		if (voloRitornoPers!=null && !voloRitornoPers.isAcquistato())
			prezzoVoloRitorno = voloRitornoPers.getVolo().getPrezzo();
		long delta=1;
		if (voloAndataPers!=null && voloRitornoPers!=null){
			delta = voloRitornoPers.getVolo().getGiorno().getTime()-voloAndataPers.getVolo().getGiorno().getTime();
			delta = delta/(60*60*24*1000);
		}
		if (hotelPers!=null && !hotelPers.isAcquistato())
			prezzoHotel = hotelPers.getHotel().getPrezzo();
		prezzo = prezzo + prezzoVoloAndata + prezzoVoloRitorno + prezzoHotel*delta;
		if (escursioniPers!=null)
		for (EscursionePersDTO epDTO: escursioniPers){
			if (!epDTO.isAcquistato())
			prezzo = prezzo + epDTO.getEscursione().getPrezzo();
		}
		return prezzo;
	}

	public long getDurataPersistenza(){
		return (voloRitornoPers.getVolo().getGiorno().getTime()-voloAndataPers.getVolo().getGiorno().getTime())/(60*60*24*1000);
	}
	public int getNumeroEscursioni(){
		return escursioniPers.size();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAcquistato() {
		return acquistato;
	}
	public void setAcquistato(boolean acquistato) {
		this.acquistato = acquistato;
	}
	public String getLuogo() {
		return luogo;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public UserDTO getUtente() {
		return utente;
	}
	public void setUtente(UserDTO utente) {
		this.utente = utente;
	}
	public List<EscursionePersDTO> getEscursioniPers() {
		return escursioniPers;
	}
	public void setEscursioniPers(List<EscursionePersDTO> escursioniPers) {
		this.escursioniPers = escursioniPers;
	}
	public HotelPersDTO getHotelPers() {
		return hotelPers;
	}
	public void setHotelPers(HotelPersDTO hotelPers) {
		this.hotelPers = hotelPers;
	}
	public VoloPersDTO getVoloAndataPers() {
		return voloAndataPers;
	}
	public void setVoloAndataPers(VoloPersDTO voloAndataPers) {
		this.voloAndataPers = voloAndataPers;
	}
	public VoloPersDTO getVoloRitornoPers() {
		return voloRitornoPers;
	}
	public void setVoloRitornoPers(VoloPersDTO voloRitornoPers) {
		this.voloRitornoPers = voloRitornoPers;
	}
	

	
}
