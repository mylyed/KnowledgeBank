package whimsicalgl.knowledgebank.ui.fragment;

import android.graphics.Color;
import android.widget.RadioButton;

import java.util.List;

import whimsicalgl.knowledgebank.db.dao.DaoFactory;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * 判断
 */
public class JudgeFragment extends SelectFragment {

    public JudgeFragment(Section section) {
        super(section);
    }

    @Override
    public List getTopics() {
        return DaoFactory.getInstance().getTopicDAO().getTopics(section, Topic.TYPE.JUDGE);
    }

    @Override
    public void lookAnswer() {
        radioGroup.clearCheck();
        Boolean answer = (Boolean) currentTopc.getAnswer();
        RadioButton radioButton;
        if (answer) {
            radioButton = (RadioButton) radioGroup.findViewWithTag(0);
        } else {
            radioButton = (RadioButton) radioGroup.findViewWithTag(1);
        }
        radioButton.setChecked(true);
        radioButton.setTextColor(Color.RED);
    }
}
