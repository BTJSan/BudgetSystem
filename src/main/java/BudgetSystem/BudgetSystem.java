package BudgetSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;


public class BudgetSystem {

    Repository<Transaction> transactions = new Repository<Transaction>();
    private BudgetCalculator calculator = new BudgetCalculator();

    static void main(String[] args) {

        BudgetSystem budgetSystem = new BudgetSystem();

        boolean closeApplication = false;
        String inputChoice;
        String menu = """
                
                Budgethanteraren
                ---------------------
                Alternativ:
                1. Lägg till transaktion
                2. Visa alla transaktioner
                3. Visa saldo och sammanställning per kategori
                4. Filtrera transaktioner
                5. Spara till fil
                e. Avsluta
                ----------------------
                \n""";

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
                        2. Utgift\n"""));

                if (newType == 1 || newType == 2)
                    running1 = false;
                else {
                    IO.println("Felaktig inmatning, försök igen");
                }
            } catch (Exception e) {
                LOGGER.info("User tried invalid input.");
                IO.println("Felaktig inmatning, försök igen.");
            }
        }

        boolean running2 = true;
        while (running2) {
            newCategory = IO.readln("\nAnge kategori (exempel lön, mat etc.): ");
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
            } catch (NumberFormatException e) {
                IO.println("Felaktigt värde, försök igen.");
            } catch (InvalidTransactionException e) {
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (list.isEmpty()) {
            LOGGER.info("Transaction list is empty.");
            IO.println("Det finns inga transaktioner registrerade.");
            return;
        }

        for (Transaction transaction : list) {
            IO.println(transaction.date().format(dtf) + "\t|\t"
                    + transaction.category() + "\t|\t"
                    + transaction.amount() + "\t|\t"
                    + transaction.type().displayName());
        }
    }

    private void showSummary() {
        Collection<Transaction> list = transactions.findAll();
        if (list.isEmpty()) {
            LOGGER.info("Transaction list is empty.");
            IO.println("Det finns inga transaktioner registrerade.");
            return;
        }

        double balance = calculator.totalBalance(transactions.findAll());
        IO.println("Saldo: " + balance);

        Map<TransactionType, Double> map = calculator.sumPerType(transactions.findAll());
        for (Map.Entry<TransactionType, Double> entry : map.entrySet()) {
            IO.println(entry.getKey().displayName() + ": " + entry.getValue());
        }
    }

    private void filterOptions() {

        Collection<Transaction> list = transactions.findAll();
        List<Transaction> filteredList;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        int sortChoice = 0;
        int typeChoice = 0;
        int dateChoice = 0;
        int monthChoice = 0;
        int yearChoice = 0;

        if (list.isEmpty()) {
            IO.println("Det finns inga transaktioner registrerade.");
            return;
        }

        boolean running1 = true;
        while (running1) {
            try {
                sortChoice = Integer.parseInt(IO.readln("""
                        Välj sortering:
                        1. Sortera på inkomster / utgifter
                        2. Sortera på datum\n"""));

                if (sortChoice == 1 || sortChoice == 2)
                    running1 = false;
                else {
                    IO.println("Felaktig inmatning, försök igen");
                }
            } catch (Exception e) {
                LOGGER.info("User tried invalid sort.");
                IO.println("Felaktig inmatning, försök igen");
            }
        }

        if (sortChoice == 1) {
            boolean running2 = true;
            while (running2) {
                try {
                    typeChoice = Integer.parseInt(IO.readln("""
                            Vilken typ vill du sortera på?
                            1. Inkomster
                            2. Utgifter\n"""));

                    if (typeChoice == 1) {
                        filteredList = calculator.filterByType(list, TransactionType.INCOME);
                    }
                    else if (typeChoice == 2) {
                        filteredList = calculator.filterByType(list, TransactionType.EXPENSE);
                    }
                    else {
                        IO.println("Felaktig inmatning, försök igen");
                        continue;
                    }
                        for (Transaction transaction : filteredList) {
                            IO.println(transaction.date().format(dtf) + "\t|\t"
                                    + transaction.category() + "\t|\t"
                                    + transaction.amount());
                        }
                        running2 = false;

                } catch (Exception e) {
                    LOGGER.info("User tried invalid type choice.");
                    IO.println("Felaktig inmatning, försök igen");
                }

            }
        }
        if (sortChoice == 2) {
            boolean runningYear = true;
            while (runningYear) {
                IO.println("Ange datumet du vill filtrera på:");

                try {
                    yearChoice = Integer.parseInt(IO.readln("Ange år med fyra siffror: "));
                    if (yearChoice >= 1900 &&  yearChoice <= LocalDate.now().getYear())
                        runningYear = false;
                    else if (yearChoice < 1900)
                        IO.println("Året får inte ligga före 1900.");
                    else
                        IO.println("Du kan inte söka på år efter nuvarande år.");

                }
                catch (Exception e) {
                    LOGGER.info("User tried invalid year.");
                    IO.println("Felaktig inmatning, försök igen.");
                }
            }

            boolean runningMonth = true;
            while (runningMonth) {
                try {
                    monthChoice = Integer.parseInt(IO.readln("""
                        Ange månad: 
                        1. Januari
                        2. Februari
                        3. Mars
                        4. April
                        5. Maj
                        6. Juni
                        7. Juli
                        8. Augusti
                        9. September
                        10. Oktober
                        11. November
                        12. December\n"""));

                    if (monthChoice >= 1 && monthChoice <= 12)
                        runningMonth = false;
                    else
                        IO.println("Felaktig inmatning, ange ett tal från 1 till 12.");
                }
                catch (Exception e) {
                    LOGGER.info("User tried invalid month.");
                    IO.println("Felaktig inmatning, försök igen.");
                }
            }

            YearMonth ym = YearMonth.of(yearChoice, monthChoice);
            int maxDay = ym.lengthOfMonth();
            boolean runningDate = true;
            while (runningDate) {
                try {
                    dateChoice = Integer.parseInt(IO.readln("Ange datum: "));
                    if (dateChoice >= 1 && dateChoice <= maxDay)
                        runningDate = false;
                    else
                        IO.println("Den här månaden har " + maxDay + " dagar, försök igen.");
                }
                catch (Exception e) {
                    LOGGER.info("User tried invalid date.");
                    IO.println("Felaktig inmatning, försök igen.");
                }
            }

            LocalDate filterDate = LocalDate.of(yearChoice, monthChoice, dateChoice);
            filteredList = calculator.filterByDate(list, filterDate);

            if (filteredList.isEmpty())
                IO.println("Det finns inga transaktioner för det angivna datumet.");
            else {
                IO.println("Transaktioner för " + filterDate + ":");
                for (Transaction transaction : filteredList) {
                    IO.println(transaction.date().format(dtf) + "\t|\t"
                            + transaction.category()
                            + "\t|\t" + transaction.amount());
                }
            }

        }


        }


    private void saveToFile() {

    }

    private static final Logger LOGGER = Logger.getLogger(BudgetSystem.class.getName());

}
