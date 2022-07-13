package tn.esprit.vbank.utils;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern numeroComptePattern = Pattern.compile("^[0-9]{8}$");
//    private static final Pattern numeroComptePattern = Pattern.compile("^[0-9]{2}-[0-9]{2}-[0-9]{2}$");

    public static boolean isSearchCriteriaValid(String numeroCompte) {
        return numeroComptePattern.matcher(numeroCompte).find();
    }

    public static boolean isSearchTransactionValid(TransactionInput transactionInput) {
    	
        if (!isSearchCriteriaValid(transactionInput.getCompteSource()))
            return false;

        if (!isSearchCriteriaValid(transactionInput.getCompteDestinataire()))
            return false;

        if (transactionInput.getCompteSource().equals(transactionInput.getCompteDestinataire()))
            return false;

        return true;
    }
}