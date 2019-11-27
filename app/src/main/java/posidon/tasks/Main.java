package posidon.tasks;

import android.app.ActivityManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

	private EditText taskTxt;
	private ArrayList<String> tasks;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ActivityManager.TaskDescription description = new ActivityManager.TaskDescription("tasks", BitmapFactory.decodeResource(getResources(), R.mipmap.recentic), 0xFF111213);
		setTaskDescription(description);

		taskTxt = findViewById(R.id.tasktxt);
		tasks = FileStuff.readData(this);
		((ListView)findViewById(R.id.list)).setAdapter(new Adapter(this, tasks));

		taskTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				String task = taskTxt.getText().toString();
				tasks.add(task);
				v.setText("");
				FileStuff.writeData(tasks, Main.this);
				return false;
			}
		});
		
		getWindow().setNavigationBarColor(0xFF000000);
	}
}
