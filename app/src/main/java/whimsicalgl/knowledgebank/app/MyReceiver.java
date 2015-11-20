package whimsicalgl.knowledgebank.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接受到广播",Toast.LENGTH_LONG).show();
    }
}
