import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TryCatchAndRegex {
    public static int tryCatchInt(Scanner scanner) {
        while (true) {
            try {
                String number = scanner.nextLine();
                if (number.equals("")) {
                    return -1;
                }
                return Integer.parseInt(number);
            } catch (NumberFormatException a) {
                System.out.println(a.getMessage());
                System.out.println("Please enter right number format input :");
            } catch (NoSuchElementException b) {
                b.fillInStackTrace();
            }
        }
    }

    public static String tryCatchString(Scanner scanner) {
        Pattern pattern = Pattern.compile("[a-zA-Z|\\s]+");
        while (true) {
            String name = scanner.nextLine();
            if (name.equals("")) {
                return "";
            }
            Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                System.out.println("Please enter the name just include a-z,A-Z");
            } else {
                return name;
            }
        }
    }

    public static LocalDate tryCatchMonth(Scanner scanner) {
        Pattern pattern = Pattern.compile("(\\d|0[1-9]|1[0-2])[-|/]\\d{4}");
        while (true) {
            System.out.println("Enter the month of bills you want to display(Example:11/1996 or 11-1996 ..)");
            try {
                String date = scanner.nextLine();
                if (date.equals("")) {
                    return null;
                }
                Matcher matcher = pattern.matcher(date);
                if (matcher.matches()) {
                    Scanner s = new Scanner(date);
                    s.findInLine("(\\d+)[-/](\\d+)");
                    MatchResult result = s.match();
                    int month = Integer.parseInt(result.group(1));
                    int year = Integer.parseInt(result.group(2));
                    s.close();
                    return LocalDate.of(year, month, 1);
                } else {
                    System.out.println("Please enter the month with format: mm/yy");
                }
            } catch (NoSuchElementException b) {
                b.fillInStackTrace();
            }
        }
    }

    public static LocalDate tryCatchDate(Scanner scanner) {
        Pattern pattern = Pattern.compile("([1-9]|[0-2][0-9]|3[0-1])[-|/]([1-9]|0[1-9]|1[0-2])[-|/]\\d{4}");
        while (true) {
            System.out.println("Enter the date of bills you want (Example:12/11/1996 or 12-11-1996 ..)");
            try {
                String date = scanner.nextLine();
                if (date.equals("")) {
                    return null;
                }
                Matcher matcher = pattern.matcher(date);
                if (matcher.matches()) {
                    Scanner s = new Scanner(date);
                    s.findInLine("(\\d+)[-/](\\d+)[-/](\\d+)");
                    MatchResult result = s.match();
                    int day = Integer.parseInt(result.group(1));
                    int month = Integer.parseInt(result.group(2));
                    int year = Integer.parseInt(result.group(3));
                    s.close();
                    return LocalDate.of(year, Month.of(month), day);
                } else {
                    System.out.println("Please enter the date with format: dd/mm/yy");
                }
            } catch (NoSuchElementException b) {
                b.fillInStackTrace();
            }
        }
    }

    public static LocalTime tryCatchTime(Scanner scanner) {
        Pattern pattern = Pattern.compile("(\\d|([0-1][0-9])|(2[0-3])):(\\d|([0-5][0-9])):(\\d|([0-5][0-9]))");
        while (true) {
            System.out.println("Enter the time of bills you want to display(Format: hh:mm:ss .Example: 00:00:00 ..)");
            try {
                String time = scanner.nextLine();
                if (time.equals("")) {
                    return null;
                }
                Matcher matcher = pattern.matcher(time);
                if (matcher.matches()) {
                    Scanner s = new Scanner(time);
                    s.findInLine("(\\d+):(\\d+):(\\d+)");
                    MatchResult result = s.match();
                    int hrs = Integer.parseInt(result.group(1));
                    int min = Integer.parseInt(result.group(2));
                    int sec = Integer.parseInt(result.group(3));
                    s.close();
                    return LocalTime.of(hrs, min, sec);
                } else {
                    System.out.println("Please enter the time with format: hh:mm:ss");
                }
            } catch (NoSuchElementException b) {
                b.fillInStackTrace();
            }
        }
    }
}