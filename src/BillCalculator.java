import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BillCalculator {
    private final BillManager billManager;
    public BillCalculator(BillManager billManager){
        this.billManager=billManager;
    }
    public void calculateSpendingByDay(Scanner scanner){
        class MoneySubject {
            final Subject subject;
            int expectMoney=0;
            int realMoney=0;
            int leftMoney;
            MoneySubject (Subject subject){
                this.subject=subject;
            }
        }
        ArrayList<MoneySubject> moneySubjects = new ArrayList<>();
        for (Subject element: billManager.getSubjectManager().getSubjects()) {
            moneySubjects.add(new MoneySubject(element));
        }
        LocalDate date = TryCatchAndRegex.tryCatchDate(scanner);
        if (date != null) {
            int sumExpectMoney =0;
            int sumRealMoney = 0;
            int sumLeftMoney;
            ArrayList<Bill> bills=billManager.displayBillByDate(date, billManager.getBills());
            if(!bills.isEmpty()) {
                for (Bill element : bills) {
                    sumRealMoney += element.getRealMoney();
                    sumExpectMoney += element.getExpectMoney();
                    for (MoneySubject element1 : moneySubjects) {
                        if (element1.subject == element.getSubject()) {
                            element1.expectMoney += element.getExpectMoney();
                            element1.realMoney += element.getRealMoney();
                            element1.leftMoney = element1.expectMoney - element1.realMoney;
                            break;
                        }
                    }
                }
                sumLeftMoney = sumRealMoney - sumExpectMoney;
                System.out.println("Summary expect money,real money spend in date:" + date);
                System.out.printf("%-20s%-20s%-20s%s","Subjects","Expected money","Real money","Left money"+"\n");
                for (MoneySubject element: moneySubjects){
                    System.out.printf("%-20s%5s%-15s%3s%-19s%5s",element.subject.getName(),element.expectMoney,"",element.realMoney,"",element.leftMoney+"\n");
                }
                System.out.printf("%-20s%5s%-15s%3s%-19s%5s","Summary",sumRealMoney,"",sumExpectMoney,"",sumLeftMoney+"\n");
            }else{
                System.out.println("Nothing to display");
            }
        }else {
            System.out.println("Pls enter content ");
        }
    }
    public void calculateSpendingByMonth(Scanner scanner){
        class MoneySubject {
            final Subject subject;
            int expectMoney=0;
            int realMoney=0;
            int leftMoney;
            MoneySubject (Subject subject){
                this.subject=subject;
            }
        }
        ArrayList<MoneySubject> moneySubjects = new ArrayList<>();
        for (Subject element: billManager.getSubjectManager().getSubjects()) {
            moneySubjects.add(new MoneySubject(element));
        }
        LocalDate month = TryCatchAndRegex.tryCatchMonth(scanner);
        if (month != null) {
            int sumExpectMoney =0;
            int sumRealMoney = 0;
            int sumLeftMoney;
            ArrayList<Bill> bills=billManager.displayBillByMonth(month, billManager.getBills());
            if(!bills.isEmpty()) {
                for (Bill element : bills) {
                    sumRealMoney += element.getRealMoney();
                    sumExpectMoney += element.getExpectMoney();
                    for (MoneySubject element1 : moneySubjects) {
                        if (element1.subject == element.getSubject()) {
                            element1.expectMoney += element.getExpectMoney();
                            element1.realMoney += element.getRealMoney();
                            element1.leftMoney = element1.expectMoney - element1.realMoney;
                            break;
                        }
                    }
                }
                sumLeftMoney = sumRealMoney - sumExpectMoney;
                System.out.println("Summary expect money,real money spend in month" + month.getMonthValue() + "/" +month.getYear());
                System.out.printf("%-20s%-20s%-20s%s","Subjects","Expected money","Real money","Left money"+"\n");
                for (MoneySubject element: moneySubjects){
                    System.out.printf("%-20s%5s%-15s%3s%-19s%5s",element.subject.getName(),element.expectMoney,"",element.realMoney,"",element.leftMoney+"\n");
                }
                System.out.printf("%-20s%5s%-15s%3s%-19s%5s","Summary",sumRealMoney,"",sumExpectMoney,"",sumLeftMoney+"\n");
            }else{
                System.out.println("Nothing to display");
            }
        }else {
            System.out.println("Pls enter content ");
        }
    }

    public void calculateSpendingByYear(Scanner scanner){
        class MoneySubject {
            final Subject subject;
            int expectMoney=0;
            int realMoney=0;
            int leftMoney;
            MoneySubject (Subject subject){
                this.subject=subject;
            }
        }
        ArrayList<MoneySubject> moneySubjects = new ArrayList<>();
        for (Subject element: billManager.getSubjectManager().getSubjects()) {
            moneySubjects.add(new MoneySubject(element));
        }
        int year = TryCatchAndRegex.tryCatchInt(scanner);
        if (year != 0) {
            int sumExpectMoney =0;
            int sumRealMoney = 0;
            int sumLeftMoney;
            ArrayList<Bill> bills=billManager.displayBillByYear(year, billManager.getBills());
            if(!bills.isEmpty()) {
                for (Bill element : bills) {
                    sumRealMoney += element.getRealMoney();
                    sumExpectMoney += element.getExpectMoney();
                    for (MoneySubject element1 : moneySubjects) {
                        if (element1.subject == element.getSubject()) {
                            element1.expectMoney += element.getExpectMoney();
                            element1.realMoney += element.getRealMoney();
                            element1.leftMoney = element1.expectMoney - element1.realMoney;
                            break;
                        }
                    }
                }
                sumLeftMoney = sumRealMoney - sumExpectMoney;
                System.out.println("Summary expect money,real money spend in year:" + year);
                System.out.printf("%-20s%-20s%-20s%s","Subjects","Expected money","Real money","Left money"+"\n");
                for (MoneySubject element: moneySubjects){
                    System.out.printf("%-20s%5s%-15s%3s%-19s%5s",element.subject.getName(),element.expectMoney,"",element.realMoney,"",element.leftMoney+"\n");
                }
                System.out.printf("%-20s%5s%-15s%3s%-19s%5s","Summary",sumRealMoney,"",sumExpectMoney,"",sumLeftMoney+"\n");
            }else{
                System.out.println("Nothing to display");
            }
        }else {
            System.out.println("Pls enter content ");
        }
    }
}
