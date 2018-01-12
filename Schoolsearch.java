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

        Schoolsearch schoolsearch = new Schoolsearch(); 
        ArrayList<Student> students = new ArrayList<Student>();
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
                    schoolsearch.student(students, lastNameNum, null); 
                } else if (listSize == 3) {
                    schoolsearch.student(students, lastNameNum, inList[2]); 
                }
            } else if (instruction.equals("T:") || instruction.equals("Teacher:")) {
                schoolsearch.teacher(students, lastNameNum); 
            } else if (instruction.equals("G:") || instruction.equals("Grade:")) {
                if (listSize == 2) {
                    schoolsearch.grade(students, lastNameNum); 
                } else if (listSize == 3) {
                    schoolsearch.gradeHL(students, lastNameNum, Integer.parseInt(inList[2])); 
                }
            } else if (instruction.equals("B:") || instruction.equals("Bus:")) {
                schoolsearch.bus(students, lastNameNum); 
            } else if (instruction.equals("A:") || instruction.equals("Average:")) {
                schoolsearch.average(students, lastNameNum); 
            } else if (instruction.equals("I:") || instruction.equals("Info:")) {
                schoolsearch.info(students); 
            }
            
            if (!in.equals("Q") && !in.equals("Quit"))
                System.out.print("What do you want to search for? ");
        }

  }

  public Schoolsearch() {
      // constructor
  }

  private void student(ArrayList<Student> students, String lastName, String bus) {
      int listSize = students.size(); 

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getStLastName()).equals(lastName)) {
              if (bus == null) {
                System.out.print(students.get(i).getStLastName() + " " + students.get(i).getStFirstName() + " "); 
                System.out.print(students.get(i).getGrade() + " " + students.get(i).getClassroom() + " "); 
                System.out.println(students.get(i).getTLastName() + " " + students.get(i).getTFirstName()); 
              } else {
                if (bus.equals("B") || bus.equals("Bus"))
                    System.out.println(students.get(i).getStLastName() + " " + students.get(i).getStFirstName() + " " + students.get(i).getBus()); 
              }
          }
      }
  }

  private void teacher(ArrayList<Student> students, String lastName) {
      int listSize = students.size(); 

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getTLastName()).equals(lastName)) {
              System.out.println(students.get(i).getStLastName() + " " + students.get(i).getStFirstName()); 
          }
      }
  }

  private void grade(ArrayList<Student> students, String number) {
      int listSize = students.size();

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getGrade().equals(number))) {
              System.out.println(students.get(i).getStLastName() + " " + students.get(i).getStFirstName()); 
          }
      }

  }

private void bus(ArrayList<Student> students, String number) {
      int listSize = students.size();

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getBus().equals(number))) {
              System.out.println(students.get(i).getStLastName() + " " + students.get(i).getStFirstName() + " " +
                                 students.get(i).getGrade() + " " + students.get(i).getClassroom());
          }
      }

  }

  private void gradeHL(ArrayList<Student> students, String number, int HL) {
      int listSize = students.size();
      double gpa = 0;
      int index = 0;

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getGrade().equals(number))) {
              if(HL == 0) {
                  double tempGPA = Double.parserDouble(students.get(i).getGPA());
                  if(tempGPA > gpa) {
                      index = i;
                  }
              }
              else if(HL == 1) {
                  double tempGPA = Double.parserDouble(students.get(i).getGPA());
                  if(tempGPA < gpa) {
                      index = i;
                  }
              }
          }
      }

      System.out.println(students.get(i).getStLastName() + " " + students.get(i).getStFirstName() + " " +
                         students.get(i).getGPA() + " " + students.get(i).getTFirstName() + " " +
                         students.get(i).getTLastName() + " " + students.get(i).getBus());

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
      double avg = sum / gpas.size();
      System.out.println(number + " " + avg);
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
