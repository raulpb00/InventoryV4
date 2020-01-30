package es.raulprieto.inventory.ui.base;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.inputmethod.InputMethodManager;

import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import java.util.Random;

import es.raulprieto.inventory.InventoryApplication;
import es.raulprieto.inventory.R;

public class BaseFragment extends Fragment {

    protected void hiddeKeyboard() {
        try {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ignored) {

        }
    }

    protected void BuildNotification(Boolean autocancel, Boolean showWhen, int largeIcon, String title, String content, PendingIntent pendingIntent) {

        Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), largeIcon);

        Notification.Builder builder = new Notification.Builder(getActivity(), InventoryApplication.CHANNEL_ID)
                .setAutoCancel(autocancel)
                .setShowWhen(showWhen)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(bitmap)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent);

       /* inboxStyle.setBigContentTitle("Inventoryaso");
        inboxStyle.addLine("hmmm");
        builder.setStyle(inboxStyle);*/

        // When I press on a notification it should be opened the
        // DepencencyActivity Activity -> which in some way will determine that
        // DependencyManageFragment should be started.


//        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
        Random random = new Random();
        int id = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(id, builder.build());
    }

}
