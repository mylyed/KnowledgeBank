package whimsicalgl.knowledgebank.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.greenrobot.event.EventBus;
import whimsicalgl.knowledgebank.R;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;
import whimsicalgl.knowledgebank.ui.fragment.JudgeFragment;
import whimsicalgl.knowledgebank.ui.fragment.MultiSelectFragment;
import whimsicalgl.knowledgebank.ui.fragment.RadioFragment;
import whimsicalgl.knowledgebank.ui.fragment.TopicBaseFragment;

/**
 * 做题
 */
public class TopicActivity extends FragmentActivity implements View.OnClickListener {

    static final String LOG_TAG = "TopicActivity";
    Section section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        //------------------
        boolean isCollection = this.getIntent().getBooleanExtra("isCollection", false);
        section = (Section) this.getIntent().getSerializableExtra(Section.class.getCanonicalName());
        Topic.TYPE type = (Topic.TYPE) this.getIntent().getSerializableExtra(Topic.TYPE.class.getCanonicalName());
        //------------------

        Log.i(LOG_TAG, type.getValue() + "");
        initFragment(isCollection, type);
        initView();
        setListener();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    Boolean btnState = false;


    public void onEvent(Boolean btnState) {
        this.btnState = btnState;
       // Toast.makeText(this, "EventBus收到信息", Toast.LENGTH_SHORT).show();
    }


    private void setListener() {
        fallback.setOnClickListener(this);
        next.setOnClickListener(this);
        lookAnswer.setOnClickListener(this);
        collection.setOnClickListener(this);
    }

    private Button fallback;
    private Button next;
    private Button lookAnswer;
    private Button collection;


    private void initView() {
        fallback = (Button) findViewById(R.id.btn_fallback);
        next = (Button) findViewById(R.id.btn_next);
        lookAnswer = (Button) findViewById(R.id.btn_look_answer);
        collection = (Button) findViewById(R.id.btn_collection);
    }

    TopicBaseFragment topicBaseFragment;

    private void initFragment(boolean isC, Topic.TYPE type) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (type) {
            case RADIO:
                topicBaseFragment = new RadioFragment(isC, section);
                break;
            case MULTISELECT:
                topicBaseFragment = new MultiSelectFragment(isC, section);
                break;
            case JUDGE:
                topicBaseFragment = new JudgeFragment(isC, section);
                break;
        }
        fragmentTransaction.replace(R.id.topic_content_section, topicBaseFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (!btnState) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_fallback:
                topicBaseFragment.fallback();
                break;
            case R.id.btn_collection:
                topicBaseFragment.collection();
                break;
            case R.id.btn_next:
                topicBaseFragment.next();
                break;
            case R.id.btn_look_answer:
                topicBaseFragment.lookAnswer();
                break;
        }

    }
}
