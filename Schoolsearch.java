import java.io.*;
import java.util.*;

// CSC 365. Winter 2018
// Lab 1-2
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

        String tLastName;
        String tFirstName;
        String classroom;
        Boolean added;

        public Teacher(String tLastName, String tFirstName, String classroom) {
            this.tLastName = tLastName;
            this.tFirstName = tFirstName;
            this.classroom = classroom;
            added = false;
        }

        public String getTLastName() { return tLastName; }

        public String getTFirstName() { return tFirstName; }

        public String getClassroom() { return classroom; }

        public Boolean getAdded() { return added; }

        public void setAdded(Boolean bool) { added = bool; }

  }

  public static void main(String[] args) {

        Schoolsearch schoolsearch = new Schoolsearch();
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        Scanner sc = new Scanner(System.in);
        String in = "";
        String[] inList;

        schoolsearch.readStudentFile(students);
        schoolsearch.readTeacherFile(teachers);

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
                    } else if (inList[2].equals("T") || inList[2].equals("Teachers")) {
                        /* G[rade]: number T[eachers] */
                        schoolsearch.gradeTeachers(students, teachers, lastNameNum);
                    }
                }
            } else if (instruction.equals("B:") || instruction.equals("Bus:")) {
                schoolsearch.bus(students, lastNameNum);
            } else if (instruction.equals("A:") || instruction.equals("Average:")) {
                schoolsearch.average(students, lastNameNum);
            } else if (instruction.equals("I") || instruction.equals("Info")) {
                schoolsearch.info(students);
            } else if (instruction.equals("C:") || instruction.equals("Classroom: ")) {
                /* let instruction be: "C[lassroom]: number S[tudent]/T[eacher]" */
                String stuTea = inList[2];
                if (stuTea.equals("S") || stuTea.equals("Student")) {
                    schoolsearch.classStudents(students, lastNameNum);
                } else if (stuTea.equals("T") || stuTea.equals("Teacher")) {
                    schoolsearch.classTeachers(teachers, lastNameNum);
                }
            } else if (instruction.equals("E") || instruction.equals("Enrollments")) {
                schoolsearch.enrollments(students, teachers);
            } else if (instruction.equals("An:") || instruction.equals("Analytics:")){
                if (lastNameNum.equals("B") || lastNameNum.equals("Bus")) {
                    schoolsearch.busAnalytics(students);
                }
                if (lastNameNum.equals("G") || lastNameNum.equals("Grade")) {
                    schoolsearch.gradeAnalytics(students);
                }
                if (lastNameNum.equals("T") || lastNameNum.equals("Teacher")) {
                    schoolsearch.teacherAnalytics(students, teachers);
                }
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
                    System.out.println(teachers.get(j).getClassroom() + " " + students.get(i).getClassroom());
                    if (teachers.get(j).getClassroom().equals(students.get(i).getClassroom())) {
                        System.out.print(students.get(i).getStLastName() + "," + students.get(i).getStFirstName() +
                                          "," + students.get(i).getGrade() + "," + students.get(i).getClassroom() +
                                          "," + teachers.get(j).getTLastName() + "," + teachers.get(j).getTFirstName());
                    }
                }
              } else {
                if (bus.equals("B") || bus.equals("Bus")) {
                    System.out.println(students.get(i).getStLastName() + "," + students.get(i).getStFirstName() + "," + students.get(i).getBus());
                }
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
          if (students.get(i).getClassroom().equals(classroom)) {
            System.out.println(students.get(i).getStLastName() + "," + students.get(i).getStFirstName());
          }
      }
  }

  private void classTeachers(ArrayList<Teacher> teachers, String classroom) {
      int listSize = teachers.size();

      for (int i = 0; i < listSize; i++) {
          if(teachers.get(i).getClassroom().equals(classroom)) {
              System.out.println(teachers.get(i).getTLastName() + "," + teachers.get(i).getTFirstName());
          }
      }
  }

  private void gradeTeachers(ArrayList<Student> students, ArrayList<Teacher> teachers, String grade) {
      int sListSize = students.size();
      int tListSize = teachers.size();
      String stClass;
      ArrayList<Teacher> teachList = new ArrayList<Teacher>();

      for (int i = 0; i < sListSize; i++) {
          if (students.get(i).getGrade().equals(grade)) {
              stClass = students.get(i).getClassroom();
              for (int j = 0; j < tListSize; j++) {
                  if (teachers.get(j).getClassroom().equals(stClass) && (teachers.get(j).getAdded() != true)) {
                      teachList.add(teachers.get(j));
                      teachers.get(j).setAdded(true);
                  }
              }
          }
      }

      for (int i = 0; i < teachList.size(); i++) {
          System.out.println(teachList.get(i).getTLastName() + "," + teachList.get(i).getTFirstName());
      }
  }

  private void enrollments(ArrayList<Student> students, ArrayList<Teacher> teachers) {
      int sListSize = students.size();
      int tListSize = teachers.size();

      for(int i = 0; i < tListSize; i++) {
          int count = 0;
          for(int j = 0; j < sListSize; j++) {
              if(students.get(j).getClassroom().equals(teachers.get(i).getClassroom())) {
                  count++;
              }
          }
          System.out.println(teachers.get(i).getClassroom() + ": " + count);
      }

  }

  private void teacherAnalytics(ArrayList<Student> students, ArrayList<Teacher> teachers) {
      int numTeachers = teachers.size();
      double avgGPA, highGPA, lowGPA = 0;

      for(int i = 0; i < numTeachers; i++) {
          avgGPA = calcTeacherAverageGrade(students, teachers.get(i).getClassroom());
          highGPA = calcTeacherHighGPA(students, teachers.get(i).getClassroom());
          lowGPA = calcTeacherLowGPA(students, teachers.get(i).getClassroom());

          System.out.println("Teacher: " + teachers.get(i).getTLastName() + ", " +
                             teachers.get(i).getTFirstName() + " Avg GPA: " +
                             String.format("%.2f", avgGPA) + " Highest GPA: " +
                             highGPA + " Lowest GPA: " + lowGPA);
      }
  }

  private double calcTeacherHighGPA(ArrayList<Student> students, String number) {
      int listSize = students.size();
      double highGPA = 0;
      double currGPA = 0;

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getClassroom().equals(number))) {
             currGPA = Double.parseDouble(students.get(i).getGPA());
             if(i == 0) {
                 highGPA = currGPA;
             }
             else if(currGPA > highGPA) {
                 highGPA = currGPA;
             }
          }
      }
      return highGPA;
  }

  private double calcTeacherLowGPA(ArrayList<Student> students, String number) {
      int listSize = students.size();
      double lowGPA = 0;
      double currGPA = 0;
      int count = 0;

      for (int i = 0; i < listSize; i++) {
          if ((students.get(i).getClassroom().equals(number))) {
             count++;
             currGPA = Double.parseDouble(students.get(i).getGPA());
             if(count == 1) {
                 lowGPA = currGPA;
             }
             else if(currGPA < lowGPA) {
                 lowGPA = currGPA;
             }
          }
      }
      return lowGPA;
  }

   private double calcTeacherAverageGrade(ArrayList<Student> students, String number) {
       int listSize = students.size();
       ArrayList<Double> gpas = new ArrayList<Double>();

       for (int i = 0; i < listSize; i++) {
           if ((students.get(i).getClassroom().equals(number))) {
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

         return avg;
   }
  private void gradeAnalytics(ArrayList<Student> students) {
      double avgGPA = 0;
      double highGPA = 0;
      double lowGPA = 0;
      int numGrades = 7;

      for(int i = 0; i < numGrades; i++) {
          avgGPA = calcAverageGrade(students, Integer.toString(i));
          highGPA = calcHighGPA(students, Integer.toString(i));
          lowGPA = calcLowGPA(students, Integer.toString(i));

          System.out.println("Grade: " + i + " Avg GPA: " + String.format("%.2f", avgGPA) +
                             " Highest GPA: " + highGPA + " Lowest GPA: " + lowGPA);
      }
  }



 private double calcHighGPA(ArrayList<Student> students, String number) {
     int listSize = students.size();
     double highGPA = 0;
     double currGPA = 0;

     for (int i = 0; i < listSize; i++) {
         if ((students.get(i).getGrade().equals(number))) {
            currGPA = Double.parseDouble(students.get(i).getGPA());
            if(i == 0) {
                highGPA = currGPA;
            }
            else if(currGPA > highGPA) {
                highGPA = currGPA;
            }
         }
     }
     return highGPA;
 }

 private double calcLowGPA(ArrayList<Student> students, String number) {
     int listSize = students.size();
     double lowGPA = 0;
     double currGPA = 0;
     int count = 0;

     for (int i = 0; i < listSize; i++) {
         if ((students.get(i).getGrade().equals(number))) {
            count++;
            currGPA = Double.parseDouble(students.get(i).getGPA());
            if(count == 1) {
                lowGPA = currGPA;
            }
            else if(currGPA < lowGPA) {
                lowGPA = currGPA;
            }
         }
     }
     return lowGPA;
 }

  private double calcAverageGrade(ArrayList<Student> students, String number) {
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

        return avg;
  }

  public class Bus {

      double highestGPA;
      double lowestGPA;
      double avgGPA;
      String busNum;

      public Bus(double highestGPA, double lowestGPA, double avgGPA, String busNum) {
          this.highestGPA = highestGPA;
          this.lowestGPA = lowestGPA;
          this.avgGPA = avgGPA;
          this.busNum = busNum;
      }

      public void setHighGPA(double gpa) { highestGPA = gpa; }

      public void setLowGPA(double gpa) { lowestGPA = gpa; }

      public void setAvgGPA(double gpa) { avgGPA = gpa; }

      public double getHighestGPA() { return highestGPA; }

      public double getLowestGPA() { return lowestGPA; }

      public double getAvgGPA() { return avgGPA; }

      public String getBusNum() { return busNum; }

  }

  private void busAnalytics(ArrayList<Student> students) {
    int listSize = students.size();
    ArrayList<Bus> busList = new ArrayList<Bus>();
    ArrayList<String> seenBusNum = new ArrayList<String>();
    String curBus = "";
    double highestGPA = 0, lowestGPA = 0, totalGPA = 0;
    double curGPA = 0, thisGPA = 0;
    int numGPA = 0;

    for (int i = 0; i < listSize; i++) {
        if (!(seenBusNum.contains(students.get(i).getBus()))) {
            curGPA = Double.parseDouble(students.get(i).getGPA());
            curBus = students.get(i).getBus();
            seenBusNum.add(curBus);
            highestGPA = curGPA;
            lowestGPA = curGPA;
            numGPA++;
            totalGPA += curGPA;
            Bus newBus = new Bus(highestGPA, lowestGPA, totalGPA / numGPA, curBus);
            busList.add(newBus);

            for (int j = 1; j < listSize; j++) {
                if (students.get(j).getBus().equals(curBus)) {
                    thisGPA = Double.parseDouble(students.get(j).getGPA());
                    totalGPA += thisGPA;
                    numGPA++;
                    if (thisGPA > newBus.getHighestGPA()) {
                        newBus.setHighGPA(thisGPA);
                    }
                    if (thisGPA < newBus.getLowestGPA()) {
                        newBus.setLowGPA(thisGPA);
                    }
                    newBus.setAvgGPA(totalGPA / numGPA);
                }
            }
        }
        totalGPA = numGPA = 0;
    }

    for (int i = 0; i < busList.size(); i++) {
        System.out.println("Bus: " + busList.get(i).getBusNum() + " Avg GPA: " + String.format("%.2f", busList.get(i).getAvgGPA()) + " Highest GPA: " +
                     busList.get(i).getHighestGPA() + " Lowest GPA: " + busList.get(i).getLowestGPA());
    }

  }

 private void readTeacherFile(ArrayList<Teacher> list) {

      String fileName = "teachers.txt";
      String line = null;

      try {
          FileReader fr = new FileReader(fileName);
          BufferedReader br = new BufferedReader(fr);

          while((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                Teacher t = new Teacher(tokens[0].trim(), tokens[1].trim(), tokens[2].trim());
                list.add(t);
            }

        } catch(IOException e) {
            System.out.println("File not found.");
        }
  }


  private void readStudentFile(ArrayList<Student> list) {

      String fileName = "list.txt";
      String line = null;

      try {
          FileReader fr = new FileReader(fileName);
          BufferedReader br = new BufferedReader(fr);

          while((line = br.readLine()) != null) {
              String[] tokens = line.split(",");
              Student s = new Student(tokens[0], tokens[1].trim(), tokens[2],
                                      tokens[3], tokens[4], tokens[5]);
              list.add(s);
          }

      } catch(IOException e) {
          System.out.println("File not found.");
      }
  }

}
