import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteReadFile {
    public static <T extends Save> void saveBill(ArrayList<T> arr,String name) {
        Path path = Paths.get("source\\"+name+".csv");
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException a) {
            System.out.println("Oop!!Something 's wrong");
        }
        try (FileWriter output = new FileWriter(path.toString())) {
            for (T element : arr) {
                output.write(element.StringAttribute());
            }
        } catch (IOException a) {
            System.out.println("Oop!!Something 's wrong");
        }
    }
    public static ArrayList<Subject> loadSubject () {
        Path path = Paths.get("source\\Subject.csv");
        if (!Files.exists(path)) {
            System.out.println("Cannot find the \"Bill.csv\" to load");
        } else {
            try (BufferedReader in = new BufferedReader(new FileReader(path.toString()))) {
                ArrayList<Subject> arr=new ArrayList<>();
                String s;
                while ((s=in.readLine())!= null){
                    arr.add(Subject.create(s));
                }
                return arr;
            } catch (IOException a) {
                System.out.println("Oop!!Something 's wrong");
            }
        }
        return null;
    }
    public static ArrayList<Bill> loadBill (SubjectManager subjectManager) {
        Path path = Paths.get("source\\Bill.csv");
        if (!Files.exists(path)) {
            System.out.println("Cannot find the \"Bill.csv\" to load");
        } else {
            try (BufferedReader in = new BufferedReader(new FileReader(path.toString()))) {
                ArrayList<Bill> arr=new ArrayList<>();
                String s;
                while ((s=in.readLine())!= null){
                    arr.add(Bill.create(s,subjectManager));
                }
                return arr;
            } catch (IOException a) {
                System.out.println("Oop!!Something 's wrong");
            }
        }
        return null;
    }
}
