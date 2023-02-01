package src;

public class Student implements Comparable<Student>{
    private Profile profile;
    private Major major;
    private int creditCompleted;

    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    public Profile getProfile() {
        return profile;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public int getCreditCompleted() {
        return creditCompleted;
    }

    public String getSeniority() {
        if (creditCompleted < 30) {
            return "Freshman";
        } else if (creditCompleted < 60) {
            return "Sophomore";
        } else if (creditCompleted < 90) {
            return "Junior";
        } else {
            return "Senior";
        }
    }

    @Override
    public int compareTo(Student student) {
        return profile.compareTo(student.profile);
    }

    @Override
    public String toString() {
        return (profile.toString() + " (" + major.code + " " + major + " " + major.school + ") credits completed: " + creditCompleted + " (" + getSeniority() + ")");
    }

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

    public static void main(String[] args) {

    }
}
