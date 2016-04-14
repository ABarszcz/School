package registrationSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/** A class that will create a course object
 * @author Anthony Barszcz
 */
public class Course {
	private Instructor prof;
	protected String courseCode;
	protected String preReq;
	private String courseName;
	private String room;
	private ArrayList<Student> classList = new ArrayList<>();
	private int maxStudents, numOfStudents;
	private boolean classIsFull;
	private DayOfWeek classDay;
	protected LocalTime startTime;
	
	/**
	 * Creates a course object without any prerequisite courses
	 * @param prof - the course's instructor
	 * @param courseCode - the course's course code
	 * @param courseName - ~ course name
	 * @param room - ~ room
	 * @param classDay - the day of the week the course takes place on
	 * @param startTime - the start time of the course
	 * @param maxStudents - the maximum number of students allowed in the course
	 */
	public Course (Instructor prof, String courseCode, String courseName, String room,
			DayOfWeek classDay, LocalTime startTime, int maxStudents) {
		this.courseCode = courseCode;
		setProf(prof);
		this.courseName = courseName;
		this.room = room;
		this.classDay = classDay;
		setTime(startTime);
		setClassSize(maxStudents);
	} //End of constructor without prerequisite
	
	/**
	 * Creates a course object with prerequisite course(s)
	 * @param prof - the course's instructor
	 * @param courseCode - the course's course code
	 * @param courseName - ~ course name
	 * @param room - ~ room
	 * @param classDay - the day of the week the course takes place on
	 * @param startTime - the start time of the course
	 * @param maxStudents - the maximum number of students allowed in the course
	 * @param preReq - the course code of the prerequisite course
	 */
	public Course (Instructor prof, String courseCode, String courseName, String room,
			DayOfWeek classDay, LocalTime startTime, int maxStudents, String preReq) {
		this.courseCode = courseCode;
		setProf(prof);
		this.preReq = preReq;
		this.courseName = courseName;
		this.room = room;
		this.classDay = classDay;
		setTime(startTime);
		setClassSize(maxStudents);
	} //End of constructor
	
	/** Checks to see if the professor can teach the class.
	 * @param prof - the professor object being tested
	 */
	public void setProf(Instructor prof) {
		if(prof.canTeach(this.courseCode))
			this.prof = prof;
		else {
			throw new IllegalArgumentException
			("Professor " + prof.toString() + " is not qualified to teach " + this.courseCode);
		} //End of if construct
	} //End of setProf
	
	/** This checks to see if the start time is within range
	 *  @param startTime - The start time of the class
	 */
	public void setTime(LocalTime startTime) {
		if(startTime.isAfter(LocalTime.of(7,59)) && startTime.isBefore(LocalTime.of(16,1))) {
			this.startTime = startTime;
		} else { 
			this.startTime = LocalTime.of(8,0);
			throw new IllegalArgumentException
				("Course start time must be between 08:00-18:00");
		} //End of if construct
	} //End of setTime
	
	/** This returns the start time of the course.
	 *  @return startTime - the start time of the course
	 */
	public String startTime() {return this.startTime.toString();}
	
	/** Method to check if the max number of students is correct
	 *  @param maxStudents - the requested maximum number of students for the class
	 */
	public String setClassSize(int maxStudents) {
		if(maxStudents < 41) {
			this.maxStudents = maxStudents;
			return "Class size was set to " + maxStudents;
		} else {
			this.maxStudents = 40;
			return "Max class size = 40, it has been set to 40";
		} //End of if construct
	} //End of classSize
	
	public int getClassSize() { return this.maxStudents; }
	
	/** This gets the day of week that the course takes place on
	 *  @return classDay - the day of week the class takes place on
	 */
	public String getDayOfCourse() {return this.classDay.toString();}
	
	/** Returns the room number in which the course takes place in
	 * @return room - the room number of the course
	 */
	public String getRoom() {return this.room;}
	
	/** Tries to add a student to a class. Will display error messages if the student is ineligable or
	 * if the class is full
	 * @param student - the student object being tested
	 */
	public String addStudent(Student student) {
		//First make sure the student is in good standing
		if(student.inGoodStanding()) {
			//Then check to see if class is full
			if(!this.classIsFull) {
				//Check if there is a prerequisite
				if(this.preReq != null) { //If there is a prereq...
					//If there is, check to see if the student has it
					if(student.getCompletedCourses().contains(this.preReq)) { //and they meet it...
						this.classList.add(student); //Add the student to the class list
						this.numOfStudents++; //Count the number of students in the class
						//Check to see if the class is now full
						if(this.numOfStudents == this.maxStudents)
						this.classIsFull=true;
						return "Student was added to the course.";
					} else
						return "Student has not completed the prerequisite course: " + this.getPrerequisite();
				} //End of null check
				else { //If there is no pre-req...
					this.classList.add(student); //Add the student to the class list
					this.numOfStudents++; //Count the number of students in the class
					//Check to see if the class is now full
					if(this.numOfStudents == this.maxStudents)
						this.classIsFull=true;
					
					return "Student was added to the course";
				}
			} else //End of full class check
				return "Student was not added because the course is full";
		} else //End of good standing check
			return "The Student is not in good standing and cannot join the course.";
	} //End of addStudent
	
	/** Displays the list of students in the course
	 *  @return list - list of students in the class
	 */
	public String showClassList() {
		String list = "";
		for(int i=0; i<this.classList.size(); i++) {
			list = list + this.classList.get(i).toString();
			//Add a new-line when the loop is about to end
			if(!(i == this.classList.size() - 1))
				list += "\n";
		}
		return list;
	} //End of showClassList
	
	
	/** Shows the average number of years students in the course have been at the college
	 *  @return - A string including the average number of years the students have been in the college
	 */
	public float averageStudentTimeAtCollege() {
		//Temporary doubles for mathematical operations
		float yearsAtCollege=0, sumOfYears=0, avgYears=0;
		
		//Loop to cycle through each student in the course
		for(int i=0; i<this.classList.size(); i++) {
			yearsAtCollege = LocalDate.now().getYear() - classList.get(i).getYearEnrolled() -1;
			sumOfYears=sumOfYears+yearsAtCollege;
		} //End of loop
		
		avgYears = sumOfYears / this.classList.size();
		return avgYears;
	} //End of averageStudentTimeAtCollege
	
	/** Returns the course name and the course code
	 *  @return courseName - the name of the course
	 *  @return courseCode - the course code
	 */
	@Override
	public String toString() {return courseCode + "-" + courseName;} //End of toString
	
	/** Returns the instructor of the course using the instructor toString
	 *  @return - the first and last name of the instructor
	 */
	public Instructor getInstructor() {return this.prof;}
	
	/** Returns the week day and start time of the course
	 * @return classDay - the day of the week the class is on
	 * @return startTime - the start time of the class
	 */
	public String getCourseDayAndTime() {
		return this.classDay + "'s, starting at " + this.startTime.toString();
	} //End of getCourseDayAndTime
	
	/** Returns whether or not the class is a mature class
	 *  @return true if average age of students is above 25
	 */
	public boolean matureClass() {
		int totalAge=0, avgAge=0;
		//Add the ages together
		for(int i=0; i<this.classList.size(); i++) {
			totalAge = totalAge + classList.get(i).getAge();
		} //End of loop
		//Divide by num of students to get average
		avgAge = totalAge / this.numOfStudents;
		
		if(avgAge > 25)
			return true;
		else
			return false;
	} //End of matureClass
	
	/** Get the prerequisite required for the course
	 * @return this.preReq - the course code of the pre-requisite course
	 */
	public String getPrerequisite() {return this.preReq;}
} //End of class
