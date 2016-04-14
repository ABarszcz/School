package registrationSystem;

/**
 * @author Anthony Barszcz - 200318107
 */
public class CompletedCourse {
	private Course completedCourse;
	private int studentGrade;
	
	/** Creates a completedCourse object, storing the course object and the grade achieved
	 * @param completedCourse - the course completed
	 * @param studentGrade - the grade the student achieved
	 */
	public CompletedCourse(Course completedCourse, int studentGrade) {
		this.completedCourse = completedCourse;
		setGrade(studentGrade);
	} //End of constructor
	
	/** Validates and sets the student's grade for the course
	 * @param grade - the final grade of the student in the course
	 */
	public void setGrade(int grade) {
		if(grade > 0 && grade < 101)
			this.studentGrade = grade;
		else
			throw new IllegalArgumentException("grade must be 0-100 inclusive");
	} //End of setGrade
	
	/** Returns the grade the student got in the course
	 * @return studentGrade - the student's grade
	 */
	public int getGrade() {return this.studentGrade;}
	
	/** Returns the course the student's grade was saved in
	 * @return course - the course
	 */
	public Course getCourse() {return this.completedCourse;}
	
	/** toString to get information about the completed course
	 *  @return course - the course itself
	 *  @return grade - the student's grade
	 */
	@Override
	public String toString() {return this.completedCourse + " grade=" + this.studentGrade;}
} //End of class
