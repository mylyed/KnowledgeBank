package whimsicalgl.knowledgebank.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RadioButton;

import whimsicalgl.knowledgebank.R;
import whimsicalgl.knowledgebank.ui.fragment.CollectionFragment;
import whimsicalgl.knowledgebank.ui.fragment.SectionFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initFragment();
    }

    private void initFragment() {
        btn_do.setChecked(true);
        fragment = new SectionFragment();
        changeFragment();
    }

    private void initListener() {
        btn_do.setOnClickListener(this);
        btn_collection.setOnClickListener(this);
    }


    RadioButton btn_do;
    RadioButton btn_collection;

    private void initView() {
        btn_collection = (RadioButton) findViewById(R.id.btn_collection);
        btn_do = (RadioButton) findViewById(R.id.btn_do);
    }

    Fragment fragment;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_do:
                fragment = new SectionFragment();
                break;
            case R.id.btn_collection:
                fragment = new CollectionFragment();
                break;
        }
        changeFragment();
    }

    private void changeFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment, fragment);
        fragmentTransaction.commit();
    }
}
