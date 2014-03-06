package it.polimi.traveldream.ejb.usermanagement.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class HotelDTO {
	
	private int idHotel;
	
	@NotEmpty
	@Pattern(regexp="([^ ^;][^;]*[^ ^;])|([^ ^;])", message="non deve contenere \";\" e non deve iniziare o finire con uno spazio")
	private String indirizzo;

	@NotEmpty
	@Pattern(regexp="([^ ^;][^;]*[^ ^;])|([^ ^;])", message="non deve contenere \";\" e non deve iniziare o finire con uno spazio")
	private String luogo;

	@NotEmpty
	@Pattern(regexp="([^ ^;][^;]*[^ ^;])|([^ ^;])", message="non deve contenere \";\" e non deve iniziare o finire con uno spazio")
	private String nome;

	@NotNull
	@DecimalMin(value="0.0", message="deve essere un valore positivo")
	private float prezzo;
	
	public HotelDTO(){}

	public HotelDTO(String indirizzo, String luogo, String nome, float prezzo) {
		this.indirizzo = indirizzo;
		this.luogo = luogo;
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	public HotelDTO(int idHotel, String indirizzo, String luogo, String nome, float prezzo) {
		this.idHotel = idHotel;
		this.indirizzo = indirizzo;
		this.luogo = luogo;
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	public String toString(){
		// trovare un modo migliore di separare i campi nella stringa (still: carattere non utilizzabile in nessun campo)
		String d = ";";
		return ""+idHotel+d+indirizzo+d+luogo+d+nome+d+prezzo;
	}
	
	/**@param value rappresentazione come stringa di un HotelDTO, generata da toString()
	 * @return istanza di HotelDTO, compreso idHotel
	 */
	public static HotelDTO fromString(String value){
		String[] fields = value.split(";");
		
		return new HotelDTO(
				Integer.parseInt(fields[0]),
				fields[1],
				fields[2],
				fields[3],
				Float.parseFloat(fields[4]));
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idHotel;
		result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((luogo == null) ? 0 : luogo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + Float.floatToIntBits(prezzo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)					return true;
		if (obj == null)					return false;
		if (getClass() != obj.getClass())	return false;
		
		HotelDTO other = (HotelDTO) obj;
		if (idHotel != other.idHotel)											return false;
		
		if (indirizzo == null) {			if (other.indirizzo != null)		return false;
		} else if (!indirizzo.equals(other.indirizzo))							return false;
		
		if (luogo == null) {				if (other.luogo != null)			return false;
		} else if (!luogo.equals(other.luogo))									return false;
		
		if (nome == null) {					if (other.nome != null)				return false;
		} else if (!nome.equals(other.nome))									return false;
		
		if (Float.floatToIntBits(prezzo) != Float.floatToIntBits(other.prezzo))	return false;
		
		return true;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	
	
}
