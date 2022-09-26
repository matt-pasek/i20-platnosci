import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);
    public static final double amount = (double) Math.floor(
            Math.random() * 100000.0
    ) /
            100.0;

    public static void main(String[] args) {
        switch (selectPaymentMethod()) {
            case 1 -> payByCash();
            case 2 -> payByCard();
            case 3 -> payByBlik();
        }
    }

    public static int selectPaymentMethod() {
        int payment = 0;
        System.out.println("Do zapłaty: " + amount);
        System.out.println(
                "Podaj sposób płatności: \n1. Gotówka \n2. Karta \n3. BLIK"
        );
        try {
            payment = Integer.parseInt(scanner.next());
            if (payment <= 3 && payment >= 1) {
                return payment;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Podana wartość jest poza zakresem [1..3]");
            selectPaymentMethod();
        }
        return 0;
    }

    public static void payByCash() {
        int sum = 0;
        System.out.print("Proszę podać banknoty: ");
        String[] banknotes = scanner.next().split(";");
        for (String banknote : banknotes) {
            try {
                sum += Math.max(Integer.parseInt(banknote), 0); // ignore negative values
            } catch (Exception e) {
                System.out.println("Nieprawidłowa wartość banknotu: " + banknote);
                payByCash();
            }
        }
        validateSum(sum);
    }

    public static void validateSum(int sum) {
        if (sum >= amount) {
            System.out.println(
                    "Płatność przyjęta\nReszta wynosi: " + Math.floor((sum - amount) * 100) / 100 + "\nDrukowanie paragonu"
            );
        } else {
            System.out.println(
                    "Za mało pieniędzy!\nDołóż banknoty albo\nwpisz X żeby anulować\nwpisz Z żeby wybrać inny sposób płatności"
            );
            String input = scanner.next();
            switch (input) {
                case "X", "x" -> {
                    System.out.println("Anulowano");
                }
                case "Z", "z" -> selectPaymentMethod();
                default -> {
                    for (String banknote : input.split(";")) {
                        sum += Integer.parseInt(banknote);
                    }
                    validateSum(sum);
                }
            }
        }
    }

    public static void payByCard() {
        System.out.println("Płatność kartą - WIP");
    }

    public static void payByBlik() {
        System.out.println("Płatność BLIK - WIP");
    }
}
