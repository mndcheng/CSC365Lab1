import java.io.*;
import java.util.*; 

// CSC 365. Winter 2018
// Lab 1-1
// Dylan Carr, Amanda Cheng

public class Schoolsearch {

   public class Student {

      String stLastName;
      String stFirstName;
      String grade;
      String classroom; 
      String bus;
      String gpa; 
      
      public Student(String stLastName, String stFirstName, String grade, String classroom, 
                     String bus, String gpa) {
                     
        this.stLastName = stLastName;
        this.stFirstName = stFirstName;
        this.grade = grade;
        this.classroom = classroom;
        this.bus = bus;
        this.gpa = gpa; 
      }
      
      public String getStLastName() { return stLastName; }
      
      public String getStFirstName() { return stFirstName; }
      
      public String getGrade() { return grade; }

      public String getClassroom() { return classroom; }
      
      public String getBus() { return bus; }
      
      public String getGPA() { return gpa; }
    
  }

  public class Teacher {
      
        String classroom; 
        String tLastName;
        String tFirstName;

        public Teacher(String classroom, String tLastName, String tFirstName) {
            this.classroom = classroom; 
            this.tLastName = tLastName;
            this.tFirstName = tFirstName;
        }

        public String getClassroom() { return classroom; }

        public String getTLastName() { return tLastName; }

        public String getTFirstName() { return tFirstName; }
  }

  public static void main(String[] args) {

        Schoolsearch schoolsearch = new Schoolsearch(); 
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Teacher> teachers = new ArrayList<Teacher>(); 
        Scanner sc = new Scanner(System.in);
        String in = "";
        String[] inList; 

        schoolsearch.readFile(students); 

        System.out.print("What do you want to search for? ");

        while (!in.equals("Q") && !in.equals("Quit")) {  
            in = sc.nextLine();
            inList = in.split(" "); 

            String instruction = inList[0];
            String lastNameNum = ""; 
            int listSize = inList.length; 

            if (inList.length > 1)  
                lastNameNum = inList[1]; 

            if (instruction.equals("S:") || instruction.equals("Student:")) {
                if (listSize == 2) {
                    schoolsearch.student(students, teachers, lastNameNum, null); 
                } else if (listSize == 3) {
                    schoolsearch.student(students, teachers, lastNameNum, inList[2]); 
                }
            } else if (instruction.equals("T:") || instruction.equals("Teacher:")) {
                schoolsearch.teacher(students, teachers, lastNameNum); 
            } else if (instruction.equals("G:") || instruction.equals("Grade:")) {
                if (listSize == 2) {
                    schoolsearch.grade(students, lastNameNum); 
                } else if (listSize == 3) {
                    if (inList[2].equals("H") || inList[2].equals("High")) {
                        schoolsearch.gradeHL(students, teachers, lastNameNum, 0);  
                    } else if (inList[2].equals("L") || inList[2].equals("Low")) {
                        schoolsearch.gradeHL(students, teachers, lastNameNum, 1); 
                    }
                }
            } else if (instruction.equals("B:") || instruction.equals("Bus:")) {
                schoolsearch.bus(students, lastNameNum); 
            } else if (instruction.equals("A:") || instruction.equals("Average:")) {
                schoolsearch.average(students, lastNameNum); 
            } else if (instruction.equals("I") || instruction.equals("Info")) {
                schoolsearch.info(students); 
            }
            
            if (!in.equals("Q") && !in.equals("Quit"))
                System.out.print("What do you want to search for? ");
        }

  }

  public Schoolsearch() {
      // constructor
  }

  private void student(ArrayList<Student> students, ArrayList<Teacher> teachers, String lastName, String bus) {
      int sListSize = students.size(); 
      int tListSize = teachers.size(); 

      for (int i = 0; i < sListSize; i++) {
          if (students.get(i).getStLastName().equals(lastName)) {
              if (bus == null) {
                for (int j = 0; j < tListSize; j++) {
                    if (teachers.get(j).getClassroom().equals(students.get(i).getClassroom())) {
                        System.out.print(students.get(i).getStLastName() + "," + students.get(i).getStFirstName() + 
                                          "," + students.get(i).getGrade() + "," + students.get(i).getClassroom() + 
                                          "," + teachers.get(j).getTLastName() + "," + teachers.get(j).getTFirstName()); 
                    }
                }
              } else {
                if (bus.equals("B") || bus.equals("Bus"))
                    System.out.println(students.get(i).getStLastName() + "," + students.get(i).getStFirstName() + "," + students.get(i).getBus()); 
              }
          }
      }
  
  }

  private void teacher(ArrayList<Student> students, ArrayList<Teacher> teachers, String lastName) {
      int sListSize = students.size();
      int tListSize = teachers.size();

      for (int i = 0; i < tListSize; i++) {
          if (teachers.get(i).getTLastName().equals(lastName)) {
              for (int j = 0; j < sListSize; j++) {
                  if (teachers.get(i).getClassroom().equals(students.get(j).getClassroom())) {
                      System.out.println(students.get(j).getStLastName() + "," + students.get(j).getStFirstName()); 
                  }
              }
          }
      }
  
  }

  private void grade(ArrayList<Student> students, String number) {
      int listSize = students.size();

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getGrade().equals(number))) {
              System.out.println(students.get(i).getStLastName() + "," + students.get(i).getStFirstName()); 
          }
      }

  }

private void bus(ArrayList<Student> students, String number) {
      int listSize = students.size();

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getBus().equals(number))) {
              System.out.println(students.get(i).getStLastName() + "," + students.get(i).getStFirstName() + "," +
                                 students.get(i).getGrade() + "," + students.get(i).getClassroom());
          }
      }

  }

  private void gradeHL(ArrayList<Student> students, ArrayList<Teacher> teachers, String number, int HL) {
      int listSize = students.size();
      int tListSize = teachers.size(); 
      double gpa = 0;
      int index = 0;
      int count = 0; 
      boolean exists = false; 

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getGrade().equals(number))) {
              exists = true; 
              if (count == 0) {
                gpa = Double.parseDouble(students.get(i).getGPA()); 
                index = i; 
              }
              if(HL == 0) {
                  double tempGPA = Double.parseDouble(students.get(i).getGPA());
                  if(tempGPA > gpa) {
                      gpa = tempGPA; 
                      index = i;
                  }
              }
              else if(HL == 1) {
                  double tempGPA = Double.parseDouble(students.get(i).getGPA());
                  if(tempGPA < gpa) {
                      gpa = tempGPA; 
                      index = i;
                  }
              }
              count++; 
          }
      }

      if (exists) {
          for (int j = 0; j < tListSize; j++) {
            if (teachers.get(j).getClassroom().equals(students.get(index).getClassroom())) {
                System.out.println(students.get(index).getStLastName() + "," + students.get(index).getStFirstName() + "," +
                                    students.get(index).getGPA() + "," + teachers.get(j).getTFirstName() + "," +
                                    teachers.get(j).getTLastName() + "," + students.get(index).getBus());
            } 
          }
      }

  }

  private void average(ArrayList<Student> students, String number) {
      int listSize = students.size();
      ArrayList<Double> gpas = new ArrayList<Double>();

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getGrade().equals(number))) {
              gpas.add(Double.parseDouble(students.get(i).getGPA()));
          }
      }

      double sum = 0;
      for(double d : gpas) {
          sum += d;
      }
      double avg = 0; 
      if (sum != 0)
        avg = sum / gpas.size();
      System.out.println(number + " " + avg);
  }

  private void info(ArrayList<Student> students) {

      int[] totals = new int[7];

      for(Student s : students) {
          totals[Integer.parseInt(s.getGrade())]++;
      }

      for(int i = 0; i < 7; i++) {
          System.out.println(i + ": " + totals[i]);
      }

  }

  private void classStudents(ArrayList<Student> students, String classroom) {
      int listSize = students.size(); 

      for (int i = 0; i < listSize; i++) {
          System.out.println(students.get(i).getStLastName() + "," + students.get(i).getStFirstName()); 
      }
  }

  private void classTeachers(ArrayList<Teacher> teachers, String classroom) {}

  private void gradeTeachers(ArrayList<Teacher> teachers, String grade) {}

  private void enrollments(ArrayList<Student> students) {}

  private void readFile(ArrayList<Student> list) {

      String fileName = "students.txt";
      String line = null;

      try {
          FileReader fr = new FileReader(fileName);

          BufferedReader br = new BufferedReader(fr);

          while((line = br.readLine()) != null) {

              String[] tokens = line.split(", ");
              Student s = new Student(tokens[0], tokens[1], tokens[2], tokens[3],
                      tokens[4], tokens[5]);
              list.add(s);

          }

      } catch(IOException e) {
          System.out.println("File not found.");
      }
  }

}
