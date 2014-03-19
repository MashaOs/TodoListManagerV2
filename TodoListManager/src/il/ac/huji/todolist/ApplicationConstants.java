package il.ac.huji.todolist;

import java.text.SimpleDateFormat;

import android.graphics.Color;

/**
 * Constants used in application, i.e. in java classes.
 */
public class ApplicationConstants {
	/**
	 * When user types text starting with "Call ", additional 
	 * menu item, enabling dialing a number, is shown 
	 */
	public static final String CALL_PREFIX = "Call ";
	/**
	 * Phone pattern
	 */
	public static final String PHONE_PATTERN = "[\\d-]+";
	/**
	 * Name used for passing list item title from the {@link AddNewTodoItemActivity}
	 * to {@link TodoListManagerActivity} 
	 */
	public static final String ITEM_TITLE_VAR = "title";
	/**
	 * Name used for passing typed date from the {@link AddNewTodoItemActivity}
	 * to {@link TodoListManagerActivity} 
	 */
	public static final String DATE_VAR = "dueDay";
	/**
	 * Message shown when no date is chosen
	 */
	public static final String NULL_DATE_MSG = "No due date";
	/**
	 * Date format
	 */
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Default color of list items
	 */
	public static final int DEFAULT_ITEM_COLOR = Color.BLACK;
	/**
	 * Color of overdue list items
	 */
	public static final int EXPIRED_TASK_COLOR = Color.RED;
}
