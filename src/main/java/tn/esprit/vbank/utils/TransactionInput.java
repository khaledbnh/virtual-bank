package tn.esprit.vbank.utils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInput {

	@NotNull
	private String nomClient;
	@NotNull
	private String typeIdentiteClient;
	@NotNull
	private String numeroIdentiteClient;
	
	private String compteSource;
    private String compteDestinataire;

    @Positive(message = "Le montant doit être positif")
    // Prevent fraudulent transfers attempting to abuse currency conversion errors
    @Min(value = 10, message = "Le montant minimum de transfert est 10dt")
    private double montant;
}
