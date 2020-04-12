package posidon.tasks

import android.content.Context
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class Adapter(private val context: Context, var tasks: ArrayList<String>) : BaseAdapter() {

    override fun getCount() = tasks.size
    override fun getItem(position: Int) = null
    override fun getItemId(position: Int): Long = 0

    internal class ViewHolder {
        var txt: TextView? = null
        var done: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val viewHolder: ViewHolder
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (convertView == null) {
            convertView = li.inflate(R.layout.task, null)
            viewHolder = ViewHolder()
            viewHolder.txt = convertView.findViewById(R.id.txt)
            viewHolder.done = convertView.findViewById(R.id.done)
            convertView.tag = viewHolder
        } else viewHolder = convertView.tag as ViewHolder
        viewHolder.txt!!.text = tasks[position]
        viewHolder.done!!.setOnClickListener {
            val v1 = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            v1.vibrate(14)
            tasks.removeAt(position)
            FileStuff.writeData(tasks, context)
            notifyDataSetChanged()
        }
        return convertView
    }
}