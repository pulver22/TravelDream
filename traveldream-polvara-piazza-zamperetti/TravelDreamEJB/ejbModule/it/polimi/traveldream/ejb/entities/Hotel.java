package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;

import java.io.Serializable;
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


/**
 * The persistent class for the Hotel database table.
 * 
 */
@Entity
@Table(name="Hotel")
@NamedQueries({
	@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h"),
	@NamedQuery(name="Hotel.findAllValidi", query="SELECT h FROM Hotel h WHERE h.valido=1 ORDER BY h.idHotel DESC"),
	@NamedQuery(name="Hotel.findByLuogo", query="SELECT h FROM Hotel h WHERE h.luogo= :luogo"),
	@NamedQuery(name="Hotel.findByLuogoEValidi", query="SELECT h FROM Hotel h WHERE h.luogo= :luogo AND h.valido= 1 ORDER BY h.idHotel DESC"),
	@NamedQuery(name="Hotel.findByPrezzo", query="SELECT h FROM Hotel h WHERE h.prezzo= :prezzo")	
})
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHotel;

	private String indirizzo;

	private String luogo;

	private String nome;

	private float prezzo;
	
	private boolean valido;

	//bi-directional many-to-one association to HotelsPers
	@OneToMany(mappedBy="hotel")
	private List<HotelsPers> hotelsPers;
	
	@ManyToMany(mappedBy="hotels")
	private List<PacchettoPredefinito> pacchettoPred;

	public Hotel() {}

	public Hotel(HotelDTO hotelDTO) {
		indirizzo	= hotelDTO.getIndirizzo();
		luogo		= hotelDTO.getLuogo();
		nome		= hotelDTO.getNome();
		prezzo		= hotelDTO.getPrezzo();
		valido		= true;
	}
	

	/** Ritorna un'istanza di oggetto Hotel, completo di id (= hotelDTO.getIdHotel() )
	 * 
	 * @param voloDTO
	 */
	public static Hotel fromDTO(HotelDTO hotelDTO){
		Hotel hotel = new Hotel(hotelDTO);
		hotel.idHotel = hotelDTO.getIdHotel();
		return hotel;
	}

	public HotelDTO toDTO(){
		return new HotelDTO(idHotel, indirizzo, luogo, nome, prezzo);
	}
	
	public int getIdHotel() {
		return this.idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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

	public List<HotelsPers> getHotelsPers() {
		return this.hotelsPers;
	}

	public void setHotelsPers(List<HotelsPers> hotelsPers) {
		this.hotelsPers = hotelsPers;
	}

	public HotelsPers addHotelsPer(HotelsPers hotelsPer) {
		getHotelsPers().add(hotelsPer);
		hotelsPer.setHotel(this);

		return hotelsPer;
	}

	public HotelsPers removeHotelsPer(HotelsPers hotelsPer) {
		getHotelsPers().remove(hotelsPer);
		hotelsPer.setHotel(null);

		return hotelsPer;
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