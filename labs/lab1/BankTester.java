/*
Треба да се креира апликација за банка која ќе управуваа со сметките на повеќе корисниците и ќе врши трансакции помеѓу нив. Банката работи само со долари.

За потребите на ваквата апликација треба да се напишат класите Account,Transaction и Bank. Класата Account претставува една сметка на еден корисник и треба да ги чува следните податоци:

Име на корисникот,
единствен идентификационен број (long)
тековното салдо на сметката (реален број).
Оваа класа исто така треба да ги имплементира и следниве методи

Account(String name, double balance) – конструктор со параметри (id-то треба да го генерирате сами со помош на класата java.util.Random)
getBalance():double
getName():String
getId():long
setBalance(double balance)
toString():String – враќа стринг во следниот формат, \n означува нов ред
Name:Andrej Gajduk\n
Balance:20.00$\n
Класата Transaction претставува трансакција (префрлување пари од една на друга сметка), од страна на банката за што честопати се наплаќа провизија. За почеток треба да се напише класата Transaction со податочни членови за идентификационите броеви на две сметки, едната од која се одземаат парите и друга на која се додаваат парите, текстуален опис и износ на трансакцијата.

За оваа класа треба да ги имплементирате методите:

Transaction(long fromId, long toId, Stirng description, double amount) – конструктор со параметри
getAmount():double
getFromId():long
getToId():long
Оваа класа треба да е immutable, а можете и да ја направите и апстрактна бидејќи не е наменета директно да се користи туку само како основна класа за изведување на други класи.

Како што споменавме претходно банката наплаќа провизија за одредени трансакции. Има два типа на провизија, фискна сума и процент. Кај фиксна сума за било која трансакција без разлика на износот на трансакцијата се наплаќа исто провизија (пример 10$). Кај процент се пресметува процент од целиот износ (процентите се зададени како цели броеви од 1-100).

За да се прави разлика меѓу различните типови на провизија, треба да напишете уште две класи кои ќе наследуваат од Transaction кои треба да ги именувате FlatAmountProvisionTransaction и FlatPercentProvisionTransaction.

Првата класа FlatAmountProvisionTransaction треба да содржи соодветен конструктор

FlatAmountProvisionTransaction(long fromId, long toId,double amount, double flatProvision) кој го иницијализира полето за опис на "FlatAmount" и соодветен get метод
getFlatAmount():double
Слично и класата FlatPercentProvisionTransaction треба да има соодветен конструктор

FlatPercentProvisionTransaction (long fromId, long toId, double amount, int centsPerDolar) кој го иницијализира полето за опис на "FlatPercent" и соодветен get метод
getPercent():int
Исто така треба да се преоптовари equals(Object o):boolean методот и за двете класи.

За крај треба да ја имплементирате класата Bank која ги чува сметките од своите корисници и дополнително врши трансакции. Класата освен сметките на своите корисници, треба да ги чува и сопственото име и вкупната сума на трансфери како и вкупната наплатена провизија од страна на банката за сите трансакции.

Класата Bank треба да ги нуди следните методи:

Bank(String name, Account accounts[]) – конструктор со соодветните параметри (направете сопствена копија на низата од сметки)
makeTransaction(Transaction t):boolean – врши проверка дали корисникот ги има потребните средства на сметка и дали и двете сметки на кои се однесува трансакцијата се нависитина во банката и ако и двата услови се исполнето ја извршува трансакцијата и враќа true, во спротивно враќа false
totalTransfers():double – ја дава вкупната сума на пари кои се префрлени во сите трансакции до сега
totalProvision():double – ја дава вкупната провизија наплатена од банката за сите извршени трансакции до сега
toString():String - го враќа името на банката во посебна линија во формат
Name:Banka na RM\n
\n
по што следат податоците за сите корисници.

Провизијата се наплаќа така што на основната сума на трансакцијата се додава вредноста не провизијата и таа сума се одзема од првата сметка (праќачот).

За сите класи да се напишат соодветни equals и hashCode методи.

Input	
typical_usage
Banka na RM
50
Alana Pagel
96124963.59$
Kathe Vath
104071827.99$
Sara Flenniken
103489869.36$
Shauna Irick
99230857.77$
Leon Brokaw
100949324.96$
Guillermina Haymond
101372350.52$
Sherie Pestana
100772483.38$
Shirlene Reichard
98451795.79$
Jerrica Kafka
100608943.69$
Nida Garg
96374183.01$
Ellamae Marshell
101834753.75$
Ranee Ricca
96386667.01$
Elois Philip
96767866.34$
Roland June
99245215.19$
Marvis Lemarr
95732058.60$
Burl Friese
100232415.35$
Reggie Olah
104652507.25$
Isis Ballard
100717280.66$
Bernardine Munford
104257015.83$
Johnette Greenhaw
103471815.46$
Olimpia Ryan
103239439.43$
Sha Duguay
96926529.60$
Donn Kurt
100902436.77$
Joel Cao
96522430.27$
Jamika Epstein
104102902.74$
Moira Haar
102396011.82$
Tiffani Bellantoni
98824115.92$
Tennille Hanzlik
101943340.74$
Dwayne Bazile
96723845.51$
Luigi Blaise
103007704.71$
Paris Fagan
100668747.81$
Ngan Raynor
100211914.45$
Alden Mirarchi
102954898.08$
Tangela Fernandez
99531915.08$
Liza Breaux
103806099.54$
Annice Lipman
97834836.56$
Broderick Arcand
102290753.08$
Terry Cygan
97565192.65$
Adriene Janz
104240885.04$
Joaquin Larocque
96872074.82$
Sabrina Hilger
102611005.33$
Glayds Hinch
101573590.11$
Nereida Mickley
101278626.39$
Elaina Hupp
103829407.46$
Karissa Brousseau
98142582.89$
America Kyle
99146314.73$
Lucie Nakata
102881106.42$
Chantel Grewell
96314595.10$
Logan Hogans
97531477.92$
Herb Mojarro
96535514.77$
transaction
FlatPercent
97854.22$
20
4 33
transaction
FlatAmount
98091.14$
10273.03$
19 9
transaction
FlatAmount
97781.88$
10029.55$
27 41
transaction
FlatPercent
104642.02$
45
30 1
transaction
FlatAmount
95760.77$
9811.63$
7 20
transaction
FlatAmount
101486.10$
10377.16$
11 24
transaction
FlatAmount
99204.78$
9813.90$
17 17
transaction
FlatAmount
102725.61$
9895.83$
3 25
transaction
FlatAmount
96297.44$
10480.20$
43 4
transaction
FlatAmount
98375.44$
10360.05$
18 27
print
stop

Result
Transaction amount: 97854.22$
Transaction description: FlatPercent
Transaction successful? true
Transaction amount: 98091.14$
Transaction description: FlatAmount
Transaction successful? true
Transaction amount: 97781.88$
Transaction description: FlatAmount
Transaction successful? true
Transaction amount: 104642.02$
Transaction description: FlatPercent
Transaction successful? true
Transaction amount: 95760.77$
Transaction description: FlatAmount
Transaction successful? true
Transaction amount: 101486.10$
Transaction description: FlatAmount
Transaction successful? true
Transaction amount: 99204.78$
Transaction description: FlatAmount
Transaction successful? true
Transaction amount: 102725.61$
Transaction description: FlatAmount
Transaction successful? true
Transaction amount: 96297.44$
Transaction description: FlatAmount
Transaction successful? true
Transaction amount: 98375.44$
Transaction description: FlatAmount
Transaction successful? true
Name: Banka na RM

Name: Alana Pagel
Balance: 96124963.59$
Name: Kathe Vath
Balance: 104176470.01$
Name: Sara Flenniken
Balance: 103489869.36$
Name: Shauna Irick
Balance: 99118236.33$
Name: Leon Brokaw
Balance: 100928197.34$
Name: Guillermina Haymond
Balance: 101372350.52$
Name: Sherie Pestana
Balance: 100772483.38$
Name: Shirlene Reichard
Balance: 98346223.39$
Name: Jerrica Kafka
Balance: 100608943.69$
Name: Nida Garg
Balance: 96472274.15$
Name: Ellamae Marshell
Balance: 101834753.75$
Name: Ranee Ricca
Balance: 96274803.75$
Name: Elois Philip
Balance: 96767866.34$
Name: Roland June
Balance: 99245215.19$
Name: Marvis Lemarr
Balance: 95732058.60$
Name: Burl Friese
Balance: 100232415.35$
Name: Reggie Olah
Balance: 104652507.25$
Name: Isis Ballard
Balance: 100707466.76$
Name: Bernardine Munford
Balance: 104148280.34$
Name: Johnette Greenhaw
Balance: 103363451.29$
Name: Olimpia Ryan
Balance: 103335200.20$
Name: Sha Duguay
Balance: 96926529.60$
Name: Donn Kurt
Balance: 100902436.77$
Name: Joel Cao
Balance: 96522430.27$
Name: Jamika Epstein
Balance: 104204388.84$
Name: Moira Haar
Balance: 102498737.43$
Name: Tiffani Bellantoni
Balance: 98824115.92$
Name: Tennille Hanzlik
Balance: 101933904.75$
Name: Dwayne Bazile
Balance: 96723845.51$
Name: Luigi Blaise
Balance: 103007704.71$
Name: Paris Fagan
Balance: 100517016.88$
Name: Ngan Raynor
Balance: 100211914.45$
Name: Alden Mirarchi
Balance: 102954898.08$
Name: Tangela Fernandez
Balance: 99629769.30$
Name: Liza Breaux
Balance: 103806099.54$
Name: Annice Lipman
Balance: 97834836.56$
Name: Broderick Arcand
Balance: 102290753.08$
Name: Terry Cygan
Balance: 97565192.65$
Name: Adriene Janz
Balance: 104240885.04$
Name: Joaquin Larocque
Balance: 96872074.82$
Name: Sabrina Hilger
Balance: 102611005.33$
Name: Glayds Hinch
Balance: 101671371.99$
Name: Nereida Mickley
Balance: 101278626.39$
Name: Elaina Hupp
Balance: 103722629.82$
Name: Karissa Brousseau
Balance: 98142582.89$
Name: America Kyle
Balance: 99146314.73$
Name: Lucie Nakata
Balance: 102881106.42$
Name: Chantel Grewell
Balance: 96314595.10$
Name: Logan Hogans
Balance: 97531477.92$
Name: Herb Mojarro
Balance: 96535514.77$

Total provisions: 147701.10$
Total tr



*/


import java.util.*;
import java.util.stream.Collectors;


class Account {
private String name;
private long id;
private double balance;

    public Account(String name, long id, double balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
    }

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.id = new Random().nextLong();
    }

    public double getBalance() {
        return balance;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nBalance: %.2f$\n", name, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Double.compare(balance, account.balance) == 0 && Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, balance);
    }
}


abstract class Transaction {
    private long fromId;
    private long toId;
    private String description;
    private double amount;

    public Transaction(long fromId, long toId, String description, double amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.description = description;
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public String getDescription() {
        return description;
    }

    public abstract double getProvision();
}
class  FlatAmountProvisionTransaction extends Transaction{
    private double flatAmount;

    public FlatAmountProvisionTransaction(long fromId, long toId, double amount, double flatProvision) {
        super(fromId, toId, "FlatAmount", amount);
        this.flatAmount = flatProvision;
    }

    public double getFlatAmount() {
        return flatAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatAmountProvisionTransaction that = (FlatAmountProvisionTransaction) o;
        return Double.compare(flatAmount, that.flatAmount) == 0 && getToId()==that.getToId() && getFromId()==that.getFromId() && Double.compare(getAmount(), that.getAmount())==0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFromId(), getToId(), getAmount(), flatAmount);
    }
    @Override
    public double getProvision() {
        return flatAmount;
    }
}
class FlatPercentProvisionTransaction extends Transaction {
    private int percent;

    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, int centsPerDolar) {
        super(fromId, toId, "FlatPercent", amount);
        this.percent = centsPerDolar;
    }

    public int getPercent() {
        return percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
        return percent == that.percent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFromId(), getToId(), getAmount(), percent);
    }
    @Override
    public double getProvision() {
        return getAmount() * percent / 100.0;
    }

}


class Bank {
private String name;
private Account[] accounts;
private double amount_of_transfers;
private double total_commission_charged;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts=Arrays.copyOf(accounts,accounts.length);
        this.amount_of_transfers = 0.0;
        this.total_commission_charged = 0.0;
    }
    public boolean makeTransaction(Transaction t) {
        long fromId = t.getFromId();
        long toId = t.getToId();
        double amount = t.getAmount();
        Account acc1 = null;
        Account acc2=null;
        boolean bool1 = false;
        boolean bool2 = false;
        for (int i = 0; i < accounts.length; i++) {
            Account acc = accounts[i];
            if (acc.getId() == fromId) {
                bool1 = true;
                acc1 = acc;
                break;
            }

        }
        if (!bool1)
            return false;


        for (int i = 0; i < accounts.length; i++) {
            Account acc = accounts[i];
            if (acc.getId() == toId) {
                bool2 = true;
                acc2 = acc;
                break;
            }

        }
        if (!bool2)
            return false;
double totalAmount=t.getAmount()+t.getProvision();
if(acc1.getBalance()<totalAmount)
    return false;
acc1.setBalance(acc1.getBalance()-totalAmount);
acc2.setBalance(acc2.getBalance()+t.getAmount());

        amount_of_transfers +=t.getAmount();
        total_commission_charged +=t.getProvision();

return true;
    }

    public double totalTransfers() {
        return amount_of_transfers;

    }

    public double totalProvision() {
        return  total_commission_charged;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n\n");
        for (Account account : accounts) {
            sb.append(account.toString());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(amount_of_transfers, bank.amount_of_transfers) == 0 && Double.compare(total_commission_charged, bank.total_commission_charged) == 0 && Objects.equals(name, bank.name) && Objects.deepEquals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(accounts), amount_of_transfers, total_commission_charged);
    }
    public Account[] getAccounts() {
        return accounts;
    }
}


public class BankTester {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        String test_type = jin.nextLine();
        switch (test_type) {
            case "typical_usage":
                testTypicalUsage(jin);
                break;
            case "equals":
                testEquals();
                break;
        }
        jin.close();
    }

    private static double parseAmount (String amount){
        return Double.parseDouble(amount.replace("$",""));
    }

    private static void testEquals() {
        Account a1 = new Account("Andrej", 20.0);
        Account a2 = new Account("Andrej", 20.0);
        Account a3 = new Account("Andrej", 30.0);
        Account a4 = new Account("Gajduk", 20.0);
        List<Account> all = Arrays.asList(a1, a2, a3, a4);
        if (!(a1.equals(a1)&&!a1.equals(a2)&&!a2.equals(a1)&&!a3.equals(a1)
                && !a4.equals(a1)
                && !a1.equals(null))) {
            System.out.println("Your account equals method does not work properly.");
            return;
        }
        Set<Long> ids = all.stream().map(Account::getId).collect(Collectors.toSet());
        if (ids.size() != all.size()) {
            System.out.println("Different accounts have the same IDS. This is not allowed");
            return;
        }
        FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, 20.0, 10.0);
        FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(20, 20, 20.0, 10.0);
        FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, 20.0, 10.0);
        FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, 50.0, 50.0);
        FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, 20.0, 10.0);
        FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, 20.0, 10);
        FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, 20.0, 10);
        FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(10, 10, 20.0, 10);
        FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, 50.0, 10);
        FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, 20.0, 30);
        FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, 20.0, 10);
        if (fa1.equals(fa1) &&
                !fa2.equals(null) &&
                fa2.equals(fa1) &&
                fa1.equals(fa2) &&
                fa1.equals(fa3) &&
                !fa1.equals(fa4) &&
                !fa1.equals(fa5) &&
                !fa1.equals(fp1) &&
                fp1.equals(fp1) &&
                !fp2.equals(null) &&
                fp2.equals(fp1) &&
                fp1.equals(fp2) &&
                fp1.equals(fp3) &&
                !fp1.equals(fp4) &&
                !fp1.equals(fp5) &&
                !fp1.equals(fp6)) {
            System.out.println("Your transactions equals methods do not work properly.");
            return;
        }
        Account accounts[] = new Account[]{a1, a2, a3, a4};
        Account accounts1[] = new Account[]{a2, a1, a3, a4};
        Account accounts2[] = new Account[]{a1, a2, a3};
        Account accounts3[] = new Account[]{a1, a2, a3, a4};

        Bank b1 = new Bank("Test", accounts);
        Bank b2 = new Bank("Test", accounts1);
        Bank b3 = new Bank("Test", accounts2);
        Bank b4 = new Bank("Sample", accounts);
        Bank b5 = new Bank("Test", accounts3);

        if (!(b1.equals(b1) &&
                !b1.equals(null) &&
                !b1.equals(b2) &&
                !b2.equals(b1) &&
                !b1.equals(b3) &&
                !b3.equals(b1) &&
                !b1.equals(b4) &&
                b1.equals(b5))) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        accounts[2] = a1;
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        long from_id = a2.getId();
        long to_id = a3.getId();
        Transaction t = new FlatAmountProvisionTransaction(from_id, to_id, 3.0, 3.0);
        b1.makeTransaction(t);
        if (b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        b5.makeTransaction(t);
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        System.out.println("All your equals methods work properly.");
    }

    private static void testTypicalUsage(Scanner jin) {
        String bank_name = jin.nextLine();
        int num_accounts = jin.nextInt();
        jin.nextLine();
        Account accounts[] = new Account[num_accounts];
        for (int i = 0; i < num_accounts; ++i)
            accounts[i] = new Account(jin.nextLine(),  parseAmount(jin.nextLine()));
        Bank bank = new Bank(bank_name, accounts);
        while (true) {
            String line = jin.nextLine();
            switch (line) {
                case "stop":
                    return;
                case "transaction":
                    String descrption = jin.nextLine();
                    double amount = parseAmount(jin.nextLine());
                    double parameter = parseAmount(jin.nextLine());
                    int from_idx = jin.nextInt();
                    int to_idx = jin.nextInt();
                    jin.nextLine();
                    Transaction t = getTransaction(descrption, from_idx, to_idx, amount, parameter, bank);
                    System.out.println("Transaction amount: " + String.format("%.2f$",t.getAmount()));
                    System.out.println("Transaction description: " + t.getDescription());
                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
                    break;
                case "print":
                    System.out.println(bank.toString());
                    System.out.println("Total provisions: " + String.format("%.2f$",bank.totalProvision()));
                    System.out.println("Total transfers: " + String.format("%.2f$",bank.totalTransfers()));
                    System.out.println();
                    break;
            }
        }
    }

    private static Transaction getTransaction(String description, int from_idx, int to_idx, double amount, double o, Bank bank) {
        switch (description) {
            case "FlatAmount":
                return new FlatAmountProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, o);
            case "FlatPercent":
                return new FlatPercentProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, (int) o);
        }
        return null;
    }


}
