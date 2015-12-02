package whimsicalgl.knowledgebank.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
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

import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import whimsicalgl.knowledgebank.R;
import whimsicalgl.knowledgebank.cache.MyCache;
import whimsicalgl.knowledgebank.db.dao.DaoFactory;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * 选择 单选或者多选 判断-->有两个选择 真确还是错误
 */
public abstract class SelectFragment extends TopicBaseFragment {

    private static final String LOG_TAG = SelectFragment.class.getCanonicalName();

    /*给答案区设置点击事件*/
    abstract void setOnCheckedChangeListener();

    public SelectFragment() {
        super();
    }

    protected Section section;
    /*是否是打开收藏的题库*/
    protected boolean isCollection;

    public SelectFragment(boolean isCollection, Section section) {
        this.isCollection = isCollection;
        this.section = section;
    }


    private List<Topic> topics;

    protected Topic currentTopc;


    protected int currentTopicIndex = 0;

    /*震动*/
    protected Vibrator vibrator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (vibrator == null) {
            vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        }
        view = inflater.inflate(R.layout.fragment_select, container, false);
        //获取上次做到第几题
        initView();
        setOnCheckedChangeListener();
//        new GetDataTask().execute();
        Observable.create(new Observable.OnSubscribe<List>() {
            @Override
            public void call(Subscriber<? super List> subscriber) {
                subscriber.onNext(getTopics());
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List>() {
            @Override
            public void call(List list) {
                if (list.size() >= 1) {
                    topics = list;
                    if (!isCollection) {
                        currentTopicIndex = MyCache.getLast(section.getSection_name() + ((Topic) topics.get(0)).getType());
                    }
                    Log.i(LOG_TAG, currentTopicIndex + "");
                    initParameter();
                    EventBus.getDefault().post(new Boolean(true));
                } else {
                    textView.setText("没有题");
                    EventBus.getDefault().post(new Boolean(false));
                }
            }
        });


        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        vibrator.cancel();
        if (topics != null && topics.size() > 0 && !isCollection && section != null)
            MyCache.setLast(section.getSection_name() + ((Topic) topics.get(0)).getType(), currentTopicIndex);

    }

    //获取题
    public abstract List getTopics();
//    private class GetDataTask extends AsyncTask<Void, Void, List> {
//
//        @Override
//        protected List doInBackground(Void[] params) {
//            return getTopics();
//        }
//
//        @Override
//        protected void onPostExecute(List o) {
//            topics = o;
//            currentTopicIndex = MyCache.getLast(section.getSection_name() + ((Topic) topics.get(0)).getType());
//            Log.i(LOG_TAG, currentTopicIndex + "");
//
//            initParameter();
//        }
//    }


    private View view;
    //题目
    private TextView textView;
    //选项
    protected RadioGroup radioGroup;

    private void initView() {
        textView = (TextView) view.findViewById(R.id.select_content);
        radioGroup = (RadioGroup) view.findViewById(R.id.select_group);
    }

    /*是否可以判断答案正确*/
    protected boolean canMark = true;

    protected void clearCheck() {
        canMark = false;
        radioGroup.clearCheck();
        canMark = true;
    }

    private void initParameter() {
        clearCheck();
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
                    checkBox.setId(i);
                    radioGroup.addView(checkBox);
                } else {
                    //单选或者判断
                    RadioButton radioButton = new RadioButton(getActivity());
                    radioButton.setText(currentTopc.getOptions().get(i).toString());
                    radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    radioButton.setTag(i);
                    radioButton.setId(i);
                    radioGroup.addView(radioButton);
                }
            }

            // lookAnswer();
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
        Observable.just(currentTopc).map(new Func1<Topic, Boolean>() {
            @Override
            public Boolean call(Topic topic) {
                return DaoFactory.getInstance().getTopicDAO().collection(topic);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    showMessage("收藏成功！");
                } else {
                    showMessage("收藏失败！");
                }
            }
        });
    }
}
