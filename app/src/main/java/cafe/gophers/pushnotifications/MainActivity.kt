package cafe.gophers.pushnotifications

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.text.TextUtils
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val channelID = "Some channel ID"

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channelName = "E-mail"
            val channelDescription = "Information about e-mail"
            val channel = NotificationChannel(channelID, channelName, importance).apply {
                description = channelDescription
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        sendNotification()

        notify_button.setOnClickListener {
            sendNotification()
        }
    }

    private fun sendNotification() {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


        // Note: if Android API level is less than 26, the channelID will be ignored
        val notificationBuilder = NotificationCompat.Builder(this, channelID)
            .setContentTitle("Hello Notification! \uD83D\uDC7B")
            .setContentText("This is the notification text \uD83D\uDC4F")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            /* Added */
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
    }
}
