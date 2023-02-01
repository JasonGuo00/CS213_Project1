package src;

public class Roster {

    private Student[] roster;
    private int size;

    public Roster() {
        roster = new Student[Constants.ROSTER_CAPACITY];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (student.equals(roster[i])) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }
    private void grow() {
        Student[] new_roster = new Student[roster.length + 4];
        for (int i = 0; i < size; i++) {
            new_roster[i] = roster[i];
        }
        roster = new_roster;
    }
    public boolean add(Student student) {
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
    public boolean contains(Student student) {
        return this.find(student) != -1;
    }
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }
    }
    public void printBySchoolMajor() {
        this.sortMajor();

        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }

        this.sortProfiles();
    }
    public void printByStanding() {
        this.sortStanding();

        for (int i = 0; i < size; i++) {
            System.out.println(roster[i]);
        }

        this.sortProfiles();
    }

    public void printBySchool(String school) {
        for(int i = 0; i < size; i++) {
            if(roster[i].getMajor().school.equalsIgnoreCase(school)) {
                System.out.println(roster[i]);
            }
        }
    }

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

    private int compareMajor(Major major1, Major major2) {
        int compare_school = major1.school.compareTo(major2.school);
        if (compare_school == 0) {
            return major1.name().compareTo(major2.name());
        } else {
            return compare_school;
        }
    }

    private int compareStanding(Student student1, Student student2) {
        int compare_standing = student1.getSeniority().compareTo(student2.getSeniority());
        if (compare_standing == 0) {
            return student1.getCreditCompleted() - student2.getCreditCompleted();
        } else {
            return compare_standing;
        }
    }

    public int changeMajor(Student stu, Major major) {
        int target = find(stu);
        if(target == Constants.NOT_FOUND) {
            return Constants.NOT_FOUND;
        }
        else {
            roster[target].setMajor(major);
            return 1;
        }
    }
}
