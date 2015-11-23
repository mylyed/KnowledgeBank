package whimsicalgl.knowledgebank.ui.fragment;

import android.graphics.Color;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import whimsicalgl.knowledgebank.db.dao.DaoFactory;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * 单选
 */
public class RadioFragment extends SelectFragment implements RadioGroup.OnCheckedChangeListener {

    public RadioFragment(Section section) {
        super(section);
    }

    public List getTopics() {
        return DaoFactory.getInstance().getTopicDAO().getTopics(section, Topic.TYPE.RADIO);
    }


    public void setOnCheckedChangeListener() {
        radioGroup.setOnCheckedChangeListener(this);
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == -1 || !canMark) {
            return;
        }
        String answer = (String) currentTopc.getAnswer();
        int i = answer.compareToIgnoreCase("A");

        boolean right = (i == checkedId);
        if (right) {
            showMessage("你做对了!   ✓");
        } else {
            long[] p = {100, 400};
            vibrator.cancel();
            vibrator.vibrate(p, -1);
            showMessage("你做错了!   ✗");
        }

    }
}
