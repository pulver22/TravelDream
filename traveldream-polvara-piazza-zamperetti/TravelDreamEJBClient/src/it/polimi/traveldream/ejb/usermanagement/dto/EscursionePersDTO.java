package it.polimi.traveldream.ejb.usermanagement.dto;

public class EscursionePersDTO {

	private int idPacchetto;
	
	private int idEscursione;
	
	private EscursioneDTO escursione;
	
	private boolean inGiftList;
	
	private boolean acquistato;
	
	private boolean regalato;
	
	public EscursionePersDTO(){}
	
	public EscursionePersDTO(EscursioneDTO escursione,
			boolean inGiftList, boolean acquistato, boolean regalato) {
		this.idEscursione=escursione.getIdEscursione();
		this.escursione = escursione;
		this.inGiftList=inGiftList;
		this.acquistato=acquistato;
		this.regalato=regalato;
	}
	
	public EscursionePersDTO(int idPacchetto, EscursioneDTO escursione,
			boolean inGiftList, boolean acquistato, boolean regalato) {
		this.idPacchetto = idPacchetto;
		this.idEscursione=escursione.getIdEscursione();
		this.escursione = escursione;
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

	public int getIdEscursione() {
		return idEscursione;
	}

	public void setIdEscursione(int idEscursione) {
		this.idEscursione = idEscursione;
	}

	public EscursioneDTO getEscursione() {
		return escursione;
	}

	public void setEscursione(EscursioneDTO escursione) {
		this.idEscursione = escursione.getIdEscursione();
		this.escursione = escursione;
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
