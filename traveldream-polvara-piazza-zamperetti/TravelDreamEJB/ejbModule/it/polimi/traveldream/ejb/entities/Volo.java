package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Volo database table.
 * 
 */
@Entity
@Table(name="Volo")
@NamedQueries({
	@NamedQuery(name="Volo.findAll", query="SELECT v FROM Volo v"),
	@NamedQuery(name="Volo.findAllValidi", query="SELECT v FROM Volo v WHERE v.valido= 1 ORDER BY v.idVolo DESC"),
	@NamedQuery(name="Volo.findByDestinazione", query="SELECT v FROM Volo v WHERE v.luogoDestinazione= :destinazione"),
	@NamedQuery(name="Volo.findByDestinazioneEValidi", query="SELECT v FROM Volo v WHERE v.luogoDestinazione= :destinazione AND v.valido= 1 ORDER BY v.idVolo DESC"),
	@NamedQuery(name="Volo.findByPartenza", query="SELECT v FROM Volo v WHERE v.luogoPartenza= :partenza"),
	@NamedQuery(name="Volo.findByPartenzaEValidi", query="SELECT v FROM Volo v WHERE v.luogoPartenza= :partenza AND v.valido= 1 ORDER BY v.idVolo DESC"),
	@NamedQuery(name="Volo.findByPrezzo", query="SELECT v FROM Volo v WHERE v.prezzo= :prezzo")	
})
public class Volo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVolo;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	private String luogoDestinazione;

	private String luogoPartenza;

	private float prezzo;
	
	private boolean valido;

	//bi-directional many-to-one association to VoliPers
	@OneToMany(mappedBy="volo")
	private List<VoliPers> voliPers;
	
	@ManyToMany(mappedBy="voli")
	private List<PacchettoPredefinito> pacchettoPred;

	public Volo() {
	}
	
	public Volo(VoloDTO voloDTO) {
		data				= voloDTO.getCalendarData();
		luogoDestinazione	= voloDTO.getLuogoDestinazione();
		luogoPartenza		= voloDTO.getLuogoPartenza();
		prezzo				= voloDTO.getPrezzo();
		valido				= true;
	}
	
	/** Ritorna un'istanza di oggetto Volo, completo di id ( = voloDTO.getIdVolo() )
	 * 
	 * @param voloDTO
	 */
	public static Volo fromDTO(VoloDTO voloDTO){
		Volo volo = new Volo(voloDTO);
		volo.idVolo = voloDTO.getIdVolo();
		return volo;
	}

	public VoloDTO toDTO(){
		Date giorno = data.getTime();
		int ore = data.get(Calendar.HOUR_OF_DAY);
		int minuti = data.get(Calendar.MINUTE);
		String ora = String.format("%02d:%02d", ore, minuti);
		return new VoloDTO(idVolo, giorno, ora, luogoDestinazione, luogoPartenza, prezzo);
	}
	
	public int getIdVolo() {
		return this.idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public Calendar getData() {
		return this.data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getLuogoDestinazione() {
		return this.luogoDestinazione;
	}

	public void setLuogoDestinazione(String luogoDestinazione) {
		this.luogoDestinazione = luogoDestinazione;
	}

	public String getLuogoPartenza() {
		return this.luogoPartenza;
	}

	public void setLuogoPartenza(String luogoPartenza) {
		this.luogoPartenza = luogoPartenza;
	}

	public float getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public List<VoliPers> getVoliPers() {
		return this.voliPers;
	}

	public void setVoliPers(List<VoliPers> voliPers) {
		this.voliPers = voliPers;
	}

	public VoliPers addVoliPer(VoliPers voliPer) {
		getVoliPers().add(voliPer);
		voliPer.setVolo(this);

		return voliPer;
	}

	public VoliPers removeVoliPer(VoliPers voliPer) {
		getVoliPers().remove(voliPer);
		voliPer.setVolo(null);

		return voliPer;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}
	
	public List<PacchettoPredefinito> getPacchettoPred() {
		return pacchettoPred;
	}

	public void clearPacchettoPred(){
		pacchettoPred.clear();
	}
}