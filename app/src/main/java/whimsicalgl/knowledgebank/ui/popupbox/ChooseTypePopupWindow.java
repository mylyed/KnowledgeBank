package whimsicalgl.knowledgebank.ui.popupbox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import whimsicalgl.knowledgebank.R;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;
import whimsicalgl.knowledgebank.ui.activity.TopicActivity;

/**
 * 题型选择对话框
 */
public class ChooseTypePopupWindow  implements View.OnClickListener {
    Context context;
    View view;
    Section section;
    AlertDialog alertDialog;
    public ChooseTypePopupWindow(Context context, Section section) {
        this.context = context;
        this.section = section;
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        view = LayoutInflater.from(context).inflate(R.layout.choose_type, null);
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        initView(view);
        initParameter();
        initListener();

    }


    private Button danxuan;
    private Button duoxuan;
    private Button panduan;

    private void initView(View view) {
        danxuan = (Button) view.findViewById(R.id.danxuan);
        duoxuan = (Button) view.findViewById(R.id.duoxuan);
        panduan = (Button) view.findViewById(R.id.panduan);
    }

    private void initParameter() {
        danxuan.setText("单选：" + section.getCount_radio() + "道");
        duoxuan.setText("多选：" + section.getCount_multi() + "道");
        panduan.setText("判断：" + section.getCount_judge() + "道");
    }

    private void initListener() {
        view.setOnClickListener(this);
        danxuan.setOnClickListener(this);
        duoxuan.setOnClickListener(this);
        panduan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.danxuan:
                startActivity(Topic.TYPE.RADIO);
                break;
            case R.id.duoxuan:
                startActivity(Topic.TYPE.MULTISELECT);
                break;
            case R.id.panduan:
                startActivity(Topic.TYPE.JUDGE);
                break;
        }
        alertDialog.dismiss();
    }

    private void startActivity(Topic.TYPE type) {
        Intent intent = new Intent(context, TopicActivity.class);
        intent.putExtra(Section.class.getCanonicalName(), section);
        intent.putExtra(Topic.TYPE.class.getCanonicalName(), type);
        context.startActivity(intent);
    }
}
