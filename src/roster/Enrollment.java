package roster;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public void add(EnrollStudent enrollStudent) {

    }
    public void remove(EnrollStudent enrollStudent) {

    }
    public boolean contains(EnrollStudent enrollStudent) {
        return false;
    }
    public void print() {
        for(int i = 0; i < size; i++) {
            System.out.println(enrollStudents[i].toString());
        }
    }
}
