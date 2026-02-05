package chapter9;

public class Student implements Comparable<Student> {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        String parts1[] = name.split(" ");
        String parts2[] = o.name.split(" ");
        return parts1[1].compareTo(parts2[1]);
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Student students[] = new Student[] {
            new Student("John Smith"),
            new Student("Adam Sandler"),
            new Student("Zack Thomas:")
        };
        System.out.println("BEFORE:");
        System.out.println(java.util.Arrays.toString(students));
        java.util.Arrays.sort(students);
        System.out.println("AFTER:");
        System.out.println(java.util.Arrays.toString(students));
    }
    
}
