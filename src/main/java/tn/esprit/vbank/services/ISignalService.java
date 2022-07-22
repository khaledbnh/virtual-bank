package tn.esprit.vbank.services;

import java.util.List;

import tn.esprit.vbank.entities.Like;
import tn.esprit.vbank.entities.Signal;


public interface ISignalService {

	public List<Signal> getSignals();

	public Signal getSignalById(Long id);

	public Signal addSignal(Signal a);

	public boolean supprimerSignal(Long id);

	public Signal updateSignal(Long id, Signal a);
	
	public Signal getSignalWithIdUserAndIdPost(Long idPost,Long idUser);

}
