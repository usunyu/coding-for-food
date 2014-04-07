/*************************************************************************
 *  Compilation:  javac Student.java
 *  Execution:    java Student
 *  
 *  Illustrates implementation of a Comparator
 *
 *  % By name
 *  ----------
 *  2 Alice
 *  1 Bob
 *  2 Carol
 *  1 Dave
 *  2 Eve
 *  3 Frank
 *  1 Grant
 *  3 Helia
 *  3 Isaac
 *  1 Jen
 *  1 Kevin
 *  2 Larry

 *  By section
 *  ----------
 *  1 Bob
 *  1 Dave
 *  1 Grant
 *  1 Jen
 *  1 Kevin
 *  2 Alice
 *  2 Carol
 *  2 Eve
 *  2 Larry
 *  3 Frank
 *  3 Helia
 *  3 Isaac
 *
 *  By Kevin
 *  ----------
 *  1 Kevin
 *  2 Larry
 *  2 Alice
 *  1 Bob
 *  2 Carol
 *  1 Dave
 *  2 Eve
 *  3 Frank
 *  1 Grant
 *  3 Helia
 *  3 Isaac
 *  1 Jen
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Student {
   
    public static final Comparator<Student> BY_NAME    = new ByName();
    public static final Comparator<Student> BY_SECTION = new BySection();
    public final Comparator<Student> BY_MY_NAME = new ByMyName();

    private final String name;
    private final int section;

    // constructor
    public Student(String name, int section) {
        this.name = name;
        this.section = section;
    }

    // comparator to sort by name
    private static class ByName implements Comparator<Student> {
        public int compare(Student a, Student b) {
            return a.name.compareTo(b.name);
        }
    }

    // comparator to sort by section
    private static class BySection implements Comparator<Student> {
        public int compare(Student a, Student b) {
            return a.section - b.section;
        }
    }

    // comparator to sort by name with this name first
    // illustrates the use of a non-static comparator
    private class ByMyName implements Comparator<Student> {
        public int compare(Student a, Student b) {
            if (a.name.compareTo(b.name) == 0) return 0;
            if (a.name.compareTo(name) == 0) return -1;
            if (b.name.compareTo(name) == 0) return +1;
            if ((a.name.compareTo(name) < 0) && (b.name.compareTo(name) > 0))
                return +1;
            if ((a.name.compareTo(name) > 0) && (b.name.compareTo(name) < 0))
                return -1;
            return a.name.compareTo(b.name);
        }
    }

    // return string representation
    public String toString() {
        return section + " " + name;
    }


    // test client
    public static void main(String[] args) {

        // create an array of students
        Student alice  = new Student("Alice",  2);
        Student bob    = new Student("Bob",    1);
        Student carol  = new Student("Carol",  2);
        Student dave   = new Student("Dave",   1);
        Student eve    = new Student("Eve",    2);
        Student frank  = new Student("Frank",  3);
        Student grant  = new Student("Grant",  1);
        Student helia  = new Student("Helia",  3);
        Student isaac  = new Student("Isaac",  3);
        Student jen    = new Student("Jen",    1);
        Student kevin  = new Student("Kevin",  1);
        Student larry  = new Student("Larry",  2);
        Student[] students = {
            larry, kevin, jen, isaac, grant, helia,
            frank, eve, dave, carol, bob, alice
        };

        // sort by name and print results
        StdOut.println("By name");
        StdOut.println("----------");
        Arrays.sort(students, Student.BY_NAME);
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();


        // now, sort by section and print results
        StdOut.println("By section");
        StdOut.println("----------");
        Arrays.sort(students, Student.BY_SECTION);
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();

        // now, sort by name relative to Kevin
        StdOut.println("By Kevin");
        StdOut.println("----------");
        Arrays.sort(students, kevin.BY_MY_NAME);
        for (int i = 0; i < students.length; i++)
            StdOut.println(students[i]);
        StdOut.println();


    }

}