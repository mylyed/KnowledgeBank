package whimsicalgl.knowledgebank.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import whimsicalgl.knowledgebank.R;

public class MainActivity extends Activity {

    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, SectionActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(runnable, 1000);
    }
}
