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

  public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<Student>();
        Scanner sc = new Scanner(System.in);
        String in = "";
        String[] inList; 

        readFile(students); 

        System.out.print("What do you want to search for? ");

        while (!in.equals("Q") && !in.equals("Quit")) {
            in = sc.nextLine();
            inList = in.split(" "); 

            String instruction = inList[0]; 
            String lastNameNum = inList[1]; 
            if (instruction.equals("S:") || instruction.equals("Student:")) {
                int listSize = inList.length; 
                if (listSize == 2) {
                    student(students, lastNameNum, null); 
                } else if (listSize == 3) {
                    student(students, lastNameNum, inList[2]); 
                }
            } else if (instruction.equals("T:") || instruction.equals("Teacher:")) {
                //teacher(students, lastNameNum); 
            } else if (instruction.equals("G:") || instruction.equals("Grade:")) {
                //grade(students, lastNameNum); 
            } else if (instruction.equals("B:") || instruction.equals("Bus:")) {
                //bus(students, lastNameNum); 
            } // incomplete 
            System.out.print("What do you want to search for? ");
        }

  }

  private void student(ArrayList<Student> students, String lastName, String bus) {
      int listSize = students.size(); 

      for (int i = 0; i < listSize; i++) {
          if (students.get(i).contains(lastName)) {
              if (bus == null) {
                System.out.print(students.get(i).getStLastName() + " " + students.get(i).getStFirstName() + " "); 
                System.out.print(students.get(i).getGrade() + " " + students.get(i).getClassroom() + " "); 
                System.out.println(students.get(i).getTLastName() + " " + students.get(i).getTFirstName()); 
              } else {
                System.out.println(students.get(i).getStLastName() + " " + students.get(i).getStFirstName() + " " + students.get(i).getBus()); 
              }
          }
      }
  }

  private void teacher(ArrayList<Student> students, String lastName) {

  }

  private void grade(ArrayList<Student> students, String number) {

  }

  private void bus(ArrayList<Student> students, String number) {

  }

  private void gradeHL(ArrayList<Student> students, int HL) {

  }

  private void average(ArrayList<Student> students, String number) {

  }

  private void info(ArrayList<Student> students) {

  }

  private void readFile(ArrayList<Student> list) {

      String fileName = "students.txt";
      String line = null;

      try {
          FileReader fr = new FileReader(fileName);

          BufferedReader br = new BufferedReader(fr);

          while((line = br.readLine()) != null) {

              String[] tokens = line.split(",");
              Student s = new Student(tokens[0], tokens[1], tokens[2], tokens[3],
                      tokens[4], tokens[5], tokens[6], tokens[7]);
              list.add(s);

          }

      } catch(IOException e) {
          System.out.println("File not found.");
      }
  }

}