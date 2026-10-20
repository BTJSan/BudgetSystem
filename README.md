# BudgetSystem - egen reflektion

Även om det här projektet i sin grundstruktur till stor del liknade Bibliotekshanteraren var det många nya detaljer  
som gjorde att det var mycket att få ihop. Den här gången upplevde jag dock att det var lättare att se brister  
där metoder eller klasser behövde läggas till för alla funktioner som behövdes alternativt tas bort för att  
de var överflödiga.  
  
Till stor del följde jag grundstrukturen som rekommenderades med TransactionType (enum), Transactions (record),  
Repository (generisk) samt InvalidTransactionException (extended RuntimeException).  
Utöver det lade jag till BudgetCalculator (har alla filtrerings och beräkningsmetoder) och BudgetSystem (där  
main-metoden samt övriga funktionsmetoder ligger).  
  
I början av arbetet utgick jag från ett TDD-arbetssätt, mycket för att själv träna på det men även för att jag ville  
vara säker på att alla grundläggande funktioner (lägga till transaktioner, räkna ut saldo, filtrera) skulle fungera  
som önskat. Det var svårt att bygga testerna i början, men med tiden började det släppa ganska bra. En fördel som  
jag upplevde var att jag hela tiden mer eller mindre blev tvungen att dubbelkolla koden. Det gjorde att jag ganska  
tidigt lade märke till vissa funktioner som jag först tänkte skulle vara bra att ha, men ganska snart kändes överflödiga.  
  
Att kunna ha Repository som generisk klass märktes tydligt hur användbart det var. I och med att generic-klasser kan  
anpassa sig mot vad den tar emot och själv lösa vilken data den får och ger krävs det inga större anpassningar om  
koden behöver skrivas om. Det gjorde att när den var skriven behövde den egentligen inte uppdateras efter att testerna  
godkändes. Det gjorde den flexibel, stabil och lätt att bygga vidare på. Jag kan tänka mig att det blir väldigt  
kraftfull när man bygger ännu större system som behöver fungera över längre tid.  
  
Streams var också en väldigt behändig funktion. Istället för att själv bygga upp loopar för sortering och filtrering  
kunde jag låta Javas inbyggda funktioner hantera det åt mig. Risken med att bygga egna loopar är att man missar något  
i själva logiken som behöver åtgärdas. Det krångliga med streams är att det finns så otroligt många funktioner  
att man kan missa saker, eller göra något i en ordning som inte är lika effektiv, men det gör fortfarande koden  
mer läsbar, lättanvänd och anpassningsbar om man vill strukturera om den.

- **Bugg och felsökning**
En bugg jag upptäckte var metoden *filterByDate* i BudgetCalculator.
Det som blev tokigt var när jag testkörde och ville ändra utskriften till användaren så tiden inte stod med,  
jag upplevde att det blev för mycket detaljer i utskriften. När jag började ändra det fungerade inte utskriften  
som den skulle, så jag använde Debug för att stega igenom och såg att jag missat att uppdatera metoden som anropades.  
Själva problemet var tämligen enkelt att åtgärda.
