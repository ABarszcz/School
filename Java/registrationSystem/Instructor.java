package registrationSystem;

import java.time.LocalDate;
import java.util.ArrayList;

/** A class that will create an Instructor object
 * @author Anthony Barszcz
 */
public class Instructor {
	private String firstName, lastName, streetAddress, city, postalCode;
	private ArrayList<String> teachableCourses = new ArrayList<String>();
	private int employeeNum;
	private LocalDate hireDate, birthDate;
	
	/**
	 * Creates the instructor object
	 * @param firstName - the instructor's first name
	 * @param lastName - the instructor's last name
	 * @param employeeNum - ~ employee number
	 * @param streetAddress - ~ street address
	 * @param city - ~ city
	 * @param postalCode - ~ postal code
	 * @param hireDate - ~ hire date
	 * @param birthDate - ~ birth date
	 */
	public Instructor(String firstName, String lastName, int employeeNum, String streetAddress, String city,
			String postalCode, LocalDate hireDate, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeNum = employeeNum;
		this.streetAddress = streetAddress;
		this.city = city;
		this.postalCode = postalCode;
		setHireDate(hireDate);
		setBirthday(birthDate);
	} //End of Instructor constructor
	
	/** Validates if an instructor can teach a course.
	 *  @param courseCode - The course the instructor is being tested on
	 *  @return canTeach - Returns whether or not the instructor can teach that course
	 */
	public boolean canTeach(String courseCode) {
		if(this.teachableCourses.contains(courseCode))
			return true;
		else
			return false;
	} //End of canTeach
	
	/** Returns the courses that the instructor is able to teach
	 * @return list - a string containing all the courses the instructor can teach
	 */
	public String listOfSubjectsCertifiedToTeach() {
		String list="";
		for(int i=0; i<this.teachableCourses.size(); i++) {
			list = list + this.teachableCourses.get(i);
			if(i < this.teachableCourses.size()-1)
				list += ", ";
		} //End of loop
		
		//Clean up the list
		list = "[" + list + "]";
		
		//Display message if list is empty
		if(list.equals("[]"))
			list = "not qualified to teach courses yet.";
		
		return list;
	} //End of listOfSubjectsCertifiedToTeach
	
	/** Adds a course to the courses the instructor is able to teach
	 * @param course - the new course being added
	 */
	public void addCourseToAbilities(String course) {
		if(!teachableCourses.contains(course))
			this.teachableCourses.add(course);
	} //End of addCourseToAbilities
	
	/** Checks to see if the instructor is under 100 years old.
	 *  If they are the correct age, set their new birth date.
	 *  @param birthDate - the instructor's birth date
	 */
	public void setBirthday(LocalDate birthDate) {
		int age = LocalDate.now().getYear() - birthDate.getYear();
		if(birthDate.getDayOfYear() > LocalDate.now().getDayOfYear())
			age = age - 1;
		
		if(age < 100) {
			this.birthDate = birthDate;
		} else {
			throw new IllegalArgumentException
				(birthDate + " would make " + this.firstName + " " + this.lastName + 
				" over 100 years old");
		} //End of if construct
	} //End of setBirthday
	
	/** Returns the instructor's age in years
	 * @return the current year subtracted by the year the instructor was born
	 */
	public int getAgeInYears() {
		int age=0;
		age = LocalDate.now().getYear() - this.birthDate.getYear();
		
		if(this.birthDate.getDayOfYear() > LocalDate.now().getDayOfYear())
			age = age - 1;
		
		return age;
	} //End of getAgeInYears
	
	/** Checks to see if the instructor was hired in the last 80 years.
	 *  If the hire date is correct, set their new hire date.
	 *  @param hireDate - the instructor's hire date
	 */
	public void setHireDate(LocalDate hireDate) {
		int yearsAtCollege = LocalDate.now().getYear() - hireDate.getYear();
		if(hireDate.getDayOfYear() > LocalDate.now().getDayOfYear())
			yearsAtCollege = yearsAtCollege - 1;
		
		if(yearsAtCollege < 80) {
			this.hireDate = hireDate;
		} else {
			throw new IllegalArgumentException
				(hireDate + " as a hire date would mean " + this.firstName +
				" started working over 80 years ago");
		} //End of if construct
	} //End of setHireDate
	
	/** Returns the number of years the instructor has worked at the college
	 * @return current year subtracted by the year the instructor was hired
	 */
	public int yearsAtCollege() {
		int yearsAtCollege=0;
		yearsAtCollege = LocalDate.now().getYear() - this.hireDate.getYear();
		
		if(this.hireDate.getDayOfYear() > LocalDate.now().getDayOfYear())
			yearsAtCollege = yearsAtCollege - 1;
		
		return yearsAtCollege;
	} //End of yearsAtCollege
	
	/** Returns the instructor's first name and last name
	 *  @return firstName - the Instructor's first name
	 *  @return lastName - the Instructor's last name
	 */
	@Override
	public String toString() {return this.firstName + " " + this.lastName;}
	
	/** Changes the instructor's street address
	 * @param newStreetAddress - The new street address
	 * @param newCity - The new city of residence
	 * @param newPostalCode - The new postal code
	 */
	public void changeAddress(String newStreetAddress, String newCity, String newPostalCode) {
		this.streetAddress = newStreetAddress;
		this.city = newCity;
		this.postalCode = newPostalCode;
	} //End of changeAddress
	
	/** Returns the instructor's address
	 * @return streetAddress - The instructor's street address
	 * @return city - The instructor's city of residence
	 * @return postalCode - The instructor's postal code
	 */
	public String getAddress() {return this.streetAddress + ", " + this.city + ", " + this.postalCode;}
} //End of class
