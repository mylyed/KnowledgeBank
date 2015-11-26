package whimsicalgl.knowledgebank.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

import whimsicalgl.knowledgebank.R;
import whimsicalgl.knowledgebank.db.dao.DaoFactory;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.ui.adapter.SectionAdapter;
import whimsicalgl.knowledgebank.ui.popupbox.ChooseTypePopupWindow;

/**
 * 选择试题
 */
public class SectionActivity extends Activity implements AdapterView.OnItemClickListener {
    static String LOG_TAG = "SectionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        initView();
        initParameter();
        setAdapterAndListener();
    }

    private BaseAdapter adapter;

    private void setAdapterAndListener() {
        adapter = new SectionAdapter(this, sectionList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private List<Section> sectionList;

    private void initParameter() {
        sectionList = DaoFactory.getInstance().getSectionDAO().getAllSection();
        //Log.w(LOG_TAG, sectionList.toString());
    }

    private ListView listView;

    private void initView() {
        listView = (ListView) findViewById(R.id.choose_listview);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.w(LOG_TAG, sectionList.get(position).toString());
        //Toast.makeText(this, sectionList.get(position).toString(), Toast.LENGTH_LONG).show();
        new ChooseTypePopupWindow(this, sectionList.get(position));
    }
}
