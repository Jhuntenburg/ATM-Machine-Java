import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Account {
    // variables
    private int customerNumber;
    private int pinNumber;

    public List<Double> getCheckingAccounts() {
        return checkingAccounts;
    }

    public void setCheckingAccounts(List<Double> checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    //	private double checkingBalance = 0;
//	private double savingBalance = 0;
    private List<Double> checkingAccounts = new ArrayList<>();
    private List<Double> savingsAccounts = new ArrayList<>();

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public Account() {
    }

    public Account(int customerNumber, int pinNumber, int numberOfCheckingAccounts, int numberOfSavingAccounts) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;


        //this.checking account size == user input
        for (int i = 0; i < numberOfCheckingAccounts; i++) {
            this.checkingAccounts.set(i, 0.0);
        }

        for (int i = 0; i < numberOfSavingAccounts; i++) {
            this.savingsAccounts.set(i, 0.0);

        }


    }

    public Account(int customerNumber, int pinNumber) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
    }

    public Account(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.checkingAccounts.add(checkingBalance);
        this.savingsAccounts.add(savingBalance);
    }

    public int setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
        return customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public int setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
        return pinNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public Double getCheckingBalance(int index) {

        return checkingAccounts.get(index);
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public double calcCheckingWithdraw(double amount) {
        checkingBalance = (checkingBalance - amount);
        return checkingBalance;
    }

    public double calcSavingWithdraw(double amount) {
        savingBalance = (savingBalance - amount);
        return savingBalance;
    }

    public double calcCheckingDeposit(double amount) {
        checkingBalance = (checkingBalance + amount);
        return checkingBalance;
    }

    public double calcSavingDeposit(double amount) {
        savingBalance = (savingBalance + amount);
        return savingBalance;
    }

    public void calcCheckTransfer(double amount) {
        checkingBalance = checkingBalance - amount;
        savingBalance = savingBalance + amount;
    }

    public void calcSavingTransfer(double amount) {
        savingBalance = savingBalance - amount;
        checkingBalance = checkingBalance + amount;
    }

    public void getCheckingWithdrawInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
                System.out.print("\nAmount you want to withdraw from Checking Account: ");
                double amount = input.nextDouble();
                if ((checkingBalance - amount) >= 0 && amount >= 0) {
                    calcCheckingWithdraw(amount);
                    System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
                    end = true;
                } else {
                    System.out.println("\nBalance Cannot be Negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }

    public void getsavingWithdrawInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                System.out.print("\nAmount you want to withdraw from Savings Account: ");
                double amount = input.nextDouble();
                if ((savingBalance - amount) >= 0 && amount >= 0) {
                    calcSavingWithdraw(amount);
                    System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                    end = true;
                } else {
                    System.out.println("\nBalance Cannot Be Negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }

    public void getCheckingDepositInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
                System.out.print("\nAmount you want to deposit from Checking Account: ");
                double amount = input.nextDouble();
                if ((checkingBalance + amount) >= 0 && amount >= 0) {
                    calcCheckingDeposit(amount);
                    System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
                    end = true;
                } else {
                    System.out.println("\nBalance Cannot Be Negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }

    public void getSavingDepositInput() {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                System.out.print("\nAmount you want to deposit into your Savings Account: ");
                double amount = input.nextDouble();

                if ((savingBalance + amount) >= 0 && amount >= 0) {
                    calcSavingDeposit(amount);
                    System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                    end = true;
                } else {
                    System.out.println("\nBalance Cannot Be Negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }

    public void getTransferInput(String accType) {
        boolean end = false;
        while (!end) {
            try {
                if (accType.equals("Checking")) {
                    System.out.println("\nSelect an account you wish to transfer funds to:");
                    System.out.println("1. Savings");
                    System.out.println("2. Exit");
                    System.out.print("\nChoice: ");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
                            System.out.print("\nAmount you want to deposit into your Savings Account: ");
                            double amount = input.nextDouble();
                            if ((savingBalance + amount) >= 0 && (checkingBalance - amount) >= 0 && amount >= 0) {
                                calcCheckTransfer(amount);
                                System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                                System.out.println(
                                        "\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
                                end = true;
                            } else {
                                System.out.println("\nBalance Cannot Be Negative.");
                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.out.println("\nInvalid Choice.");
                            break;
                    }
                } else if (accType.equals("Savings")) {
                    System.out.println("\nSelect an account you wish to transfer funds to: ");
                    System.out.println("1. Checking");
                    System.out.println("2. Exit");
                    System.out.print("\nChoice: ");
                    int choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
                            System.out.print("\nAmount you want to deposit into your savings account: ");
                            double amount = input.nextDouble();
                            if ((checkingBalance + amount) >= 0 && (savingBalance - amount) >= 0 && amount >= 0) {
                                calcSavingTransfer(amount);
                                System.out.println("\nCurrent checking account balance: " + moneyFormat.format(checkingBalance));
                                System.out.println("\nCurrent savings account balance: " + moneyFormat.format(savingBalance));
                                end = true;
                            } else {
                                System.out.println("\nBalance Cannot Be Negative.");
                            }
                            break;
                        case 2:
                            return;
                        default:
                            System.out.println("\nInvalid Choice.");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                input.next();
            }
        }
    }
}
