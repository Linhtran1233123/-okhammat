public class Subject implements  Save {
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String StringAttribute(){
        return this.name+"\n";
    }
    public static Subject create(String s){
        return new Subject(s);
    }
    public void setName(String name) {
        this.name = name;
    }
}
