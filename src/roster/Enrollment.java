package roster;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public Enrollment() {
        enrollStudents = new EnrollStudent[Constants.ROSTER_CAPACITY];
        size = 0;
    }

    private int find(EnrollStudent student) {
        for (int i = 0; i < size; i++) {
            if (student.equals(enrollStudents[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    private void grow() {
        EnrollStudent[] new_enrollStudents = new EnrollStudent[enrollStudents.length + Constants.ROSTER_INC];
        for (int i = 0; i < size; i++) {
            new_enrollStudents[i] = enrollStudents[i];
        }
        enrollStudents = new_enrollStudents;
    }

    public void add(EnrollStudent enrollStudent) {
        if(size+1 == enrollStudents.length) {
            this.grow();
        }
        enrollStudents[size] = enrollStudent;
        size++;
    }
    public void remove(EnrollStudent enrollStudent) {
        int index = find(enrollStudent);
        if(index != Constants.NOT_FOUND && index != size - 1) {
            enrollStudents[index] = enrollStudents[size-1];
            enrollStudents[size-1] = null;
            size--;
        }
        else if(index == size-1) {
            enrollStudents[index] = null;
            size--;
        }
    }
    public boolean contains(EnrollStudent enrollStudent) {
        return find(enrollStudent) != Constants.NOT_FOUND;
    }
    public void print() {
        for(int i = 0; i < size; i++) {
            System.out.println(enrollStudents[i].toString());
        }
    }
}
