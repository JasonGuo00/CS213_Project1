package roster;

/**
 * Holds all the important information that needs to be tracked for each Student in the roster.
 * Includes the Profile of the Student: their name and date of birth.
 * Also includes additional information on their declared majors and how many credit they've completed.
 * @author Jason Guo, Russel Rivera
 */
public class Student implements Comparable<Student>{
    private Profile profile;
    private Major major;
    private int creditCompleted;

    /**
     * Creates a Student object with the given parameters.
     * @param profile Profile of the student
     * @param major Major of the student
     * @param creditCompleted How many credits the student has completed
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * Getter for the student's profile.
     * @return Returns the profile (Profile) of the student
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Getter for the major.
     * @return Returns the major (enum) of the student
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Setter for the major.
     * @param major New major for the student
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Getter for credits completed.
     * @return returns the number of credits completed
     */
    public int getCreditCompleted() {
        return creditCompleted;
    }

    /**
     * Obtains the seniority of a student based on how many credits they've completed.
     * @return Returns a string that identifies the student's seniority
     */
    public String getSeniority() {
        if (creditCompleted < Constants.SOPHOMORE_CREDS) {
            return "Freshman";
        } else if (creditCompleted < Constants.JUNIOR_CREDS) {
            return "Sophomore";
        } else if (creditCompleted < Constants.SENIOR_CREDS) {
            return "Junior";
        } else {
            return "Senior";
        }
    }

    /**
     * Overrides compareTo method, compares the portfolios of the two students.
     * @param student the object to be compared.
     * @return Returns 1, 0, or -1 depending on the portfolios of the compared students
     */
    @Override
    public int compareTo(Student student) {
        return profile.compareTo(student.profile);
    }

    /**
     * Overrides the toString method.
     * @return Returns the name, d.o.b, major code, major, school, credits completed, and seniority of the student
     */
    @Override
    public String toString() {
        return (profile.toString() + " (" + major.code + " " + major + " " + major.school + ") credits completed: " + creditCompleted + " (" + getSeniority() + ")");
    }

    /**
     * Overrides the equals method.
     * @param obj object to be compared
     * @return Returns true if the two students are the same, and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student) {
            Student student = (Student)obj;
            if(profile.equals(student.profile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Testbed Main.
     * Used to test the functionality of the Student compareTo() method.
     * @param args
     */
    public static void main(String[] args) {
        Student s1 = new Student(new Profile("Doe", "John", new Date("1/14/2001")), Major.CS, 0);
        Student s2 = new Student(new Profile("Doe", "Jane", new Date("3/2/2002")), Major.CS, 0);
        int expectedOutput = 1;
        int actualOutput = s1.compareTo(s2);
        System.out.println("** Test Case 1: Compare first name lexicographically if last names are the same. **");
        System.out.println("Student 1 : " + s1.getProfile() + " Compared to Student 2: " + s2.getProfile());
        System.out.println("Expected Result: " + expectedOutput + " || Actual Result: " + actualOutput);

        s1 = new Student(new Profile("Doe", "John", new Date("4/1/1999")), Major.CS, 0);
        s2 = new Student(new Profile("Doe", "John", new Date("11/1/2002")), Major.CS, 0);
        expectedOutput = -1;
        actualOutput = s1.compareTo(s2);
        System.out.println("** Test Case 2: Compare d.o.b chronologically if first and last names are the same. **");
        System.out.println("Student 1: " + s1.getProfile() + " Compared to Student 2: " + s2.getProfile());
        System.out.println("Expected Result: " + expectedOutput + " || Actual Result: " + actualOutput);

        s1 = new Student(new Profile("Kim", "Robert", new Date("12/14/2004")), Major.CS, 0);
        s2 = new Student(new Profile("Zoo", "John", new Date("1/2/2000")), Major.CS, 0);
        expectedOutput = -1;
        actualOutput = s1.compareTo(s2);
        System.out.println("** Test Case 3: Compares last names lexicographically before anything else. **");
        System.out.println("Student 1: " + s1.getProfile() + " Compared to Student 2: " + s2.getProfile());
        System.out.println("Expected Result: " + expectedOutput + " || Actual Result: " + actualOutput);

        s1 = new Student(new Profile("Doe", "John", new Date("1/1/2000")), Major.CS, 0);
        s2 = new Student(new Profile("Doe", "John", new Date("1/1/2000")), Major.CS, 0);
        expectedOutput = 0;
        actualOutput = s1.compareTo(s2);
        System.out.println("** Test Case 4: Compare two students with the same profile. **");
        System.out.println("Student 1: " + s1.getProfile() + " Compared to Student 2: " + s2.getProfile());
        System.out.println("Expected Result: " + expectedOutput + " || Actual Result: " + actualOutput);

        s1 = new Student(new Profile("Wong", "Allison", new Date("6/12/2001")), Major.CS, 0);
        s2 = new Student(new Profile("Wang", "Johnathan", new Date("9/12/2004")), Major.CS, 0);
        expectedOutput = 1;
        actualOutput = s1.compareTo(s2);
        System.out.println("** Test Case 5: Compare two students with last names containing the same starting letter, lexicographically. **");
        System.out.println("Student 1: " + s1.getProfile() + " Compared to Student 2: " + s2.getProfile());
        System.out.println("Expected Result: " + expectedOutput + " || Actual Result: " + actualOutput);

        s1 = new Student(new Profile("Wong", "Allison", new Date("6/12/2001")), Major.CS, 0);
        s2 = new Student(new Profile("Wong", "Alice", new Date("9/12/2004")), Major.CS, 0);
        expectedOutput = 1;
        actualOutput = s1.compareTo(s2);
        System.out.println("** Test Case 6: Compare two students with the same last name and same starting letters in the first name, lexicographically. **");
        System.out.println("Student 1: " + s1.getProfile() + " Compared to Student 2: " + s2.getProfile());
        System.out.println("Expected Result: " + expectedOutput + " || Actual Result: " + actualOutput);
    }
}
