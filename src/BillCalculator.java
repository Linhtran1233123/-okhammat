import java.time.LocalDate;
import java.util.Scanner;

public class BillCalculator {
    private BillManager billManager;
    public BillCalculator(BillManager billManager){
        this.billManager=billManager;
    }
    public void calculateSpendingByDay(Scanner scanner){
        System.out.println("Enter day you want to display");
        LocalDate day=TryCatch.tryCatchDate(scanner);
        int sumExpectMoney=0;
        int sumRealMoney=0;
        int sumLeftMoney=0;
        for(Bill element:billManager.displayBillByDate(day,billManager.getBills())){
            sumRealMoney+=element.getRealMoney();
            sumExpectMoney+=element.getExpectMoney();
        }
        sumLeftMoney=sumRealMoney-sumExpectMoney;
        System.out.println("Summary money of day: "+day);
        displaySpendingMoney(sumExpectMoney,sumRealMoney,sumLeftMoney);
    }
    public void displaySpendingMoney(int sumExpectMoney,int sumRealMoney,int sumLeftMoney){
        System.out.printf("%-10s%s","Expect Money",sumExpectMoney);
        System.out.printf("%-10s%s","Real Money",sumRealMoney);
        System.out.printf("%-10s%s","Left Money",sumLeftMoney);
    }
}
