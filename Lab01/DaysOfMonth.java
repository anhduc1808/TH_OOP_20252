import java.util.Scanner;

public class DaysOfMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        int days = 0;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: days = 31; break;
            case 4: case 6: case 9: case 11: days = 30; break;
            case 2: days = (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 29 : 28; break;
            default: System.out.println("Invalid month");
        }
        if (days > 0) {
            System.out.println("Number of days: " + days);
        }
        scanner.close();
    }
}