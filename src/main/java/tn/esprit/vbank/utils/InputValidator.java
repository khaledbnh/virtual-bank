package tn.esprit.vbank.utils;

import java.util.regex.Pattern;


public class InputValidator {

    private static final Pattern numeroComptePattern = Pattern.compile("^[0-9]{8}$");

    /*public static boolean isNumeroCompteValid(String numeroCompte) {
        return numeroComptePattern.matcher(numeroCompte).find();
    }*/
    
    public static boolean isNumeroCinValid(String numeroCin) {
        return numeroComptePattern.matcher(numeroCin).find();
    }

    public static boolean isInputValid(TransactionInput transactionInput) {
    	
        if (transactionInput.getCompteSource().equals(transactionInput.getCompteDestinataire()))
            return false;
        
        if (transactionInput.getCompteSource() == null && transactionInput.getCompteSource().isEmpty())
        	return false;

        if (transactionInput.getCompteDestinataire() == null && transactionInput.getCompteDestinataire().isEmpty())
        	return false;
        
        if (transactionInput.getCompteSource().equals("0") && transactionInput.getCompteDestinataire().equals("0"))
        	return false;        
        
     /*   if (!transactionInput.getCompteSource().equals("0") && !isNumeroCompteValid(transactionInput.getCompteSource()))
            return false;

        if (transactionInput.getCompteDestinataire().equals("0") && !isNumeroCompteValid(transactionInput.getCompteDestinataire()))
            return false;
       */ 
        if ((!transactionInput.getTypeIdentiteClient().equalsIgnoreCase("CIN") && !transactionInput.getTypeIdentiteClient().equalsIgnoreCase("PASSPORT"))
        		|| !isNumeroCinValid(transactionInput.getNumeroIdentiteClient())) {
        	return false;
        }

        return true;
    }
}