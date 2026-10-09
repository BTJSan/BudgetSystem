package BudgetSystem;

public class BudgetSystem {
    //Todo: lägg till metoder och menyn
    //addTransaction
    //showTransactions

    static void main() {

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
                    addTransaction();
                }
                case "2" -> {
                    showTransactions();
                }
                case "3" -> {

                }
                case "4" -> {
                    //Kontrollera om resultatet blir 0 och skriv ut meddelande
                    //Annars ska resultatet skrivas ut

                }
                case "5" -> {
                    saveToFile();
                }
                case "e"  -> {
                    closeApplication = true;
                }
                default -> {
                    IO.println("Ogiltigt val.\n");
                }
            }
        }

    }




    private static void addTransaction() {

    }

    private static void showTransactions() {

    }

    private static void saveToFile() {

    }

}
