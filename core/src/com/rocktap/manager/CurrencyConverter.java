package com.rocktap.manager;

import com.rocktap.utils.Constants;

import javafx.util.Pair;

/**
 * Created by Skronak on 17/08/2017.
 * Classe utilitaire pour gerer
 * les larges montants du jeu
 */
public class CurrencyConverter {


    public int[] addCurrency(int firstValue, int firstCurrency, int secValue, int secCurrency) {
        int[] newValue={firstValue, firstCurrency};
        if (firstCurrency - secCurrency >= Constants.UNLIMITED_CURRENCY_LIMIT) {
            return newValue;
        }
//        if (firstCurrency - secCurrency )

        return newValue;
    }

    public void decreaseCurrency() {

    }

    public void calculateCurrency() {

    }

    // Transforme index lettre en lettre
    // TODO voir perf sinon liste
    public String printLetter(int currency) {
        String currencyString = "";
        int currencyLast = 0;

        if (currency ==0) {
            return currencyString;
        }

        // cas multi lettres
        if (currency > Constants.CURRENCY_MAX_LETTER_INDEX) {
            currencyLast = currency%Constants.CURRENCY_MAX_LETTER_INDEX;

            while (currency > Constants.CURRENCY_MAX_LETTER_INDEX) {
                currency = (int) currency/Constants.CURRENCY_MAX_LETTER_INDEX;
                if (currency >= Constants.CURRENCY_MAX_LETTER_INDEX) {
                    currencyString += String.valueOf(getLetter(Constants.CURRENCY_MAX_LETTER_INDEX));
                } else {
                    currencyString += String.valueOf(getLetter(currency));
                }
            }
//    	Calcule derniere lettre
            if (currencyLast >0) {
                currencyString += String.valueOf(getLetter(currencyLast));
            }
        } else {
            currencyString += getLetter(currency);
        }
        return currencyString;
    }

    public Pair<Long, Integer>  addOperation(Long  firstValue, int firstCurrency, Long secValue, int secCurrency) {
        Long newValue=firstValue;
        int currencyDifference = firstCurrency - secCurrency ;
        int maxCurrency = Math.max(firstCurrency, secCurrency);

        if (currencyDifference >= Constants.UNLIMITED_CURRENCY_LIMIT) {
            System.out.println("Non significative value" + newValue);
            return new Pair<Long, Integer>(newValue, firstCurrency);
        } else if (currencyDifference <= -Constants.UNLIMITED_CURRENCY_LIMIT) {
            System.out.println("Non significative value" + newValue);
            return new Pair<Long, Integer>(secValue, secCurrency);
        }

        if (currencyDifference == 0) {
            return new Pair<Long, Integer>(firstValue + secValue, firstCurrency);
        }

        Long valueRes= (long) ((firstValue * Math.pow(1000, Long.valueOf(firstCurrency))) + (secValue * Math.pow(1000, Long.valueOf(secCurrency))));

        return new Pair<Long, Integer>(valueRes, maxCurrency);
    }

    // Ajuste la currency pour l'affichage
    public Pair<Long, Integer> adjustCurrency(Long value, int currency) {

        while(value>1000) {
            value=value/1000;
            currency+=1;
        }
        return new Pair
                <Long, Integer>(value, currency);
    }

   public char getLetter(int i)
    {
        return (char) (i + 64);
    }

}

