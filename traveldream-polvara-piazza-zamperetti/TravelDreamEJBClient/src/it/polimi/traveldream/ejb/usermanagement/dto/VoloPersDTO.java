package it.polimi.traveldream.ejb.usermanagement.dto;

public class VoloPersDTO {

	private int idPacchetto;
	
	private int idVolo;
	
	private VoloDTO volo;
	
	private boolean inGiftList;
	
	private boolean acquistato;
	
	private boolean regalato;
	
	public VoloPersDTO(){}
	
	public VoloPersDTO(VoloDTO volo,
			boolean inGiftList, boolean acquistato, boolean regalato) {
		this.idVolo = volo.getIdVolo();
		this.volo = volo;
		this.inGiftList=inGiftList;
		this.acquistato=acquistato;
		this.regalato=regalato;
	}
	
	public VoloPersDTO(int idPacchetto, VoloDTO volo,
			boolean inGiftList, boolean acquistato, boolean regalato) {
		this.idPacchetto=idPacchetto;
		this.idVolo = volo.getIdVolo();
		this.volo = volo;
		this.inGiftList=inGiftList;
		this.acquistato=acquistato;
		this.regalato=regalato;
	}
	
	public int getIdPacchetto() {
		return idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public int getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public VoloDTO getVolo() {
		return volo;
	}

	public void setVolo(VoloDTO volo) {
		this.idVolo = volo.getIdVolo();
		this.volo = volo;
	}

	public boolean isInGiftList() {
		return inGiftList;
	}

	public void setInGiftList(boolean inGiftList) {
		this.inGiftList = inGiftList;
	}

	public boolean isAcquistato() {
		return acquistato;
	}

	public void setAcquistato(boolean acquistato) {
		this.acquistato = acquistato;
	}

	public boolean isRegalato() {
		return regalato;
	}

	public void setRegalato(boolean regalato) {
		this.regalato = regalato;
	}
}
