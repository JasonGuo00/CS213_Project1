package roster;

/**
 * Object to store and handle enrollStudent objects.
 * Contains expanding array to add, remove, and change enrollStudent objects.
 * @author Jason Guo, Russel Rivera
 */
public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    /**
     * Default constructor for Enrollment object -> creates a EnrollStudent[] list and initiates size as 0.
     */
    public Enrollment() {
        enrollStudents = new EnrollStudent[Constants.ROSTER_CAPACITY];
        size = 0;
    }

    /**
     * Obtain the entire enrollStudents list.
     * @return EnrollStudent[] list: enrollStudents.
     */
    public EnrollStudent[] getEnrollStudents() {
        return enrollStudents;
    }

    /**
     * Obtain the target student from the enrollStudents list.
     * @param student The target student.
     * @return The instance of the target student contained in the enrollStudents list.
     */
    public EnrollStudent getStudent(EnrollStudent student) {
        for (int i = 0; i < size; i++) {
            if (student.equals(enrollStudents[i])) {
                return enrollStudents[i];
            }
        }
        return null;
    }

    /**
     * Obtain the number of students enrolled.
     * @return int representing the number of students enrolled.
     */
    public int getSize() {
        return size;
    }

    /**
     * Find the index of the target student in the enrollStudents list.
     * @param student Target student.
     * @return int representing the index of the student in the list.
     */
    private int find(EnrollStudent student) {
        for (int i = 0; i < size; i++) {
            if (student.equals(enrollStudents[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    /**
     * Grow the array by 4.
     */
    private void grow() {
        EnrollStudent[] new_enrollStudents = new EnrollStudent[enrollStudents.length + Constants.ROSTER_INC];
        for (int i = 0; i < size; i++) {
            new_enrollStudents[i] = enrollStudents[i];
        }
        enrollStudents = new_enrollStudents;
    }

    /**
     * Enroll a student by adding them to the enrollStudents array.
     * @param enrollStudent The student to be enrolled.
     */
    public void add(EnrollStudent enrollStudent) {
        int index = find(enrollStudent);
        if(index == Constants.NOT_FOUND) {
            if(size+1 == enrollStudents.length) {
                this.grow();
            }
            enrollStudents[size] = enrollStudent;
            size++;
        }
        else {
            enrollStudents[index].setCredits(enrollStudent.getCredits());
        }
    }

    /**
     * Remove the target student from enrollment.
     * @param enrollStudent The student to be removed.
     */
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

    /**
     * Check if enrollStudents list contains the target student.
     * @param enrollStudent The target student to be searched for.
     * @return true or false.
     */
    public boolean contains(EnrollStudent enrollStudent) {
        return find(enrollStudent) != Constants.NOT_FOUND;
    }

    /**
     * Print out the students that are enrolled as is.
     */
    public void print() {
        for(int i = 0; i < size; i++) {
            System.out.println(enrollStudents[i].toString());
        }
    }
}
