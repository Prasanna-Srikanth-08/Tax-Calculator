package com.calculator.tax.calculator.calculations;

public class OldRegimeCalculation implements CalculateTax{

    private static final double FIVE_LAKH = 500000;
    private static final double TEN_LAKH = 1000000;

    @Override
    public double calculateTax(double amountAfterDeductions) {
        double taxAmount =0.0;
        double diff = 0.0;
        if(amountAfterDeductions<=FIVE_LAKH) {
            return 0.0;
        }
        else {
            taxAmount += 12500; // for 2.5L to 5L
            if(amountAfterDeductions<=TEN_LAKH) {
                diff = amountAfterDeductions - FIVE_LAKH;
                taxAmount+=(diff*0.20);
            } else {
                taxAmount+=100000;
                diff = amountAfterDeductions-TEN_LAKH;
                taxAmount+=(diff*0.30);
            }
        }
        return taxAmount;
    }
}
