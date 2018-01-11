import java.io.*;
import java.util.*; 

public class Schoolsearch {

   public class Student {

      String stLastName;
      String stFirstName;
      String grade;
      String classroom;
      String bus;
      String gpa;
      String tLastName;
      String tFirstName;
      
      public Student(String stLastName, String stFirstName, String grade, String classroom, 
                     String bus, String gpa, String tLastName, String tFirstName) {
                     
        this.stLastName = stLastName;
        this.stFirstName = stFirstName;
        this.grade = grade;
        this.classroom = classroom;
        this.bus = bus;
        this.gpa = gpa;
        this.tLastName = tLastName;
        this.tFirstName = tFirstName;   
      }
      
      public String getStLastName() { return stLastName; }
      
      public String getStFirstName() { return stFirstName; }
      
      public String getGrade() { return grade; }
      
      public String getClassroom() { return classroom; }
      
      public String getBus() { return bus; }
      
      public String getGPA() { return gpa; }
      
      public String getTLastName() { return tLastName; }
      
      public String getTFirstName() { return tFirstName; }
    
  }
// test
  public static void main(String[] args) {

        ArrayList<Student> students = readFile();
        Scanner sc = new Scanner(System.in);
        String in = "";
        String searchVal; 

        System.out.print("What do you want to search for? ");

        while (!in.equals("Q") && !in.equals("Quit")) { // how to check for Q or Quit
            in = sc.nextLine();
            if (in.contains("S")) {
                // searchFile for <lastname>
            }
            System.out.print("What do you want to search for? ");
        }

  }

  private void Student(ArrayList<Student> students, String lastName, String bus) {

  }

  private void Teacher(ArrayList<Student> students, String lastName) {

  }

  private void Grade(ArrayList<Student> students, String number) {

  }

  private void Bus(ArrayList<Student> students, String number) {

  }

  private void GradeHL(ArrayList<Student> students, int HL) {

  }

  private void Average(ArrayList<Student> students, String number) {

  }

  private void Info(ArrayList<Student> students) {

  }


  private ArrayList<Student> readFile() {

      ArrayList<Student> students = new ArrayList<Student>();
      String fileName = "students.txt";

      String line = null;

      try {
          FileReader fr = new FileReader(fileName);

          BufferedReader br = new BufferedReader(fr);

          while((line = br.readLine()) != null) {

              String[] tokens = line.split(",");
              Student s = new Student(tokens[0], tokens[1], tokens[2], tokens[3],
                      tokens[4], tokens[5], tokens[6], tokens[7]);
              students.add(s);

          }

      } catch(IOException e) {
          System.out.println("File not found.");
      }
      return students;
  }

}