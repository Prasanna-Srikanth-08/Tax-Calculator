package com.calculator.tax.calculator.calculations;

public class NewRegimeCalculation implements CalculateTax{
    private static final double SIX_LAKHS = 600000;
    private static final double SEVEN_LAKHS = 700000;
    private static final double NINE_LAKHS = 900000;

    private static final double TWELVE_LAKHS = 1200000;

    private static final double FIFTEEN_LAKHS = 1500000;

    @Override
    public double calculateTax(double amountAfterDeductions) {
        double taxAmount =0.0;
        double diff = 0.0;
        if(amountAfterDeductions<=SEVEN_LAKHS) {
            return 0.0;
        }
        else{
            taxAmount+=15000; //3L to 6L
            if(amountAfterDeductions<NINE_LAKHS) {
                diff = amountAfterDeductions - SIX_LAKHS;
                taxAmount+=(diff*0.10);  //6L - 9L
            } else if(amountAfterDeductions>=NINE_LAKHS){
                taxAmount+=30000;
            }
            if(amountAfterDeductions>NINE_LAKHS && amountAfterDeductions<TWELVE_LAKHS) {
                diff = amountAfterDeductions - NINE_LAKHS;
                taxAmount+=(diff*0.15);
            } else if(amountAfterDeductions>=TWELVE_LAKHS){
                taxAmount+=45000;
            }
            if(amountAfterDeductions>TWELVE_LAKHS && amountAfterDeductions<FIFTEEN_LAKHS) {
                diff = amountAfterDeductions - TWELVE_LAKHS;
                taxAmount+=(diff*0.20);
            }
            else if(amountAfterDeductions>=FIFTEEN_LAKHS) {
                taxAmount+=60000;
                diff = amountAfterDeductions-FIFTEEN_LAKHS;
                taxAmount+=(diff*0.30);
            }
        }
        return taxAmount;
    }
}
