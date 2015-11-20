package whimsicalgl.knowledgebank.ui.fragment;

import android.graphics.Color;
import android.widget.RadioButton;

import java.util.List;

import whimsicalgl.knowledgebank.db.dao.DaoFactory;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * 单选
 */
public class RadioFragment extends SelectFragment {

    public RadioFragment(Section section) {
        super(section);
    }

    public List getTopics() {
        return DaoFactory.getInstance().getTopicDAO().getTopics(section, Topic.TYPE.RADIO);
    }


    @Override
    public void lookAnswer() {
        radioGroup.clearCheck();
        String answer = (String) currentTopc.getAnswer();
        int i = answer.compareToIgnoreCase("A");
        RadioButton radioButton = (RadioButton) radioGroup.findViewWithTag(i);
        radioButton.setChecked(true);
        radioButton.setTextColor(Color.RED);
    }

    @Override
    public void collection() {

    }
}
