package it.polimi.traveldream.ejb.usermanagement;

import it.polimi.traveldream.ejb.usermanagement.dto.EscursioneDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.HotelDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.VoloDTO;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ComponentiEAO {
	List<HotelDTO> allHotel();
	List<HotelDTO> allHotel(String luogo);

	List<VoloDTO> allVoli();
	List<VoloDTO> allVoliAndata(String luogo);
	List<VoloDTO> allVoliRitorno(String luogo);

	List<EscursioneDTO> allEscursioni();
	List<EscursioneDTO> allEscursioni(String luogo);
}
