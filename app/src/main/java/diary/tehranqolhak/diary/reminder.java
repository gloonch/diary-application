package diary.tehranqolhak.diary;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Locale;

public class reminder extends BroadcastReceiver {
    String locale = Locale.getDefault().getDisplayLanguage();

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (locale) {
            case "English":
                showNotification(context, "How was your day ?",
                        "Write it down ...", "Reminder", "reminder");
                break;
            case "Deutsch":
                showNotification(context, "Wie war dein Tag ?",
                        "Schreib es auf ...", "Reminder", "reminder");
                break;
            case "español":
                showNotification(context, "Que tal tu día ?",
                        "Escríbelo ...", "Reminder", "reminder");
                break;
            case "français":
                showNotification(context, "Comment était ta journée ?",
                        "Écris le ...", "Reminder", "reminder");
                break;
            case "italiano":
                showNotification(context, "Com'è stata la tua giornata ?",
                        "Scrivilo ...", "Reminder", "reminder");
                break;
            case "português":
                showNotification(context, "Como foi o seu dia ?",
                        "Anotá-la ...", "Reminder", "reminder");
                break;
        }

    }

    public void showNotification(Context context, String title, String text, String channel_name, String channel_id) {

        Context mContext = context.getApplicationContext();

        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(channel_id, channel_name, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.notify)
                .setContentTitle(title)
                .setContentText(text);
        Intent open = new Intent(context, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(open);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        stackBuilder.addNextIntent(new Intent());
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(true);

        notificationManager.notify(13, mBuilder.build());

    }


}
