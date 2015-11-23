package whimsicalgl.knowledgebank.ui.fragment;

import android.graphics.Color;
import android.widget.CheckBox;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import whimsicalgl.knowledgebank.db.dao.DaoFactory;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * 多选
 */
public class MultiSelectFragment extends SelectFragment {
    public void setOnCheckedChangeListener() {
    }


    public MultiSelectFragment(Section section) {
        super(section);
    }

    @Override
    public List getTopics() {
        return DaoFactory.getInstance().getTopicDAO().getTopics(section, Topic.TYPE.MULTISELECT);
    }

    @Override
    public void lookAnswer() {
        clearCheck();
        Set<String> answers = (Set) currentTopc.getAnswer();
        for (String s : answers) {
            int i = s.compareToIgnoreCase("A");
            CheckBox checkBox = (CheckBox) radioGroup.findViewWithTag(i);
            checkBox.setChecked(true);
            checkBox.setTextColor(Color.RED);
        }
    }

    private boolean isRight() {
        Set<String> answers = (Set) currentTopc.getAnswer();


        Set<String> myAnswers = new HashSet<>();

        for (int i = 0; i < currentTopc.getOptions().size(); i++) {
            CheckBox checkBox = (CheckBox) radioGroup.findViewById(i);
            if (checkBox.isChecked()) {
                myAnswers.add(String.valueOf((char) ('A' + i)));
            }
        }
        return answers.equals(myAnswers);
    }


    @Override
    public void next() {

        if (!isRight()) {
            showMessage("答案错误！");
            return;
        }
        showMessage("答案正确！");
        super.next();
    }
}
