package it.polimi.traveldream.ejb.usermanagement.dto;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class VoloDTO {
	
	private int idVolo;
	
	@NotNull
	private Date giorno;
	
	@Pattern(regexp="([01][0-9]|[2][0-3]):[0-5][0-9]", message="formato dell'ora non valido")
	private String ora;
	
	@NotEmpty
	@Pattern(regexp="([^ ^;][^;]*[^ ^;])|([^ ^;])", message="non deve contenere \";\" e non deve iniziare o finire con uno spazio")
	private String luogoDestinazione;

	@NotEmpty
	@Pattern(regexp="([^ ^;][^;]*[^ ^;])|([^ ^;])", message="non deve contenere \";\" e non deve iniziare o finire con uno spazio")
	private String luogoPartenza;

	@NotNull
	@DecimalMin(value="0.0", message="deve essere un valore positivo")
	private float prezzo;
	

	public VoloDTO() {
	}
	
	public VoloDTO(Date giorno, String ora, String luogoDestinazione, String luogoPartenza, float prezzo) {
		this.giorno = giorno;
		this.ora = ora;
		this.luogoDestinazione = luogoDestinazione;
		this.luogoPartenza = luogoPartenza;
		this.prezzo = prezzo;
	}
	
	public VoloDTO(int idVolo, Date giorno, String ora, String luogoDestinazione, String luogoPartenza, float prezzo) {
		this.idVolo = idVolo;
		this.giorno = giorno;
		this.ora = ora;
		this.luogoDestinazione = luogoDestinazione;
		this.luogoPartenza = luogoPartenza;
		this.prezzo = prezzo;
	}
	
	public String toString(){
		// trovare un modo migliore di separare i campi nella stringa (still: carattere non utilizzabile in nessun campo)
		String d = ";";
		return ""+idVolo+d+giorno.getTime()+d+ora+d+luogoDestinazione+d+luogoPartenza+d+prezzo;
	}
	
	/**@param value rappresentazione come stringa di un VoloDTO, generata da toString()
	 * @return istanza di VoloDTO, compreso idEscursione
	 */
	public static VoloDTO fromString(String value){
		String[] fields = value.split(";");
		
		int 	idVolo = Integer.parseInt(fields[0]);
		Date 	giorno = new Date(Long.parseLong(fields[1]));
		String 	ora = fields[2];
		String	luogoDestinazione = fields[3];
		String	luogoPartenza =  fields[4];
		float	prezzo = Float.parseFloat(fields[5]);
		
		return new VoloDTO(idVolo, giorno, ora, luogoDestinazione, luogoPartenza, prezzo);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((giorno == null) ? 0 : giorno.hashCode());
		result = prime * result + idVolo;
		result = prime * result + ((luogoDestinazione == null) ? 0 : luogoDestinazione.hashCode());
		result = prime * result + ((luogoPartenza == null) ? 0 : luogoPartenza.hashCode());
		result = prime * result + ((ora == null) ? 0 : ora.hashCode());
		result = prime * result + Float.floatToIntBits(prezzo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)					return true;
		if (obj == null)					return false;
		if (getClass() != obj.getClass())	return false;
		
		VoloDTO other = (VoloDTO) obj;
		if (giorno == null) {			if (other.giorno != null)				return false;
		} else if (!giorno.equals(other.giorno))								return false;
		
		if (idVolo != other.idVolo)												return false;
		
		if (luogoDestinazione == null) {if (other.luogoDestinazione != null)	return false;
		} else if (!luogoDestinazione.equals(other.luogoDestinazione))			return false;
		
		if (luogoPartenza == null) {	if (other.luogoPartenza != null)		return false;		
		} else if (!luogoPartenza.equals(other.luogoPartenza))					return false;
		
		if (ora == null) {				if (other.ora != null)					return false;
		} else if (!ora.equals(other.ora))										return false;
		
		if (Float.floatToIntBits(prezzo) != Float.floatToIntBits(other.prezzo))	return false;
		
		return true;
	}

	public Calendar getCalendarData() {
		Calendar data = Calendar.getInstance();
		data.setTime(giorno);
		
		String[] fields = ora.split(":");
		int ore = Integer.parseInt(fields[0]);
		int minuti = Integer.parseInt(fields[1]);
		
		data.set(Calendar.HOUR_OF_DAY, ore);
		data.set(Calendar.MINUTE, minuti);
		
		return data;
	}

	public Date getGiorno() {
		return giorno;
	}

	public void setGiorno(Date giorno) {
		this.giorno = giorno;
	}
	
	public String getOra(){
		return ora;
	}
	
	public void setOra(String ora){
		this.ora = ora;
	}
	
	
	public String getDataEOraFormattati(){
		Calendar data = getCalendarData();
		return String.format("%d/%d/%d %s", data.get(Calendar.DAY_OF_MONTH), data.get(Calendar.MONTH)+1, data.get(Calendar.YEAR), ora);
	}

	public String getLuogoDestinazione() {
		return luogoDestinazione;
	}

	public void setLuogoDestinazione(String luogoDestinazione) {
		this.luogoDestinazione = luogoDestinazione;
	}

	public String getLuogoPartenza() {
		return luogoPartenza;
	}

	public void setLuogoPartenza(String luogoPartenza) {
		this.luogoPartenza = luogoPartenza;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}
	
	
}
