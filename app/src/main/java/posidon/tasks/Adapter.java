package posidon.tasks;

import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import static android.content.Context.VIBRATOR_SERVICE;

public class Adapter extends BaseAdapter {

    private Context context;
    ArrayList<String> tasks;

    public Adapter(Context context, ArrayList<String> tasks){
        this.context = context;
        this.tasks = tasks;
    }

	@Override
    public int getCount() { return tasks.size(); }

    @Override
    public Object getItem(int position) { return null; }

    @Override
    public long getItemId(int position) { return 0; }

    static class ViewHolder {
        TextView txt;
        ImageView done;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView==null){
            convertView = li.inflate(R.layout.task, null);
            viewHolder = new ViewHolder();
            viewHolder.txt = convertView.findViewById(R.id.txt);
            viewHolder.done = convertView.findViewById(R.id.done);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.txt.setText(tasks.get(position));
        viewHolder.done.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Vibrator v1 = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
				if (v1 != null) v1.vibrate(14);
				tasks.remove(position);
				FileStuff.writeData(tasks, context);
				notifyDataSetChanged();
			}
		});
        return convertView;
    }
}