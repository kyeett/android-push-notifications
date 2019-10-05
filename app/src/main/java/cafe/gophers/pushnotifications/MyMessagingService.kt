package cafe.gophers.pushnotifications

import com.google.firebase.messaging.FirebaseMessagingService
import android.util.Log
import com.google.firebase.messaging.RemoteMessage

class MyMessagingService : FirebaseMessagingService() {

    val TAG = "Firebase Messaging Svc"

    override fun onNewToken(token: String?) {
        Log.d(TAG, "Refreshed token: " + token!!)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Not needed yet
    }
}
