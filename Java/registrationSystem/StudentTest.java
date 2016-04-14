package registrationSystem;

import java.time.LocalDate;
import java.time.Month;

/**
 * This main method will be used to test your Student class.  Your class must 
 * name it's methods such that it can work with this main method.
 * @author jwright
 */
public class StudentTest {
    public static void main(String[] args)
    {
        //test a regular student
        Student st1 = new Student("Fred", "Flintstone", "16 Cobblestone Way", 
                                  "Bedrock", "L0L 6T6", "COPA", 223, 
                                   LocalDate.of(2000, Month.AUGUST, 22), 
                                   LocalDate.of(1974,Month.APRIL,12));
        System.out.printf("Student 1 is: %s, he is %d years old.%n", st1, st1.getAge());
        System.out.printf("Address: %s%n", st1.getAddress());
        
        
        //Have the student change their address
        st1.changeAddress("49 Granite Court", "Bedrock", "R2D 2B3");
        System.out.printf("The new address: %s%n", st1.getAddress());
        
        
        //Test if the student can get suspended
        System.out.printf("The student is currently %s%n", st1.inGoodStanding()? "in good standing":"suspended");
        st1.suspendStudent();
        System.out.printf("Now, the student is currently %s%n", st1.inGoodStanding()? "in good standing":"suspended");
        st1.reinstateStudent();
        System.out.printf("Now, the student is currently %s%n", st1.inGoodStanding()? "in good standing":"suspended");
        
        
        
        //Test adding a student that is too old to enrol in college
        Student st2 = new Student("Barney", "Rubble", "18 Cobblestone Way", 
                                  "Bedrock", "L0L 6T6", "COPA", 224, 
                                   LocalDate.of(2016, Month.FEBRUARY, 1), 
                                   LocalDate.of(1872,Month.APRIL,12));
        System.out.printf("%nStudent: %s is %d years old", st2, st2.getAge());  //it should return 0, not error out
        
        
        //set the birthday and test for age and ability to see birth year
        st2.setBirthday(LocalDate.of(1976,Month.FEBRUARY,1));
        System.out.printf("%nNow, student: %s is %d years old", st2, st2.getAge());        
        System.out.printf("%n%s was born in the year %d", st2, st2.getYearBorn());
        
        
        //Test that the start date can be retrieved
        System.out.printf("%s started in the year %s", st2, st2.getYearEnrolled());        
    }   //end of main method
}   //end of class
