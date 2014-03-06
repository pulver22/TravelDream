package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;

import java.io.Serializable;
import java.sql.Time;
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
 * The persistent class for the Escursione database table.
 * 
 */
@Entity
@Table(name="Escursione")
@NamedQueries({
	@NamedQuery(name="Escursione.findAll", query="SELECT e FROM Escursione e"),
	@NamedQuery(name="Escursione.findAllValidi", query="SELECT e FROM Escursione e WHERE e.valido= 1 ORDER BY e.idEscursione DESC"),
	@NamedQuery(name="Escursione.findByLuogo", query="SELECT e FROM Escursione e WHERE e.luogo= :luogo"),
	@NamedQuery(name="Escursione.findByLuogoEValidi", query="SELECT e FROM Escursione e WHERE e.luogo= :luogo AND e.valido= 1 ORDER BY e.idEscursione DESC"),
	@NamedQuery(name="Escursione.findByPrezzo", query="SELECT e FROM Escursione e WHERE e.prezzo= :prezzo")	
})
public class Escursione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEscursione;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private Time durata;

	private String luogo;

	private String nome;

	private float prezzo;
	
	private boolean valido;

	//bi-directional many-to-one association to EscursioniPers
	@OneToMany(mappedBy="escursione")
	private List<EscursioniPers> escursioniPers;

	@ManyToMany(mappedBy="escursioni")
	private List<PacchettoPredefinito> pacchettoPred;
	
	public Escursione() {
	}
	
	public Escursione(EscursioneDTO escursioneDTO){
		data 	= escursioneDTO.getData();
		durata 	= escursioneDTO.getDurata();
		luogo 	= escursioneDTO.getLuogo();
		nome 	= escursioneDTO.getNome();
		prezzo	= escursioneDTO.getPrezzo();
		valido	= true;
	}

	/** Ritorna un'istanza di oggetto Volo, completo di id ( voloDTO.getIdVolo() )
	 * 
	 * @param voloDTO
	 * @return Istanza di volo univocamente identificabile nel DB
	 */
	public static Escursione fromDTO(EscursioneDTO escursioneDTO){
		Escursione escursione = new Escursione(escursioneDTO);
		escursione.idEscursione = escursioneDTO.getIdEscursione();
		return escursione;
	}

	public EscursioneDTO toDTO(){
		return new EscursioneDTO(idEscursione, data, durata, luogo, nome, prezzo);
	}
	
	public int getIdEscursione() {
		return this.idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getDurata() {
		return this.durata;
	}

	public void setDurata(Time durata) {
		this.durata = durata;
	}

	public String getLuogo() {
		return this.luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public List<EscursioniPers> getEscursioniPers() {
		return this.escursioniPers;
	}

	public void setEscursioniPers(List<EscursioniPers> escursioniPers) {
		this.escursioniPers = escursioniPers;
	}

	public EscursioniPers addEscursioniPer(EscursioniPers escursioniPer) {
		getEscursioniPers().add(escursioniPer);
		escursioniPer.setEscursione(this);

		return escursioniPer;
	}

	public EscursioniPers removeEscursioniPer(EscursioniPers escursioniPer) {
		getEscursioniPers().remove(escursioniPer);
		escursioniPer.setEscursione(null);

		return escursioniPer;
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