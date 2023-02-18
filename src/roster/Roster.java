package roster;

/**
 * Object to store and handle Student objects
 * Contains expanding array to add, remove, store, and sort Student objects
 * Also includes methods to print out sorted information to the console.
 * @author Jason Guo, Russel Rivera
 */
public class Roster {

    private Student[] roster;
    private int size;

    /**
     Creates Roster object, requires no parameters
     */
    public Roster() {
        roster = new Student[Constants.ROSTER_CAPACITY];
        size = 0;
    }

    /**
     * Getter for the number of Students stored
     * @return returns an int describing how many Students are stored
     */
    public int getSize() {
        return size;
    }

    /**
     * Obtains the index of the given Student object in the roster
     * @param student Student object to search for
     * @return Returns the index of student, or -1 if not found
     */
    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (student.equals(roster[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    /**
     * Increases the size of the roster array by 4, keeping the data
     */
    private void grow() {
        Student[] new_roster = new Student[roster.length + Constants.ROSTER_INC];
        for (int i = 0; i < size; i++) {
            new_roster[i] = roster[i];
        }
        roster = new_roster;
    }

    /**
     * Adds Student object to roster, sorted by profile; sorting by last name, first name, then date of birth
     * @param student Student object to add
     * @return returns 1 on a success, 0 on a failure.
     */
    public boolean add(Student student) {

        if(student == null) {
            return false;
        }

        int i;

        if (size + 1 == roster.length) {
            this.grow();
        }

        for (i = size - 1; i >= 0 && roster[i].compareTo(student) > 0; i--) {
            roster[i + 1] = roster[i];
        }

        roster[i + 1] = student;

        size++;

        return true;
    }

    /**
     * Searches for Student object and removes it from the roster
     * @param student Student object to search for and remove
     * @return Returns true if successful, false if Student wasn't found removing was unsuccessful
     */
    public boolean remove(Student student) {
        boolean found = false;
        int i;

        for (i = 0; i < size - 1; i++) {
            if (student.compareTo(roster[i]) == 0) {
                found = true;
            }

            if (found) {
                roster[i] = roster[i + 1];
            }
        }

        if (found) {
            size--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Obtains whether the Student object is in the roster
     * @param student Student object to search for
     * @return Returns true if Student was found in the roster, and false if not
     */
    public boolean contains(Student student) {
        return this.find(student) != -1;
    }

    /**
     * Iterates through the roster, sorted by profile, and prints out each item
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
    }

    /**
     * Iterates through the roster, sorted by school and major, and prints out each item
     */
    public void printBySchoolMajor() {
        this.sortMajor();

        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }

        this.sortProfiles();
    }


    /**
     * Iterates through the roster, sorted lexicographically by seniority and credits, and prints out each item
     */
    public void printByStanding() {
        this.sortStanding();

        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }

        this.sortProfiles();
    }

    /**
     * Iterates through the roster, sorted by profile, and prints out each item that matches the school name
     * @param school String with school name to match
     */
    public void printBySchool(String school) {
        for(int i = 0; i < size; i++) {
            if(roster[i].getMajor().school.equalsIgnoreCase(school)) {
                System.out.println(roster[i]);
            }
        }
    }

    /**
     * Sorts the list based on seniority, then credits
     */
    private void sortStanding() {
        for (int i = 1; i < size; ++i) {
            Student item = roster[i];
            int j = i - 1;

            while (j >= 0 && compareStanding(item, roster[j]) < 0) {
                roster[j + 1] = roster[j];
                j--;
            }

            roster[j + 1] = item;
        }
    }

    /**
     * Sorts the list based on profile, which is last name, first name, then date of birth
     */
    private void sortProfiles() {
        for (int i = 1; i < size; ++i) {
            Student item = roster[i];
            int j = i - 1;

            while (j >= 0 && roster[j].getProfile().compareTo(item.getProfile()) > 0) {
                roster[j + 1] = roster[j];
                j--;
            }

            roster[j + 1] = item;
        }
    }


    /**
     * Sorts the list based on school name, then major name
     */
    private void sortMajor() {
        for (int i = 1; i < size; ++i) {
            Student item = roster[i];
            int j = i - 1;

            while (j >= 0 && compareMajor(item.getMajor(), roster[j].getMajor()) < 0) {
                roster[j + 1] = roster[j];
                j--;
            }

            roster[j + 1] = item;
        }
    }

    /**
     * Compares school name, then major name
     * First compares school name string. If they are different, returns result.
     * If school names are the same, compares major name string and returns result.
     * @param major1 first Major object to compare
     * @param major2 second Major object to compare
     * @return Returns a value less than 0 if major1 is less than major2, 0 if they are equal, and 1 if the former is greater
     */
    private int compareMajor(Major major1, Major major2) {
        int compare_school = major1.school.compareTo(major2.school);
        if (compare_school == 0) {
            return major1.name().compareTo(major2.name());
        } else {
            return compare_school;
        }
    }

    /**
     * Compares seniority, then credits
     * First compares seniority. If they are different, returns result.
     * If seniority is the same, compares credits and returns result.
     * @param student1 first Student object to compare
     * @param student2 second Student object to compare
     * @return Returns a value less than 0 if student1 is less than student2, 0 if they are equal, and 1 if the former is greater
     */
    private int compareStanding(Student student1, Student student2) {
        int compare_standing = student1.getSeniority().compareTo(student2.getSeniority());
        if (compare_standing == 0) {
            return student1.getCreditCompleted() - student2.getCreditCompleted();
        } else {
            return compare_standing;
        }
    }

    /**
     * Searches for student object and changes its major
     * @param student Student object to search for
     * @param major Major object to set student's to
     * @return Returns true if successful, and false if unsuccessful and student was not found
     */
    public boolean changeMajor(Student student, Major major) {
        int target = find(student);
        if(target == Constants.NOT_FOUND) {
            return false;
        }
        else {
            roster[target].setMajor(major);
            return true;
        }
    }

    public boolean checkValid(Student student, int credits) {
        for (int i = 0; i < size; i++) {
            if (student.compareTo(roster[i]) == 0) {
                if (roster[i].isValid(credits)) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }

    public Student getStudent(Student student) {
        for (int i = 0; i < size; i++) {
            if (student.equals(roster[i])) {
                return roster[i];
            }
        }
        return null;
    }
}
