package tn.esprit.vbank.utils;

import java.util.Random;
import java.util.Set;

import tn.esprit.vbank.entities.Transaction;
import tn.esprit.vbank.utils.dto.CalculationResult;

public class TransactionUtils {

    public static String generateTransactionReference() {
    	int leftLimit = 48;
	    int rightLimit = 122;
	    int targetStringLength = 10;
	    Random random = new Random();

	    return random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
    }
    
    public static CalculationResult calculateTotals(Set<Transaction> transactions, Long compte) {
    	double totalDepenses = 0d;
    	double totalRecu = 0d;
    	if (!transactions.isEmpty()) {
    		for (Transaction t : transactions) {
    			if (t.getCompteSourceId() == compte) {
    				totalDepenses = totalDepenses + t.getMontant();
    			} else {
    				totalRecu = totalRecu + t.getMontant();    			
    			}
    		}
    	}
    	return new CalculationResult(totalDepenses, totalRecu);
    }
}