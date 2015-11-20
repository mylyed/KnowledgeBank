package whimsicalgl.knowledgebank.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import whimsicalgl.knowledgebank.R;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * 选择 单选或者多选 判断-->有两个选择 真确还是错误
 */
public abstract class SelectFragment extends TopicBaseFragment {
    private static final String LOG_TAG = SelectFragment.class.getCanonicalName();

    public SelectFragment() {
        super();
    }

    protected Section section;

    public SelectFragment(Section section) {
        this.section = section;
    }


    private List<Topic> topics;

    protected Topic currentTopc;


    private int currentTopicIndex = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_select, container, false);
        //获取上次做到第几题
        initView();
        new GetDataTask().execute();
        return view;
    }

    //获取题
    public abstract List getTopics();

    private class GetDataTask extends AsyncTask<Void, Void, List> {

        @Override
        protected List doInBackground(Void[] params) {
            return getTopics();
        }

        @Override
        protected void onPostExecute(List o) {
            topics = o;
            initParameter();
        }
    }


    private View view;
    //题目
    private TextView textView;
    //选项
    protected RadioGroup radioGroup;

    private void initView() {
        textView = (TextView) view.findViewById(R.id.select_content);
        radioGroup = (RadioGroup) view.findViewById(R.id.select_group);
    }

    private void initParameter() {
        radioGroup.clearCheck();
        radioGroup.removeAllViews();
        try {
            currentTopc = topics.get(currentTopicIndex);
            String text = new StringBuilder(Integer.toString(currentTopicIndex + 1)).append(".").append(currentTopc.getContent()).toString();
            textView.setText(text);
            int count = currentTopc.getOptions().size();
            for (int i = 0; i < count; i++) {
                if (currentTopc.getType() == Topic.TYPE.MULTISELECT.getValue()) {
                    //多选
                    CheckBox checkBox = new CheckBox(getActivity());
                    checkBox.setText(currentTopc.getOptions().get(i).toString());
                    checkBox.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    checkBox.setTag(i);
                    radioGroup.addView(checkBox);
                } else {
                    //单选或者判断
                    RadioButton radioButton = new RadioButton(getActivity());
                    radioButton.setText(currentTopc.getOptions().get(i).toString());
                    radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    radioButton.setTag(i);
                    radioGroup.addView(radioButton);
                }
            }

            lookAnswer();
        } catch (Exception e) {
            textView.setText("程序出错了！");
            Log.e(LOG_TAG, e.getMessage());
        }
    }


    @Override
    public void fallback() {
        if (currentTopicIndex <= 0) {
            currentTopicIndex = topics.size() - 1;
        } else {
            --currentTopicIndex;
        }
        initParameter();
    }

    @Override
    public void next() {
        if (currentTopicIndex >= topics.size() - 1) {
            currentTopicIndex = 0;
        } else {
            ++currentTopicIndex;
        }
        initParameter();
    }

    //收藏该题
    @Override
    public void collection() {

    }
}