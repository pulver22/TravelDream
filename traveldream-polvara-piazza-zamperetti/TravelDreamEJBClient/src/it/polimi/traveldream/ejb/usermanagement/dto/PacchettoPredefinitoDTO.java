package it.polimi.traveldream.ejb.usermanagement.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class PacchettoPredefinitoDTO {
	
	private int id;
	
	@NotEmpty
	private String luogo;
	
	@NotNull
	private HotelDTO hotel;
	
	@NotNull
	private VoloDTO voloAndata;
	
	@NotNull
	private VoloDTO voloRitorno;
	
	@NotNull
	private List<EscursioneDTO> escursioni;
	
	
	
	
	public PacchettoPredefinitoDTO(){
		escursioni = new ArrayList<EscursioneDTO>();
	}
	
	
	/** (!) implementazione non univoca: non vengono rappresentate le escursioni, ma solo il loro numero
	 * @return id;;luogo;;hotel;;voloAndata;;voloRitorno;;escursioni.size()
	 */
	public String toString(){
		// trovare un modo migliore di separare i campi nella stringa (still: carattere non utilizzabile in nessun campo e riconoscibile da quello usato nei componenti)
		String d = ";;";
		return ""+id+d+luogo+d+hotel+d+voloAndata+d+voloRitorno+d+escursioni.size();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public VoloDTO getVoloAndata() {
		return voloAndata;
	}

	public void setVoloAndata(VoloDTO voloAndata) {
		this.voloAndata = voloAndata;
	}

	public VoloDTO getVoloRitorno() {
		return voloRitorno;
	}

	public void setVoloRitorno(VoloDTO voloRitorno) {
		this.voloRitorno = voloRitorno;
	}

	public List<EscursioneDTO> getEscursioni() {
		return escursioni;
	}

	public void setEscursioni(List<EscursioneDTO> escursioni) {
		this.escursioni = escursioni;
	}
	
	public int getNumeroEscursioni(){
		return escursioni.size();
	}
	
	/**@return durata in giorni come differenza fra il giorno del volo di ritorno e il giorno del volo di andata
	 */
	public long getDurataPersistenza(){
		return (voloRitorno.getGiorno().getTime()-voloAndata.getGiorno().getTime())/(60*60*24*1000);
	}
	
	public float getPrezzo(){
		float prezzo = voloAndata.getPrezzo() + voloRitorno.getPrezzo() + hotel.getPrezzo()*getDurataPersistenza();
		
		for(EscursioneDTO escursioneDTO: escursioni) prezzo += escursioneDTO.getPrezzo();
		
		return prezzo;
	}
	
}
