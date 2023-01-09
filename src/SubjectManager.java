import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SubjectManager {
    private ArrayList<Subject> subjects;

    public SubjectManager(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public boolean check(String name) {
        for (Subject c : subjects) {
            if (c.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Subject searchSubject(String name) {
        for (Subject c : subjects) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public void addSubject(Scanner scanner) {
        System.out.println("Write name of new Subject:");
        String name = TryCatchAndRegex.tryCatchString(scanner);
        if (!name.equals("")) {
            if (!check(name)) {
                subjects.add(new Subject(name));
                System.out.println("Success to add new subject!!");
            } else {
                System.out.println("The input subject's name has already existed.Please use another name");
            }
        } else {
            System.out.println("Nothing to add");
        }
    }

    public void deleteCategory(Scanner scanner, ArrayList<Bill> bills) {
        System.out.println("Write the name of the subject you want to delete: ");
        String name = TryCatchAndRegex.tryCatchString(scanner);
        if (name.equals("")) {
            System.out.println("Nothing to delete");
            return;
        }
        if (searchSubject(name) != null) {
            System.out.println("Success to delete the subject ");
            Iterator<Bill> itr = bills.iterator();
            while (itr.hasNext()) {
                Bill bill = itr.next();
                if (bill.getSubject() == searchSubject(name)) {
                    itr.remove();
                }
            }
            subjects.remove(searchSubject(name));
        } else {
            System.out.println("Doesn't exist any subject has the input id");
        }
    }

    public void editSubjectByName(Scanner scanner) {
        System.out.println("Write the name of the subject you want to edit: ");
        String name = TryCatchAndRegex.tryCatchString(scanner);
        if (!(searchSubject(name) == null)) {
            System.out.println("Write the new name of the subject you want to edit");
            String newName = scanner.nextLine();

            searchSubject(name).setName(newName);
            System.out.println("Success to edit the subject with old name: " + name + "to new name: " + newName);
        } else {
            System.out.println("Doesn't exist any subject has the input name");
        }
    }

    public void displaySubject() {
        System.out.println("List of Subjects:");
        System.out.printf("%-10s%s", "Index", "Name" + "\n");
        for (Subject c : subjects) {
            System.out.printf("%-10s%s", subjects.indexOf(c), c.getName() + "\n");
        }
    }
}