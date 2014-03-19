package il.ac.huji.todolist;

import java.util.Date;
/**
 * Represents task consisting of title and due date
 */
public class Task {
	// Title of the task
	private String title;
	// String representation of due date
	private String dueDate;
	// Due date
	private Date date;
	/**
	 * Creates new task with given due date and title
	 * @param date date object
	 * @param title title of task
	 */
	public Task(Date date, String title) {
		this.title = title;
		this.date = date;
		if (date != null) 
			this.dueDate = ApplicationConstants.DATE_FORMAT.format(date);
		else
			this.dueDate = ApplicationConstants.NULL_DATE_MSG;		
	}
	/**
	 * Returns date
	 * @return due date
	 */
	public Date getDate() {
		return this.date;
	}
	/**
	 * Returns string representation of due date in the format
	 * defined {@link ApplicationConstants#DATE_FORMAT}
	 * @return string representation of due date
	 */
	public String getDateString() {
		return this.dueDate;
	}
	/**
	 * Returns title of the task
	 * @return title of the task
	 */
	public String getTitle() {
		return this.title;
	}
	
	@Override 
	public boolean equals(Object o) {
		if (o instanceof Task) {
			Task t = (Task) o;
			return (this.dueDate.equals(t.dueDate) && this.title.equals(t.title));
		}
		return false;
	}
}
