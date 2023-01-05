import java.time.LocalDate;
import java.time.LocalTime;


public class Bill {

    private String name;
    private int expectMoney;
    private int realMoney;
    private LocalDate date;
    private LocalTime time;
    private Subject subject;
    private int leftMoney;

    public Bill(String name,int expectMoney,int realMoney, LocalDate date,LocalTime time, Subject subject) {
        this.name=name;
        this.expectMoney = expectMoney;
        this.date = date;
        this.time= time;
        this.subject = subject;
        this.realMoney=realMoney;
        this.leftMoney=expectMoney-realMoney;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpectMoney() {
        return expectMoney;
    }

    public void setExpectMoney(int expectMoney) {
        this.expectMoney = expectMoney;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getRealMoney() {
        return realMoney;
    }

    public void setRealMoney(int realMoney) {
        this.realMoney = realMoney;
    }

    public void display() {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%s", this.name, this.subject.getName(), this.date.toString(),this.time.toString(), this.expectMoney,this.realMoney,this.leftMoney + "\n");
    }
}
