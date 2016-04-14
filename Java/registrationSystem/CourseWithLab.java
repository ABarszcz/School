package registrationSystem;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * @author Anthony Barszcz - 200318107
 */
public class CourseWithLab extends Course {
	private Instructor labTech;
	private String labRoom;
	private DayOfWeek labDay;
	private LocalTime labStartTime;

	/**
	 * Extends the course object, adding parameters to match a course with a lab, without a prerequisite
	 * @param prof - the course's instructor
	 * @param courseCode - the course's course code
	 * @param courseName - ~ course name
	 * @param room - ~ room
	 * @param classDay - the day of the week the course takes place on
	 * @param startTime - the start time of the course
	 * @param maxStudents - the maximum number of students allowed in the course
	 * @param labTech - the instructor for the lab
	 * @param labRoom - the room the lab takes place in
	 * @param labDay - the day the lab takes place
	 * @param labStartTime - the start time of the lab
	 */
	public CourseWithLab(Instructor prof, String courseCode, String courseName, String room,
			DayOfWeek classDay, LocalTime startTime, int maxStudents, Instructor labTech,
			String labRoom, DayOfWeek labDay, LocalTime labStartTime) {
		super(prof, courseCode, courseName, room, classDay, startTime, maxStudents);
		
		setLabTech(labTech);
		this.labRoom = labRoom;
		this.labDay = labDay;
		setLabStartTime(labStartTime);
	} //End of CourseWithLab
	
	/**
	 * Extends the course object, adding parameters to match a course with a lab, with a prerequisite
	 * @param prof - the course's instructor
	 * @param courseCode - the course's course code
	 * @param courseName - ~ course name
	 * @param room - ~ room
	 * @param classDay - the day of the week the course takes place on
	 * @param startTime - the start time of the course
	 * @param maxStudents - the maximum number of students allowed in the course
	 * @param preReq - the course code of the prerequisite course
	 * @param labTech - the instructor for the lab
	 * @param labRoom - the room the lab takes place in
	 * @param labDay - the day the lab takes place
	 * @param labStartTime - the start time of the lab
	 */
	public CourseWithLab(Instructor prof, String courseCode, String courseName, String room,
			DayOfWeek classDay, LocalTime startTime, int maxStudents, String preReq,
			Instructor labTech, String labRoom, DayOfWeek labDay, LocalTime labStartTime) {
		super(prof, courseCode, courseName, room, classDay, startTime, maxStudents);
		
		this.preReq = preReq;
		setLabTech(labTech);
		this.labRoom = labRoom;
		this.labDay = labDay;
		setLabStartTime(labStartTime);
	} //End of CourseWithLab
	
	/** Returns the course name and the course code
	 *  @return courseName - the name of the course
	 *  @return courseCode - the course code
	 */
	@Override
	public String toString() {return super.toString() + " with lab";} //End of toString
	
	public void setLabTech(Instructor labTech) {
		if(labTech.canTeach(this.courseCode+"-LAB"))
			this.labTech = labTech;
		else {
			throw new IllegalArgumentException
			("The Lab Tech is not qualified to host the lab");
		} //End of if construct
	} //End of setLabTech
	
	/** Validates and sets the start time of the lab
	 *  @param startTime - the time the lab will start at
	 */
	public void setLabStartTime(LocalTime startTime) {
		if(startTime.isAfter(LocalTime.of(7,59)) && startTime.isBefore(LocalTime.of(16,1))) {
			this.startTime = startTime;
		} else { 
			this.startTime = LocalTime.of(8,0);
			throw new IllegalArgumentException
				("The lab start time must be between 08:00-18:00");
		} //End of if construct
	} //End of setLabStartTime
	
	/** Returns the lab technician object
	 *  @return labTech
	 */
	public Instructor getLabTech() {return labTech;}
	
	/** Returns the start time of the lab
	 *  @return labStartTime - the start time of the lab
	 */
	public LocalTime getLabStartTime() {return labStartTime;}
	
	/** Returns information about how to attend the lab
	 * @return - room number, the day of the lab, and the start time
	 */
	public String getLabClassAndTime() {
		return "room: " + this.labRoom + ", "
				+ this.labDay.toString() +
				" starting at " + this.startTime();
	} //End of getLabClassAndTime
} //End of class
