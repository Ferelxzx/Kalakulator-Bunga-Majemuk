import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Mbowis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));

        System.out.println("=== KALKULATOR BUNGA MAJEMUK ===");

        double principal = getPositiveDouble(scanner, "Masukkan jumlah pokok (Rp): ");
        double annualRate = getPositiveDouble(scanner, "Masukkan suku bunga tahunan (%): ");
        int years = getPositiveInt(scanner, "Masukkan durasi investasi (tahun): ");

        int compoundFrequency = chooseCompoundFrequency(scanner);

        double r = annualRate / 100;
        double total = calculateCompoundInterest(principal, r, compoundFrequency, years);

        // Tabel hasil per tahun
        System.out.println("\nTABEL PERTUMBUHAN SALDO:");
        System.out.println("-------------------------------");
        System.out.printf("%-5s%-20s\n", "Tahun", "Jumlah Uang");
        System.out.println("-------------------------------");

        for (int t = 1; t <= years; t++) {
            double amountPerYear = calculateCompoundInterest(principal, r, compoundFrequency, t);
            System.out.printf("%-5d%-20s\n", t, rupiahFormat.format(amountPerYear));
        }

        System.out.println("-------------------------------");
        System.out.println("Total akhir setelah " + years + " tahun: " + rupiahFormat.format(total));
    }

    public static double calculateCompoundInterest(double p, double r, int n, int t) {
        return p * Math.pow(1 + r / n, n * t);
    }

    public static int chooseCompoundFrequency(Scanner scanner) {
        System.out.println("\nPilih frekuensi penggabungan bunga:");
        System.out.println("1. Tahunan");
        System.out.println("2. Triwulan");
        System.out.println("3. Bulanan");
        System.out.println("4. Harian");

        while (true) {
            System.out.print("Pilihan Anda (1-4): ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: return 1;
                case 2: return 4;
                case 3: return 12;
                case 4: return 365;
                default:
                    System.out.println("Pilihan tidak valid. Masukkan angka 1-4.");
            }
        }
    }

    public static double getPositiveDouble(Scanner scanner, String prompt) {
        double value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.print("Masukkan angka yang valid: ");
                scanner.next();
            }
            value = scanner.nextDouble();
            if (value <= 0) System.out.println("Nilai harus lebih besar dari 0.");
        } while (value <= 0);
        return value;
    }

    public static int getPositiveInt(Scanner scanner, String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Masukkan angka bulat positif: ");
                scanner.next();
            }
            value = scanner.nextInt();
            if (value <= 0) System.out.println("Nilai harus lebih besar dari 0.");
        } while (value <= 0);
        return value;
    }
}

