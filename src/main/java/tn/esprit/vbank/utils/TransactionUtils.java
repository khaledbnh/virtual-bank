package tn.esprit.vbank.utils;

import java.util.Random;

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
}