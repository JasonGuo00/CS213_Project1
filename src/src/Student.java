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

    @Override
    public int compareTo(Student student) {
        Profile student1 = profile;
        Profile student2 = student.profile;
        return student1.compareTo(student2);
    }

    @Override
    public String toString() {
        return (profile.toString() + " (" + major.code + " " + major + " " + major.school + ") credits completed: " + creditCompleted);
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
}
