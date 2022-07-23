package tn.esprit.vbank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.vbank.entities.Signal;
import tn.esprit.vbank.repositories.SignalRepository;
import tn.esprit.vbank.services.ISignalService;

@Service
public class SignalServiceImpl implements ISignalService {

	private static final long serialVersionUID = 6191889143079517027L;

	@Autowired
	private SignalRepository SignalRepository;

	@Override
	public List<Signal> getSignals() {

		return (List<Signal>) SignalRepository.findAll();
	}

	@Override
	public Signal getSignalById(Long id) {
		return SignalRepository.findById(id).orElse(null);
	}

	@Override
	public Signal addSignal(Signal a) {

		return SignalRepository.save(a);
	}

	@Override
	public boolean supprimerSignal(Long id) {

		try {
			SignalRepository.deleteById(id);

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public Signal updateSignal(Long id, Signal a) {

		a.setIdSignal(id);
		return SignalRepository.save(a);
	}

	@Override
	public Signal getSignalWithIdUserAndIdPost(Long idPost, Long idUser) {
		return SignalRepository.getSignalWithIdUserAndIdPost(idPost, idUser);
	}

}
