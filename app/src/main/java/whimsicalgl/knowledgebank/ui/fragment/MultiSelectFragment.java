package whimsicalgl.knowledgebank.ui.fragment;

import android.graphics.Color;
import android.widget.CheckBox;

import java.util.List;
import java.util.Set;

import whimsicalgl.knowledgebank.db.dao.DaoFactory;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * 多选
 */
public class MultiSelectFragment extends SelectFragment {
    public MultiSelectFragment(Section section) {
        super(section);
    }

    @Override
    public List getTopics() {
        return DaoFactory.getInstance().getTopicDAO().getTopics(section, Topic.TYPE.MULTISELECT);
    }

    @Override
    public void lookAnswer() {
        radioGroup.clearCheck();
        Set<String> answers = (Set) currentTopc.getAnswer();
        for (String s : answers) {
            int i = s.compareToIgnoreCase("A");
            CheckBox checkBox = (CheckBox) radioGroup.findViewWithTag(i);
            checkBox.setChecked(true);
            checkBox.setTextColor(Color.RED);
        }
    }
}
