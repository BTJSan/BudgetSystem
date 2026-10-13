package BudgetSystem;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;


public class BudgetSystem {

    Repository<Transaction> transactions = new Repository<Transaction>();
    private BudgetCalculator calculator = new BudgetCalculator();

    //Todo: lägg till metoder och menyn
    //addTransaction
    //showTransactions

    static void main() {

        BudgetSystem budgetSystem = new BudgetSystem();

        boolean closeApplication = false;
        String inputChoice;
        String menu = """
                
                Budgethanteraren
                ---------------------
                Alternativ:
                1. Lagg till transaktion
                2. Visa alla transaktioner
                3. Visa saldo och sammanställning per kategori
                4. Filtrera transaktioner
                5. Spara till fil
                e. Avsluta
                ----------------------
                """;

        while (!closeApplication) {

            IO.println(menu);
            inputChoice = IO.readln("Vad vill du göra?");

            switch (inputChoice) {
                case "1" -> {
                    budgetSystem.addTransaction();
                }
                case "2" -> {
                    budgetSystem.showTransactions();
                }
                case "3" -> {
                    budgetSystem.showSummary();
                }
                case "4" -> {
                    //Kontrollera om resultatet blir 0 och skriv ut meddelande
                    //Annars ska resultatet skrivas ut
                    budgetSystem.filterOptions();

                }
                case "5" -> {
                    budgetSystem.saveToFile();
                }
                case "e" -> {
                    closeApplication = true;
                }
                default -> {
                    IO.println("Ogiltigt val.\n");
                }
            }
        }

    }

    private void addTransaction() {
        int newType = 0;
        String newCategory = "";
        double newAmount = 0.0;

        boolean running1 = true;
        while (running1) {
            try {
                newType = Integer.parseInt(IO.readln("""
                Ange typ av transaktion: 
                1. Inkomst
                2. Utgift"""));

                if (newType == 1 || newType == 2)
                running1 = false;
                else {
                    IO.println("Felaktig inmatning, försök igen");
                }
            }
            catch (Exception e) {
                IO.println("Felaktig inmatning, försök igen.");
            }
        }

        boolean running2 = true;
        while (running2) {
        newCategory = IO.readln("Ange kategori (exempel lön, mat etc.): ");
        if (newCategory.trim().isEmpty())
            IO.println("Du måste ange en kategori, försök igen.");
        else
            running2 = false;
        }

        boolean running3 = true;
        while (running3) {
            try {
                newAmount = Double.parseDouble(IO.readln("Ange summan: "));
                if (newAmount <= 0)
                    throw new InvalidTransactionException("Beloppet måste vara större än 0.");

                running3 = false;
            }
            catch (NumberFormatException e) {
                IO.println("Felaktigt värde, försök igen.");
            }
            catch (InvalidTransactionException e) {
                IO.println(e.getMessage());
            }
        }

        TransactionType typeEnum;
        if (newType == 1)
            typeEnum = TransactionType.INCOME;
        else
            typeEnum = TransactionType.EXPENSE;

        Transaction transaction = new Transaction(LocalDateTime.now(), newCategory, newAmount, typeEnum);
        transactions.add(transaction);

    }

    private void showTransactions() {
        Collection<Transaction> list = transactions.findAll();
        if (list.isEmpty()) {
            IO.println("Det finns inga transaktioner registrerade.");
            return;
        }

        for (Transaction transaction : list) {
            IO.println(transaction.date() + "\t|\t" + transaction.category()
                    + "\t|\t"
                    + transaction.amount() + "\t|\t"
                    + transaction.type().displayName());
        }
    }

    private void showSummary() {
        double balance = calculator.totalBalance(transactions.findAll());
        IO.println("Saldo: " + balance);

        Map<TransactionType, Double> map = calculator.sumPerType(transactions.findAll());
        for (Map.Entry<TransactionType, Double> entry : map.entrySet()) {
            IO.println(entry.getKey().displayName() + ": " + entry.getValue());
        }
    }

    private void filterOptions() {

    }

    private void saveToFile() {

    }

}
