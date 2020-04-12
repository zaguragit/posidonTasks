package posidon.tasks

import android.app.ActivityManager.TaskDescription
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Main : AppCompatActivity() {

    private lateinit var taskTxt: EditText
    private lateinit var tasks: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        val description = TaskDescription("tasks", BitmapFactory.decodeResource(resources, R.mipmap.recentic), -0xeeeded)
        setTaskDescription(description)
        taskTxt = findViewById(R.id.tasktxt)
        tasks = FileStuff.readData(this)
        (findViewById<View>(R.id.list) as ListView).adapter = Adapter(this, tasks)
        taskTxt.setOnEditorActionListener { v, _, _ ->
            val task = taskTxt.text.toString()
            tasks.add(task)
            v.text = ""
            FileStuff.writeData(tasks, this@Main)
            false
        }
        window.navigationBarColor = -0x1000000
    }
}