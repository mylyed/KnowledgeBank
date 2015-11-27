package whimsicalgl.knowledgebank.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import whimsicalgl.knowledgebank.R;

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

    }
}
