package whimsicalgl.knowledgebank.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import whimsicalgl.knowledgebank.R;
import whimsicalgl.knowledgebank.model.Topic;
import whimsicalgl.knowledgebank.ui.activity.TopicActivity;

/**
 * Created by lilei on 2015/11/27.
 */
public class CollectionFragment extends Fragment implements View.OnClickListener {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collection, container, false);
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        bt_radio.setOnClickListener(this);
        bt_multi.setOnClickListener(this);
        bt_judge.setOnClickListener(this);
    }

    Button bt_radio;
    Button bt_multi;
    Button bt_judge;

    private void initView() {
        bt_radio = (Button) view.findViewById(R.id.collection_radio);
        bt_multi = (Button) view.findViewById(R.id.collection_multi);
        bt_judge = (Button) view.findViewById(R.id.collection_judge);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), TopicActivity.class);
        // intent.putExtra(Section.class.getCanonicalName(), section);
        intent.putExtra("isCollection", true);
        Topic.TYPE type = null;
        switch (v.getId()) {
            case R.id.collection_radio: {
                type = Topic.TYPE.RADIO;
                break;
            }
            case R.id.collection_multi: {
                type = Topic.TYPE.MULTISELECT;
                break;
            }
            case R.id.collection_judge: {
                type = Topic.TYPE.JUDGE;
                break;
            }
        }
        intent.putExtra(Topic.TYPE.class.getCanonicalName(), type);
        startActivity(intent);
    }
}
