package posidon.tasks;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class FileStuff {
	private static final String fileName = "tasklist.dat";
	static void writeData(ArrayList<String> tasks, Context context) {
		try {
			FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tasks);
			oos.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	static ArrayList<String> readData(Context context) {
		ArrayList<String> tasks = null;
		try {
			FileInputStream fis = context.openFileInput(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			tasks = (ArrayList<String>) ois.readObject();
		}
		catch (FileNotFoundException e) { tasks = new ArrayList<>(); }
		catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
		return tasks;
	}
}
