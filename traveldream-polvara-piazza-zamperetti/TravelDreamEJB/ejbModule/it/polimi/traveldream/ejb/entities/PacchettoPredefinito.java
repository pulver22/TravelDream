package it.polimi.traveldream.ejb.entities;

import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the PacchettoPredefinito database table.
 * 
 */
@Entity
@Table(name="PacchettoPredefinito")
@NamedQueries({
	@NamedQuery(name="PacchettoPredefinito.findAll", query="SELECT p FROM PacchettoPredefinito p"),
	@NamedQuery(name="PacchettoPredefinito.findById", 
	query="SELECT p FROM PacchettoPredefinito p WHERE p.id = :id")
})
@NamedQuery(name="PacchettoPredefinito.findAll", query="SELECT p FROM PacchettoPredefinito p")
public class PacchettoPredefinito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String luogo;
	
	@ManyToMany
	@JoinTable(
		name="EscursioniPred"
		, joinColumns={
			@JoinColumn(name="Pacchetto", referencedColumnName="Id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IdEscursione", referencedColumnName="IdEscursione")
			}
		)
	private List<Escursione> escursioni;
	
	@ManyToMany
	@JoinTable(
		name="VoliPred"
		, joinColumns={
			@JoinColumn(name="Pacchetto", referencedColumnName="Id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IdVolo", referencedColumnName="IdVolo")
			}
		)
	private List<Volo> voli;
	
	@ManyToMany
	@JoinTable(
		name="HotelsPred"
		, joinColumns={
			@JoinColumn(name="Pacchetto", referencedColumnName="Id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IdHotel", referencedColumnName="IdHotel")
			}
		)
	private List<Hotel> hotels;

	public PacchettoPredefinito() {
		super();
	}
	
	public PacchettoPredefinito(PacchettoPredefinitoDTO pacchettoPredefinitoDTO){
		id = pacchettoPredefinitoDTO.getId();
		
		voli 		= new ArrayList<Volo>();
		hotels 		= new ArrayList<Hotel>();
		escursioni 	= new ArrayList<Escursione>();
		
		luogo = pacchettoPredefinitoDTO.getLuogo();
		
		if(pacchettoPredefinitoDTO.getVoloAndata()!=null)
			voli.add( Volo.fromDTO(pacchettoPredefinitoDTO.getVoloAndata() ));
		if(pacchettoPredefinitoDTO.getVoloRitorno()!=null)
			voli.add( Volo.fromDTO(pacchettoPredefinitoDTO.getVoloRitorno() ));
		
		for(EscursioneDTO eDTO: pacchettoPredefinitoDTO.getEscursioni()){
			escursioni.add(Escursione.fromDTO(eDTO));
		}
		
		if(pacchettoPredefinitoDTO.getHotel()!=null)
			hotels.add(Hotel.fromDTO(pacchettoPredefinitoDTO.getHotel()));		
	}
	
	public PacchettoPredefinitoDTO toDTO(){
		PacchettoPredefinitoDTO pacchettoPredefinitoDTO = new PacchettoPredefinitoDTO();
		
		pacchettoPredefinitoDTO.setId(id);
		
		pacchettoPredefinitoDTO.setLuogo(luogo);
		
		pacchettoPredefinitoDTO.setHotel(hotels.get(0).toDTO());
		
		Volo volo0 = voli.get(0), volo1 = voli.get(1);
		if(volo0.getData().compareTo(volo1.getData()) < 0){ // volo0.data < volo1.data
			pacchettoPredefinitoDTO.setVoloAndata(volo0.toDTO());
			pacchettoPredefinitoDTO.setVoloRitorno(volo1.toDTO());
		}else{												// volo0.data >= volo1.data
			pacchettoPredefinitoDTO.setVoloAndata(volo1.toDTO());
			pacchettoPredefinitoDTO.setVoloRitorno(volo0.toDTO());
		}
		
		ArrayList<EscursioneDTO> escursioniDTO = new ArrayList<EscursioneDTO>();
		for(Escursione escursione: escursioni) escursioniDTO.add(escursione.toDTO());
		pacchettoPredefinitoDTO.setEscursioni(escursioniDTO);
		
		return pacchettoPredefinitoDTO;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLuogo() {
		return this.luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public List<Escursione> getEscursioni() {
		return escursioni;
	}

	public void setEscursioni(List<Escursione> escursioni) {
		this.escursioni = escursioni;
	}

	public List<Volo> getVoli() {
		return voli;
	}

	public void setVoli(List<Volo> voli) {
		this.voli = voli;
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

}