package groupproject2025;
 
// LiveExample 10.1
import java.util.Scanner;
 
public class TestLoanClassTemplate {
	/** Main method */
	public static void main(String[] args) {
		// Create a Scanner
		Scanner input = new Scanner(System.in);
 
		// Enter yearly interest rate
		System.out.print(
				"Enter annual interest rate, for example, 8.25: ");
		double annualInterestRate = input.nextDouble();
 
		// Enter number of years
		System.out.print("Enter number of years as an integer: ");
		int numberOfYears = input.nextInt();
 
		// Enter loan amount
		System.out.print("Enter loan amount, for example, 120000.95: ");
		double loanAmount = input.nextDouble();
 
		// Enter extra payment amount
		System.out.print("Enter extra payment amount, for example, 1000.00: ");
		double extraPaymentAmount = input.nextDouble();
 
		// Close scanner
		input.close();
 
		// Create Loan object
		Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);
 
		// Display loan date, monthly payment, and total payment, and total interest
		System.out.printf("The loan was created on %s%n" +
				"The monthly payment is %.2f%nThe total payment is %.2f%nThe total interest is %.2f%n%n",
				loan.getLoanDate().toString(),
				loan.getMonthlyPayment(),
				loan.getTotalPayment(),
				loan.getTotalInterest());
 
		extraPayFirstMonth(loan, extraPaymentAmount);
		System.out.println();
		extraPayEveryMonth(loan, extraPaymentAmount);
	}
 
	/** Extra payment first month only */
	public static void extraPayFirstMonth(Loan l, double extraPay) {
		System.out.printf("Extra payment only first month $%.2f%n", extraPay);
		double myLoanAmount = l.getLoanAmount();
		double myMonthlyInterestRate = l.getAnnualInterestRate() / 1200;
		double myMonthlyPayment = l.getMonthlyPayment();
		int month = 0;
		double interest = 0.0;
		double principal = 0.0;
		double totalInterest = 0.0;
 
		while (myLoanAmount >= myMonthlyPayment) {
			interest = myLoanAmount * myMonthlyInterestRate;
			principal = myMonthlyPayment - interest;

			// Only subtract extra payment in the very first month
			if (month == 0) {
				myLoanAmount -= (principal + extraPay);
			} else {
				myLoanAmount -= principal;
			}

			totalInterest += interest;
			month++;
		}

		if (myLoanAmount > 0) {
			interest = myLoanAmount * myMonthlyInterestRate;
			totalInterest += interest;
			month++;
		}

		double interestSaved = l.getTotalInterest() - totalInterest;
		System.out.printf("Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",
				totalInterest, interestSaved, month);
	}
 
	/** Extra payment every month */
	public static void extraPayEveryMonth(Loan l, double extraPay) {
		System.out.printf("Extra payment every month $%.2f%n", extraPay);
		double myLoanAmount = l.getLoanAmount();
		double myMonthlyInterestRate = l.getAnnualInterestRate() / 1200;
		double myMonthlyPayment = l.getMonthlyPayment();
		int month = 0;
		double interest = 0.0;
		double principal = 0.0;
		double totalInterest = 0.0;
 
		while (myLoanAmount >= (myMonthlyPayment + extraPay)) {
			interest = myLoanAmount * myMonthlyInterestRate;
			principal = myMonthlyPayment - interest;
			myLoanAmount -= (principal + extraPay);
			totalInterest += interest;
			month++;
		}
		while (myLoanAmount >= myMonthlyPayment) {
			interest = myLoanAmount * myMonthlyInterestRate;
			principal = myMonthlyPayment - interest;
			myLoanAmount -= principal;
			totalInterest += interest;
			month++;
		}
		if (myLoanAmount < myMonthlyPayment) {
			interest = myLoanAmount * myMonthlyInterestRate;
			totalInterest += interest;
			month++;
		}
		double interestSaved = l.getTotalInterest() - totalInterest;
		System.out.printf(
				"Total interest $%.2f%n"
						+ "Total interest saved $%.2f%n"
						+ "Total months %d %n",
				totalInterest, interestSaved, month);
	}
}
/*
 * Enter annual interest rate, for example, 8.25: 5.75
 * Enter number of years as an integer: 15
 * Enter loan amount, for example, 120000.95: 25000
 * The loan was created on Sat Oct 21 08:29:24 EDT 2023
 * The monthly payment is 207.60
 * The total payment is 37368.45
 */
/*
 * Enter annual interest rate, for example, 8.25: 5
 * Enter number of years as an integer: 4
 * Enter loan amount, for example, 120000.95: 20000
 * The loan was created on Sun Nov 09 13:57:33 EST 2025
 * The monthly payment is 460.59
 * The total payment is 22108.12
 */

/*
 * EXAMPLE: 5%, 30 years, loan amount 300000, 1000 extra payment
 * 
 * +Loan was created on [Date]
 * 
 * +The monthly payment is 1610.46
 * +The total payment is 579767.35
 * +The total interest is 279767.35
 * 
 * +Extra payment only first month $1000.00
 * Total interest 276334.97
 * Total interest saved 3432
 * Total months 358
 * 
 * Extra payment every month 1000
 * Total interest paid
 * Total Interest
 * Total interest saved $
 * Total months
 * 
 * Extra payment every month
 * Total interest
 * Total interest saved
 * Total months
 * 
 */
