package it.polimi.traveldream.ejb.usermanagement;

import java.util.List;

import it.polimi.traveldream.ejb.usermanagement.dto.PacchettoPredefinitoDTO;
import it.polimi.traveldream.ejb.usermanagement.dto.UserDTO;

import javax.ejb.Local;

@Local
public interface UserMgr {
	
	public void save(UserDTO user);
	
	public void update(UserDTO user);
	
	public void unregister();
	
	public UserDTO getUserDTO();

	String getPrincipalEmail();

	List<PacchettoPredefinitoDTO> getAllPacchettiPredefiniti();

	
	

}
