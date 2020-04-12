package posidon.tasks

import android.content.Context
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.util.*

object FileStuff {

    private const val fileName = "tasklist.dat"

    fun writeData(tasks: ArrayList<String>, context: Context) {
        try {
            val fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val oos = ObjectOutputStream(fos)
            oos.writeObject(tasks)
            oos.close()
        } catch (e: IOException) { e.printStackTrace() }
    }

    fun readData(context: Context) = try {
        val fis = context.openFileInput(fileName)
        val ois = ObjectInputStream(fis)
        ois.readObject() as ArrayList<String>
    } catch (e: Exception) { ArrayList<String>() }
}