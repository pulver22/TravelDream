package it.polimi.traveldream.ejb.usermanagement.dto;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class EscursioneDTO {
	
	private int idEscursione;
	
	@NotNull
	private Date data;

	@NotNull
	/** deprecata **/
	private Time durata;

	@NotEmpty
	@Pattern(regexp="([^ ^;][^;]*[^ ^;])|([^ ^;])", message="non deve contenere \";\" e non deve iniziare o finire con uno spazio")
	private String luogo;

	@NotEmpty
	@Pattern(regexp="([^ ^;][^;]*[^ ^;])|([^ ^;])", message="non deve contenere \";\" e non deve iniziare o finire con uno spazio")
	private String nome;

	@NotNull
	@DecimalMin(value="0.0", message="deve essere un valore positivo")
	private float prezzo;
	
	
	public EscursioneDTO() {
	}

	public EscursioneDTO(Date data, Time durata, String luogo, String nome, float prezzo) {
		this.data = data;
		this.durata = durata;
		this.luogo = luogo;
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	public EscursioneDTO(int idEscursione, Date data, Time durata, String luogo, String nome, float prezzo) {
		this.idEscursione = idEscursione;
		this.data = data;
		this.durata = durata;
		this.luogo = luogo;
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	public String toString(){
		// trovare un modo migliore di separare i campi nella stringa (still: carattere non utilizzabile in nessun campo)
		String d = ";";
		return ""+idEscursione+d+data.getTime()+d+durata.getTime()+d+luogo+d+nome+d+prezzo;
	}
	
	/**@param value rappresentazione come stringa di un EscursioneDTO, generata da toString()
	 * @return istanza di EscursioneDTO, compreso idEscursione
	 */
	public static EscursioneDTO fromString(String value){
		String[] fields = value.split(";");
		
		int 	idEscursione = Integer.parseInt(fields[0]);
		Date 	data = new Date(Long.parseLong(fields[1]));
		Time 	durata = new Time(Long.parseLong(fields[2]));
		String	luogo = fields[3];
		String	nome =  fields[4];
		float	prezzo = Float.parseFloat(fields[5]);
		
		return new EscursioneDTO(idEscursione, data, durata, luogo, nome, prezzo);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((durata == null) ? 0 : durata.hashCode());
		result = prime * result + idEscursione;
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
		
		EscursioneDTO other = (EscursioneDTO) obj;
		
		if (data == null) {				if (other.data != null)					return false;
		} else if (!data.equals(other.data))									return false;
		
		if (durata == null) {			if (other.durata != null)				return false;
		} else if (!durata.equals(other.durata))								return false;
		
		if (idEscursione != other.idEscursione)									return false;
		
		if (luogo == null) {			if (other.luogo != null)				return false;
		} else if (!luogo.equals(other.luogo))									return false;
		
		if (nome == null) {				if (other.nome != null)					return false;
		} else if (!nome.equals(other.nome))									return false;
		
		if (Float.floatToIntBits(prezzo) != Float.floatToIntBits(other.prezzo))	return false;
		
		return true;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public String getDataFormattata(){
		Calendar calendarData = Calendar.getInstance();
		calendarData.setTime(data);
		
		return String.format("%d/%d/%d", calendarData.get(Calendar.DAY_OF_MONTH), calendarData.get(Calendar.MONTH)+1, calendarData.get(Calendar.YEAR));
	}

	public Time getDurata() {
		return durata;
	}

	public void setDurata(Time durata) {
		this.durata = durata;
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

	public int getIdEscursione() {
		return idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}
	
	
}
