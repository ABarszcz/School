package registrationSystem;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class will take all the information about a student and create an object
 * of that student.
 * @author Anthony Barszcz
 */
public class Student {
	private String firstName, lastName, streetAddress, city, postalCode, major;
	private int studentNum;
	private LocalDate enrolDate, birthDate;
	private boolean goodStanding;
	private ArrayList<CompletedCourse> completedCourses = new ArrayList<>();
	
	/**
	 * Create the student object that contains all information about a student
	 * @param firstName - Student's first name
	 * @param lastName - Student's last name
	 * @param streetAddress - Student's street address
	 * @param city - Student's city of residence
	 * @param postalCode - Student's postal code
	 * @param major - Student's major
	 * @param studentNum - Student's student number
	 * @param enrolDate - Student's enroll date
	 * @param birthDate - Student's birth date
	 * @param goodStanding - Student's standing status
	 */
	public Student(String firstName, String lastName, String streetAddress, String city,
			String postalCode, String major, int studentNum, LocalDate enrolDate, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.postalCode = postalCode;
		this.major = major;
		this.studentNum = studentNum;
		this.enrolDate = enrolDate;
		setBirthday(birthDate);
		this.goodStanding = true;
	} //End of Student constructor
	
	/** Returns the student's name
	 *  @return firstName - the Student's first name 
	 *  @return lastName - the Student's last name 
	 *  @return studentNum - the Student's student number */
	@Override
	public String toString() {return this.firstName + " " + this.lastName + ", student number: " + studentNum;}
	
	/** Gets the year the student was born in
	 * @return birthDate - the student's birth date */
	public int getYearBorn() {return this.birthDate.getYear();}
	
	/** Gets the current age of the student
	 * @return studentAge - the student's current age */
	public int getAge() {
		int age = 0;
		if(this.birthDate!=null) {
			age = LocalDate.now().getYear() - this.birthDate.getYear();
			if(this.birthDate.getDayOfYear() > LocalDate.now().getDayOfYear())
				age = age - 1;
		} //End of if
		return age;
	} //End of getAge()
	
	/** Gets the year the student was enrolled in
	 *  @return enrolDate - the Student's date of enrollment */
	public int getYearEnrolled() {return this.enrolDate.getYear();}
	
	/** Gets the address of the student
	 *  @return streetAddress - the Student's street address
	 *  @return city - the Student's city of residence
	 *  @return postalCode - the Student's postal code */
	public String getAddress() {return this.streetAddress + " " + this.city + " " + this.postalCode;}
	
	/** Gets the standing status of the student
	 *  @return goodStanding - whether or not the student is in good standing */
	public boolean inGoodStanding() {return this.goodStanding;}
	
	/** Sets the Student's first and last name
	 *  @param newFirstName - the Student's new first name
	 *  @param newLastName - the Student's new last name */
	public void setName(String newFirstName, String newLastName) {
		this.firstName = newFirstName; 
		this.lastName = newLastName;
	} //End of setName
	
	/** Sets the student's new address
	 *  @param newStreetAddress - the student's new street address
	 *  @param newCity - the student's new city 
	 *  @param newPostalCode - the student's new postal code */
	public void changeAddress(String newStreetAddress, String newCity, String newPostalCode) {
		this.streetAddress = newStreetAddress;
		this.city = newCity;
		this.postalCode = newPostalCode;
	} //End of changeAddress
	
	/** Checks to see if the student is under 100 years old.
	 *  If they are not, set their new birth date
	 *  @param newBirthDate - The student's new birth date */
	public void setBirthday (LocalDate birthDate) {
		int age = LocalDate.now().getYear() - birthDate.getYear();
		if(birthDate.getDayOfYear() > LocalDate.now().getDayOfYear())
			age = age - 1;
		
		if(age < 100) {
			this.birthDate = birthDate;
		} else {
			throw new IllegalArgumentException
				(birthDate + " would make " + this.firstName + " over 100 years old");
		} //End of if construct
	} //End of setBirthday
	
	/** Gets the student's birthday
	 *  @return this.birthDate - the student's birthdate */
	public LocalDate getBirthday() {return this.birthDate;}
	
	/** Gets the student's student number
	 *  @return this.studentNum - the student's student number */
	public int getStudentNumber() {return this.studentNum;}
	
	/** Suspends the student */
	public void suspendStudent() {this.goodStanding = false;}
	
	/** Reinstates the student */
	public void reinstateStudent() {this.goodStanding = true;}
	
	/** Add a completed course to the student's record
	 * @param course - the course to be added to the record
	 * @param grade - the grade they achieved */
	public void addCompletedCourse(Course course, int grade) {
		if(grade > 0 && grade < 101) {
			if(grade > 49) {
				CompletedCourse completedCourse = new CompletedCourse(course, grade);
				completedCourses.add(completedCourse);
			}
		} else
		throw new IllegalArgumentException("grade must be 0-100 inclusive");
	} //End of addCompletedCourses
	
	/** Get a list of the courses completed by the student
	 * @return list - a formatted list of completed courses */
	public String getCompletedCourses() {
		String list="";
		for(int i=0; i<this.completedCourses.size(); i++) {
			list = list + this.completedCourses.get(i);
			if(i < this.completedCourses.size()-1)
				list += ", ";
		} //End of loop
		
		//Clean up the list
		list = "[" + list + "]";
		
		//Display message if list is empty
		if(list.equals("[]"))
			list = "not qualified to teach courses yet.";
		
		return list;
	} //End of getCompletedCourses
	
	/** Check to see if the student has completed a course
	 * @param courseCode - the course code of the course to be checked
	 * @return - whether or not they've completed it */
	public boolean hasCompleted(String courseCode) {
		if(this.getCompletedCourses().contains(courseCode))
			return true;
		else
			return false;
	} //End of hasCompleted
} //End of Student class
