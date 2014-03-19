package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

/**
 * TodoListManager Application.
 * Tested on Custom Phone 7 - 4.3 - API 18 - 1024x600 by Genymotion.
 * Empty text cannot be added.
 * User:	masha_os
 *   ID:	332508373
 */
public class TodoListManagerActivity extends Activity {
	private static int REQ_RESULT_FOR_ADD = 42;
	// list view
	private ListView list;
	// custom list adapter setting alternating colors to items in the list
	private MyListAdapter lstAdpr;
	
	/**
	 * Creates ArrayAdapter for the ListView and registers context menu for the list.
	 */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        this.list = (ListView) findViewById(R.id.lstTodoItems);
        
        // Top Menu
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setIcon(R.drawable.add);
        actionBar.show();
        
        // ListView
        ArrayList<Task> l = new ArrayList<Task>();
        this.lstAdpr = new MyListAdapter(this, android.R.layout.simple_list_item_1, l); 
        this.list.setAdapter(this.lstAdpr); 
        
        // Context Menu
        registerForContextMenu(this.list);
    }
    
    
    /**
     * Inflates the menu: adds items to the action bar if it is present.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_list_menu, menu);
        return true;
    }
    
    /**
     * Creates context menu enabling removing elements from the list, calling.
     * Invoked on long-clicking on an item.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo info) { 
    	 super.onCreateContextMenu(menu, v, info);    	 
    	 AdapterView.AdapterContextMenuInfo in = (AdapterView.AdapterContextMenuInfo)info;
    	 String selItemVal = lstAdpr.getItem(in.position).getTitle();
    	 menu.setHeaderTitle(selItemVal);	
    	 if (selItemVal.startsWith(ApplicationConstants.CALL_PREFIX)) 
    		 menu.add(Menu.NONE, R.id.menuItemCall, 1, selItemVal);
    	 MenuInflater inflater = getMenuInflater();
    	 inflater.inflate(R.menu.context_menu, menu);    	 
    } 
    
    /**
     * Invoked on pressing context menu button.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	 AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	 String selectedValue = this.lstAdpr.getItem(info.position).getTitle();
    	 switch (item.getItemId()) {
    	 case R.id.menuItemDelete:
    		 this.lstAdpr.remove(this.lstAdpr.getItem(info.position));
    		 this.lstAdpr.notifyDataSetChanged(); 
    		 break;
    	 case R.id.menuItemCall:
    	     Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
    	     phoneIntent.setData(Uri.parse("tel:" + selectedValue));
    	     startActivity(phoneIntent);
    		 break;
    	 default:
    		 break;
    	 }
    	 return true;
    }        
    /**
     * Invoked on pressing 'add' button in the main menu.
     * Adds a new item.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       	if (item.getItemId() == R.id.menuItemAdd) {
       		Intent intent = new Intent(this, AddNewTodoItemActivity.class); 
       		startActivityForResult(intent, REQ_RESULT_FOR_ADD);
       	}
		return true;
    }
    
    
    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent result) { 
    	if (reqCode == REQ_RESULT_FOR_ADD && resCode == Activity.RESULT_OK) {
    		if (result == null) {
    			Log.e("onActivityResult", "null result");
    			return;
    		}
    		String txt = result.getStringExtra(ApplicationConstants.ITEM_TITLE_VAR);
   	       	if (txt == null || txt.isEmpty()) 
   	       		return;
    		Date dueDate = (Date) result.getSerializableExtra(ApplicationConstants.DATE_VAR);
	        this.lstAdpr.add(new Task(dueDate, txt));
   	        this.lstAdpr.notifyDataSetChanged();
    	}
	} 
}
