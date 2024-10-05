package com.calculator.tax.calculator.SalaryAndDeductions;

import com.calculator.tax.calculator.calculations.CalculateTax;
import com.calculator.tax.calculator.calculations.NewRegimeCalculation;
import com.calculator.tax.calculator.calculations.OldRegimeCalculation;

public class Deductions {

    private static final float STANDARD_DEDUCTIONS = 50000;

    private static final double MAX_DEDUCTIONS_ON80C = 150000;

    private static final double MAX_NPS_DEDUCTIONS = 50000;

    public void calculateIncomeAfterDeductions(double totalIncome,double basicSalary,
                                               double HRA, double investmentsOn80C, double investmentOnNPS,
                                               double maxInvestmentForParentsHealthInsurance,
                                               boolean seperateHealthInsurance,double investmentOnSelfInsurance,
                                               double investmentOnParentsHealthInsurance,
                                               double investmentOnSelfAndFamilyInsurance,double houseRentPaid,
                                               double interestPaidOnEducationLoan,
                                               boolean livingInMetroCity) {

        double newRegimeIncomeAfterDeductions = totalIncome - STANDARD_DEDUCTIONS;
        CalculateTax calculateTax = new NewRegimeCalculation();
        System.out.println("New Regime tax is "+calculateTax.calculateTax(newRegimeIncomeAfterDeductions));

        //Standard Deduction
        double oldRegimeIncomeAfterDeductions = totalIncome - STANDARD_DEDUCTIONS;

        //80C Deductions
        if(investmentsOn80C!=0) {
            if (investmentsOn80C > MAX_DEDUCTIONS_ON80C) {
                oldRegimeIncomeAfterDeductions -= MAX_DEDUCTIONS_ON80C;
            } else {
                oldRegimeIncomeAfterDeductions -= investmentsOn80C;
            }
        }

        //NPS Deductions
        if(investmentOnNPS!=0) {
            if(investmentOnNPS > MAX_NPS_DEDUCTIONS) {
                oldRegimeIncomeAfterDeductions -= MAX_NPS_DEDUCTIONS;
            } else {
                oldRegimeIncomeAfterDeductions -= investmentOnNPS;
            }
        }

        //HRA Deductions
        if(HRA!=0 && basicSalary!=0 && houseRentPaid!=0) {
            double deductionForHRA = calculateHRA(basicSalary,HRA,houseRentPaid,livingInMetroCity);
            oldRegimeIncomeAfterDeductions -= (deductionForHRA*12);
        }

        double maxInvestmentOnSelfAndFamilyInsurance = 25000;
        //Medical Insurance Deductions
        if(seperateHealthInsurance) {
            if(investmentOnSelfInsurance>maxInvestmentOnSelfAndFamilyInsurance) {
                investmentOnSelfInsurance = investmentOnSelfInsurance - maxInvestmentOnSelfAndFamilyInsurance;
                oldRegimeIncomeAfterDeductions -= investmentOnSelfInsurance;
            }
            if(investmentOnParentsHealthInsurance>maxInvestmentForParentsHealthInsurance) {
                investmentOnParentsHealthInsurance = investmentOnParentsHealthInsurance - maxInvestmentForParentsHealthInsurance;
                oldRegimeIncomeAfterDeductions -= investmentOnParentsHealthInsurance;
            }
        } else {
            if(investmentOnSelfAndFamilyInsurance>maxInvestmentOnSelfAndFamilyInsurance) {
                investmentOnSelfAndFamilyInsurance = investmentOnSelfAndFamilyInsurance - maxInvestmentOnSelfAndFamilyInsurance;
                oldRegimeIncomeAfterDeductions -= investmentOnSelfAndFamilyInsurance;
            }
        }

        //interest paid on Education loan
        oldRegimeIncomeAfterDeductions -= interestPaidOnEducationLoan;


        CalculateTax oldRegimeCalculation = new OldRegimeCalculation();
        System.out.println("Old regime tax is "+oldRegimeCalculation.calculateTax(oldRegimeIncomeAfterDeductions));
    }

    public double calculateHRA(double basicSalary,double HRA,double houseRentPaid,boolean livingInMetroCity) {
        double receivedHRA = HRA;

        double basicPercent;
        if(livingInMetroCity) {
            basicPercent = basicSalary*0.5;
        } else {
            basicPercent = basicSalary*0.4;
        }

        double rentPaidMinus10PercentOfBasic = houseRentPaid - (basicSalary*0.10);
        System.out.println("HRA applicable is "+Math.min(receivedHRA, Math.min(basicPercent,rentPaidMinus10PercentOfBasic)));
        return Math.min(receivedHRA, Math.min(basicPercent,rentPaidMinus10PercentOfBasic));
    }
}
