package tn.esprit.vbank.utils.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompteInput {

    @NotBlank(message = "Numero de compte est obligatoire")
    private String numeroCompte;
}