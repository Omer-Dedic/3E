import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.a3e.Handler
import java.util.Calendar

class ResetDatabaseWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val currentDate = Calendar.getInstance()
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val month = currentDate.get(Calendar.MONTH) + 1

        if (day == 1 && month == 1) {
            val dbHelper = Handler(applicationContext)
            dbHelper.resetUplataTable()
        }

        return Result.success()
    }
}
