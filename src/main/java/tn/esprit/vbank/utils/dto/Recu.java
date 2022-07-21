package tn.esprit.vbank.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recu {
    
	private String typeTransaction;
    private String numCompteSource;
    private String numCompteDestinataire;
    private String nomClient;
    private String typeIdentiteClient;
    private String numIdentiteClient;
    private String nomDestinataire;
    private String montant;
    private String timestamp;
    private String reference;

}
