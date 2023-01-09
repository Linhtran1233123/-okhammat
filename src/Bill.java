import java.time.LocalDate;
import java.time.LocalTime;


public class Bill implements Save {

    private String name;
    private int expectMoney;
    private int realMoney;
    private LocalDate date;
    private LocalTime time;
    private Subject subject;
    private final int leftMoney;

    public Bill(String name, int expectMoney, int realMoney, LocalDate date, LocalTime time, Subject subject) {
        this.name = name;
        this.expectMoney = expectMoney;
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.realMoney = realMoney;
        this.leftMoney = expectMoney - realMoney;
    }

    public static Bill create(String s, SubjectManager subjectManager) {
        String[] s1 = s.split(",");
        String name = s1[0];
        Subject subject = subjectManager.searchSubject(s1[1]);
        String[] s2 = s1[2].split("[-|/]");
        LocalDate date = LocalDate.of(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]), Integer.parseInt(s2[2]));
        String[] s3 = s1[3].split(":");
        LocalTime time1;
        if (s3.length == 3) {
            time1 = LocalTime.of(Integer.parseInt(s3[0]), Integer.parseInt(s3[1]), Integer.parseInt(s3[2]));
        } else {
            time1 = LocalTime.of(Integer.parseInt(s3[0]), Integer.parseInt(s3[1]), 0);
        }
        int expectMoney = Integer.parseInt(s1[4]);
        int realMoney = Integer.parseInt(s1[5]);
        return new Bill(name, expectMoney, realMoney, date, time1, subject);
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
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s%s", this.name, this.subject.getName(), this.date.toString(), this.time.toString(), this.expectMoney, this.realMoney, this.leftMoney + "\n");
    }

    public String StringAttribute() {
        return this.name + "," + this.subject.getName() + "," + this.date.toString() + "," + this.time.toString() + "," + this.expectMoney + "," + this.realMoney + "," + this.leftMoney + "\n";
    }
}
