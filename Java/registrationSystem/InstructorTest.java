/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registrationSystem;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author jwright
 */
public class InstructorTest {

    public static void main(String[] args)
    {
        System.out.printf("~~~~~~~Test 1: create a valid Instructor and call the toString() method (2 marks)~~~~~~~");
        Instructor jDub = new Instructor("Jaret","Wright",1000012,"10 Awesome Lane","Barrie","L2S 1A6", LocalDate.of(2010, Month.AUGUST, 22), LocalDate.of(1962, Month.JUNE, 6));
        System.out.printf("%nExpected output: The Instructor is: Jaret Wright", jDub);
        System.out.printf("%nActual output:   The Instructor is: %s", jDub);
        
        
        System.out.printf("%n%n~~~~~~~Test 2: Display the Instructor's age (1 mark)~~~~~~~");
        System.out.printf("%nExpected output: Jaret Wright is 53 years old");
        System.out.printf("%nActual output:   %s is %d years old.", jDub, jDub.getAgeInYears());
        
        
        System.out.printf("%n%n~~~~~~~Test 3: Display how long the Instructor has been at the college (2 marks)~~~~~~~");
        System.out.printf("%nExpected output: Jaret Wright has been at the college 5 years.");
        System.out.printf("%nActual output:   %s has been at the college %d years.", jDub, jDub.yearsAtCollege());
        
        
        System.out.printf("%n%n~~~~~~~Test 4: Display the Instructor's address (1 mark)~~~~~~~");
        System.out.printf("%nExpected output: The address of Jaret Wright is 10 Awesome Lane, Barrie, L2S 1A6");
        System.out.printf("%nActual output:   The address of %s is %s", jDub, jDub.getAddress());
        
        System.out.printf("%n%n~~~~~~~Test 5: Change the Instructor address and display the result (2 marks)~~~~~~~");
        jDub.changeAddress("101 Spring St.","Utopia","L3S 2A2");
        System.out.printf("%nExpected output: The address of Jaret Wright is 101 Spring St., Utopia, L3S 2A2");
        System.out.printf("%nActual output:   The address of %s is %s", jDub, jDub.getAddress());
        
        System.out.printf("%n%n~~~~~~~Test 6: Display the courses the Instructor can teach (2 marks)~~~~~~~");
        System.out.printf("%nExpected output: Jaret Wright can teach the following courses: not qualified to teach courses yet.");
        System.out.printf("%nActual output:   %s can teach the following courses: %s", jDub, jDub.listOfSubjectsCertifiedToTeach());
        
        System.out.printf("%n%n~~~~~~~Test 7: Add some courses for the instructor to teach (2 marks)~~~~~~~");
        jDub.addCourseToAbilities("COMP1008");
        jDub.addCourseToAbilities("COMP2003");
        System.out.printf("%nExpected output: Jaret Wright can teach the following courses: [COMP1008, COMP2003]");
        System.out.printf("%nActual output:   %s can teach the following courses: %s", jDub, jDub.listOfSubjectsCertifiedToTeach());     
        
        System.out.printf("%n%n~~~~~~~Test 8: Test if the canTeach() identifies that an instructor cannot teach a course (1 marks) ~~~~~~~");
        System.out.printf("%nExpected output: Jaret Wright is certified to teach \"COMP3002\": false");
        System.out.printf("%nActual output:   %s is certified to teach \"COMP3002\": %s", jDub, jDub.canTeach("COMP3002"));        
        
        System.out.printf("%n%n~~~~~~~Test 9: Test if the canTeach() identifies that an instructor can teach a course (1 mark)~~~~~~~");
        System.out.printf("%nExpected output: Jaret Wright is certified to teach \"COMP1008\": true");
        System.out.printf("%nActual output:   %s is certified to teach \"COMP1008\": %s", jDub, jDub.canTeach("COMP1008"));
        
        System.out.printf("%n%n~~~~~~~Test 10: Test creating an Instructor that is too old (2 marks) ~~~~~~~");
        System.out.printf("%nExpected output: The birthday submitted for Al Dente indicates that he/she is over 100 years old.  The birthday is set to null.");
        System.out.printf("%nActual output:   ");
        Instructor oldMan = new Instructor("Al","Dente",1000013,"16 Ancient Ave","Innisfil","W32S 1A6", LocalDate.of(2010, Month.AUGUST, 22), LocalDate.of(1901, Month.DECEMBER, 21));
        
        System.out.printf("%n%n~~~~~~~Test 11: Test changing a birthday with a valid date (1 mark) ~~~~~~~");
        oldMan.setBirthday(LocalDate.of(1971, Month.FEBRUARY, 21));
        System.out.printf("%nExpected output: Al Dente is 45 years old.");
        System.out.printf("%nActual output:   %s is %d years old.", oldMan, oldMan.getAgeInYears());
        
        System.out.printf("%n%n~~~~~~~Test 12: Test creating an Instructor that has an invalid hire date (2 marks) ~~~~~~~");
        System.out.printf("%nExpected output: The hired on date for Anita Knapp is more than 80 years ago, it has been set to null.");
        System.out.printf("%nActual output:   ");
        Instructor hiredLongAgo = new Instructor("Anita","Knapp",1000014,"16 Ancient Ave","Innisfil","W32S 1A6", LocalDate.of(1910, Month.AUGUST, 22), LocalDate.of(1981, Month.DECEMBER, 21));
        
        System.out.printf("%n%n");
    }        
}
