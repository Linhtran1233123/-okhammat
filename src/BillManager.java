import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillManager {
    private ArrayList<Bill> bills;
    private SubjectManager subjectManager;

    public BillManager(SubjectManager subjectManager) {
        this.subjectManager = subjectManager;
        this.bills = new ArrayList<>();
    }

    public SubjectManager getSubjectManager() {
        return subjectManager;
    }

    public void setSubjectManager(SubjectManager subjectManager) {
        this.subjectManager = subjectManager;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public void addBills(Scanner scanner) {
        System.out.println("Enter your name of new bill");
        String name = TryCatch.tryCatchString(scanner);
        System.out.println("Enter your subject of new bill");
        Subject subject = choiceSubject(scanner);
        LocalDate date = TryCatch.tryCatchDate(scanner);
        LocalTime time = TryCatch.tryCatchTime(scanner);
        System.out.println("Enter your expected money to spend of new bill");
        int expectMoney = TryCatch.tryCatchInt(scanner);
        System.out.println("Enter your real money to spend of new bill");
        int realMoney = TryCatch.tryCatchInt(scanner);
        if (!(name.equals("") || subject.getName().equals("") || date == null || time == null || expectMoney == -1 || realMoney == -1)) {
            Bill bill = new Bill(name, expectMoney, realMoney, date, time, subject);
            if (checkBill(bill)) {
                sortBillByDate(bill);
                System.out.println("Success to add new bill");
            } else {
                System.out.println("Cant exist two bill with the same name,date,time");
            }
        }else{
            System.out.println("Cant create bill has value \"\",null,-1");
        }
    }
    public void deleteBills(Scanner scanner) {
        while(true) {
            ArrayList<Bill> bills1 = searchBill(scanner);
            if(bills1== null){
                System.out.println("Nothing to delete");
                return;
            }
            if (!bills1.isEmpty()) {
                System.out.println("Sure to delete?");
                displayBill(bills1);
                System.out.println("Y to agree/Again to try again/Enter to quit");
                String input=scanner.nextLine();
                if (input.equalsIgnoreCase("y")) {
                    for (Bill b : bills1) {
                        bills.remove(b);
                    }
                    System.out.println("Success to delete");
                    return;
                }else if (input.equalsIgnoreCase("")){
                    return;
                }
            } else {
                System.out.println("Nothing to delete");
                System.out.println("Want to try again?Y to agree/enter to exit");
                if(scanner.nextLine().equals("")){
                    return;
                }
            }
        }
    }
    public void updateBills(Scanner scanner) {
        while (true) {
            ArrayList<Bill> bills1 = searchBill(scanner);
            if(bills1== null){
                System.out.println("Nothing to delete");
                return;
            }
            if (!bills1.isEmpty()) {
                System.out.println("Sure to update?");
                displayBill(bills1);
                System.out.println("Y to agree/Again to try again/Enter to quit");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("y")) {
                    for (Bill b : bills1) {
                        Bill temp = new Bill(b.getName(), b.getExpectMoney(), b.getRealMoney(), b.getDate(), b.getTime(), b.getSubject());
                        System.out.println("Enter your new name of bill(Enter to skip)");
                        String name = TryCatch.tryCatchString(scanner);
                        if (!name.equals("")) {
                            temp.setName(name);
                        }
                        System.out.println("choose your new subject of bill(Enter to skip or '1' to continue)");
                        String subjectName = TryCatch.tryCatchString(scanner);
                        if (!subjectName.equals("")) {
                            temp.setSubject(choiceSubject(scanner));
                        }
                        LocalDate date = TryCatch.tryCatchDate(scanner);
                        if (date != null) {
                            temp.setDate(date);
                        }
                        LocalTime time = TryCatch.tryCatchTime(scanner);
                        if (time != null) {
                            temp.setTime(time);
                        }
                        System.out.println("Enter your expected money to spend of new bill");
                        int expectMoney = TryCatch.tryCatchInt(scanner);
                        if (expectMoney != -1) {
                            temp.setExpectMoney(expectMoney);
                        }
                        System.out.println("Enter your real money to spend of new bill");
                        int realMoney = TryCatch.tryCatchInt(scanner);
                        if (expectMoney != -1) {
                            temp.setRealMoney(realMoney);
                        }
                        if (checkBill(temp)) {
                            b.setName(temp.getName());
                            b.setExpectMoney(temp.getExpectMoney());
                            b.setRealMoney(temp.getRealMoney());
                            b.setDate(temp.getDate());
                            b.setTime(temp.getTime());
                            b.setSubject(temp.getSubject());
                        } else {
                            System.out.println("Cant exist two bill with the same name,date,time");
                        }
                    }
                    System.out.println("Complete to update");
                    return;
                } else if (input.equalsIgnoreCase("")) {
                    return;
                }
            } else {
                System.out.println("Nothing to delete");
                System.out.println("Want to try again?Y to agree/enter to exit");
                if (scanner.nextLine().equals("")) {
                    return;
                }
            }
        }
    }
    public Subject choiceSubject(Scanner scanner) {
        subjectManager.displaySubject();
        Pattern pattern = Pattern.compile("[a-zA-Z|\\s]+");
        while (true) {
            System.out.println("Enter the name of subject you want to choose(write new name to add new subject)");
            String name = scanner.nextLine();
            Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                System.out.println("Please enter the subject just include a-z,A-Z");
            } else {
                if (subjectManager.check(name)) {
                    return subjectManager.searchSubject(name);
                } else {
                    System.out.println("Doesnt exist subject with entered name");
                    System.out.println("Do you want to create new subject with this name?");
                    System.out.println("type \"YES\" to create or \"No\" to return subject choose or \"Empty\" to get subject \"Empty\"");
                    String choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("YES")) {
                        Subject s = new Subject(name);
                        subjectManager.getSubjects().add(s);
                        System.out.println("Success to create new subject name: " + s.getName());
                        return s;
                    } else if (choice.equalsIgnoreCase("Empty")) {
                        return subjectManager.getSubjects().get(0);
                    }
                }
            }
        }
    }

    public void sortBillByDate(Bill bill) {
        LocalDate date = bill.getDate();
        LocalTime time = bill.getTime();
        for (Bill b : bills) {
            if (date.compareTo(b.getDate()) <= 0) {
                if (date.compareTo(b.getDate()) == 0 && time.compareTo(b.getTime()) <= 0) {
                    bills.add(bills.indexOf(b), bill);
                    return;
                } else if (date.compareTo(b.getDate()) < 0) {
                    bills.add(bills.indexOf(b), bill);
                    return;
                }
            }
        }
        bills.add(bill);
    }

    public ArrayList<Bill> displayBillBySubject(String subjectName, ArrayList<Bill> bills) {
        Subject subject = subjectManager.searchSubject(subjectName);
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill b : bills) {
            if (b.getSubject() == subject) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Bill> displayBillByName(String name, ArrayList<Bill> bills) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill b : bills) {
            if (b.getName().toLowerCase().contains(name.toLowerCase())) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Bill> searchBill(Scanner scanner) {
        String name = "";
        String subjectName = "";
        LocalDate date1 = null;
        LocalDate date2 = null;
        LocalTime time1 = null;
        LocalTime time2 = null;
        int choice = -1;
        ArrayList<Bill> temp;
        ArrayList<Bill> temp1=null;
        while (choice != 0) {
             temp = bills;
            System.out.println("Choose one or more following choices to find bills:");
            System.out.println("1.Enter name");
            System.out.println("2.Enter subject");
            System.out.println("3.Choice Date");
            System.out.println("4.Choice Time");
            System.out.println("5.Complete filter and Search bill with name:" + name + ",subject:" + subjectName + ",from date:" + date1 + " to date:" + date2 + ",from time:" + time1 + " to " + time2);
            System.out.println("0.Done search");
            choice = TryCatch.tryCatchInt(scanner);
            switch (choice) {
                case 1:
                    System.out.println("Name:");
                    name = TryCatch.tryCatchString(scanner);
                    break;
                case 2:
                    System.out.println("Subject 's name:");
                    subjectName = TryCatch.tryCatchString(scanner);
                    break;
                case 3:
                    System.out.println("Subject 's date or subject 's range of date:");
                    System.out.println("1.Search by exactly date");
                    System.out.println("2.Search by range of date");
                    int choice1 = TryCatch.tryCatchInt(scanner);
                    if (choice1 == 1) {
                        date1 = TryCatch.tryCatchDate(scanner);
                    }
                    if (choice1 == 2) {
                        date1 = TryCatch.tryCatchDate(scanner);
                        date2 = TryCatch.tryCatchDate(scanner);
                    }
                    break;
                case 4:
                    System.out.println("Subject 's time or subject 's range of date:");
                    System.out.println("1.Search by exactly date");
                    System.out.println("2.Search by range of date");
                    int choice2 = TryCatch.tryCatchInt(scanner);
                    if (choice2 == 1) {
                        time1 = TryCatch.tryCatchTime(scanner);
                    }
                    if (choice2 == 2) {
                        time1 = TryCatch.tryCatchTime(scanner);
                        time2 = TryCatch.tryCatchTime(scanner);
                    }
                    break;
                case 5:
                    if(!(!name.equals("")&&!subjectName.equals("")&&date1 != null&&time1 != null)){
                    if (!name.equals("")) {
                        temp = displayBillByName(name, temp);
                    }
                    if (!subjectName.equals("")) {
                        temp = displayBillBySubject(subjectName, temp);
                    }
                    if (date1 != null) {
                        if (date2 == null) {
                            temp = displayBillByDate(date1, temp);
                        } else {
                            temp = displayBillByDateRange(date1, date2, temp);
                        }
                    }
                    if (time1 != null) {
                        if (time2 == null) {
                            temp = displayBillByTime(time1, temp);
                        } else {
                            temp = displayBillByTimeRange(time1, time2, temp);
                        }
                    }
                    temp1=temp;
                    displayBill(temp);
                    }else {
                        System.out.println("Nothing to search");
                    }
                    break;
            }
        }
        return temp1;
    }

    public ArrayList<Bill> displayBillByYear(int year, ArrayList<Bill> bills) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill b : bills) {
            if (b.getDate().getYear() == year) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Bill> displayBillByTimeRange(LocalTime time1, LocalTime time2, ArrayList<Bill> bills) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill b : bills) {
            if (b.getTime().compareTo(time1) * b.getTime().compareTo(time2) <= 0) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Bill> displayBillByTime(LocalTime time, ArrayList<Bill> bills) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill b : bills) {
            if (b.getTime().compareTo(time) == 0) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Bill> displayBillByMonth(LocalDate date, ArrayList<Bill> bills) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill b : bills) {
            if (b.getDate().getMonthValue() == date.getMonthValue() && b.getDate().getYear() == date.getYear()) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Bill> displayBillByDateRange(LocalDate date1, LocalDate date2, ArrayList<Bill> bills) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill b : bills) {
            if (b.getDate().compareTo(date1) * b.getDate().compareTo(date2) <= 0) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Bill> displayBillByDate(LocalDate date, ArrayList<Bill> bills) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill b : bills) {
            if (b.getDate().compareTo(date) == 0) {
                temp.add(b);
            }
        }
        return temp;
    }
    public boolean checkBill(Bill bill){
        for (Bill b :bills){
            if(b.getName().equals(bill.getName())&&b.getDate().compareTo(bill.getDate())==0&&b.getTime().compareTo(bill.getTime())==0){
                return false;
            }
        }
        return  true;
    }
    public void displayBill(ArrayList<Bill> temp)  {
        if (!temp.isEmpty()) {
            System.out.printf("%-10s%-15s%-15s%-15s%-15s%-15s%-15s%s", "Index", "Name", "Subject", "Date", "Time", "Expect money", "Real money", "Left money" + "\n");
            int count = 0;
            for (Bill b : temp) {
                    System.out.printf("%3s%-7s", ++count, "");
                    b.display();
                }
        } else {
            System.out.println("Bills list is empty!!!");
        }
    }
}
