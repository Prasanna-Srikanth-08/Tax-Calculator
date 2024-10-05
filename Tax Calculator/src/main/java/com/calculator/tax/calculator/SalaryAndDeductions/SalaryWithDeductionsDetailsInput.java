package com.calculator.tax.calculator.SalaryAndDeductions;

import java.util.Scanner;

public class SalaryWithDeductionsDetailsInput {

    public void SalaryDetails() {

        double maxInvestmentForParentsHealthInsurance = 25000;

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Total income per year\n");
            double totalIncome = scanner.nextDouble();

            System.out.println("Part of Income which is a Basic Salary\n");
            double basicSalary = scanner.nextDouble();

            System.out.println("Part of Income which is a HRA\n");
            double HRA = scanner.nextDouble();

            System.out.println("Investments made on 80C\n");
            double investmentsOn80C = scanner.nextDouble();

            System.out.println("Investment made on NPS\n");
            double investmentOnNPS = scanner.nextDouble();

            System.out.println("Investment made on Health Insurance\n");
            System.out.println("Do you have seperate insurance for you and your parents? (true/false) \n");
            boolean seperateHealthInsurance = scanner.nextBoolean();

            double investmentOnSelfInsurance = 0;
            double investmentOnParentsHealthInsurance = 0;
            if (seperateHealthInsurance) {
                System.out.println("Is your parents age above 60?\n");
                boolean parentsAgeAbove60 = scanner.nextBoolean();
                if (parentsAgeAbove60) {
                    maxInvestmentForParentsHealthInsurance = 50000;
                }
                System.out.println("Amount invested on your Health insurance\n");
                investmentOnSelfInsurance = scanner.nextDouble();

                System.out.println("Amount invested on parents health insurance\n");
                investmentOnParentsHealthInsurance = scanner.nextDouble();
            }

            System.out.println("Amount invested on self insurace or self insurance + Family insurance(). " +
                    "Enter this details only if you have skipped to enter value in seperate insurance value for parents and self previously. \n");
            double investmentOnSelfAndFamilyInsurance = scanner.nextDouble();

            System.out.println("HRA Deductions\n");
            System.out.println("Do you live in a metro city (true/false)\n");
            boolean livingInMetroCity = scanner.nextBoolean();

            System.out.println("Amount spent on House Rent on Yearly");
            double houseRentPaid = scanner.nextDouble();

            System.out.println("Interest paid on Education Loan");
            double interestPaidOnEducationLoan = scanner.nextDouble();

            Deductions deductions = new Deductions();
            deductions.calculateIncomeAfterDeductions(totalIncome, basicSalary, HRA, investmentsOn80C, investmentOnNPS,
                    maxInvestmentForParentsHealthInsurance, seperateHealthInsurance, investmentOnSelfInsurance,
                    investmentOnParentsHealthInsurance, investmentOnSelfAndFamilyInsurance, houseRentPaid,
                    interestPaidOnEducationLoan,livingInMetroCity);
        }
    }
}
