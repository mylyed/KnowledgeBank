package whimsicalgl.knowledgebank.ui.fragment;

import android.graphics.Color;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import whimsicalgl.knowledgebank.db.dao.DaoFactory;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * 判断
 */
public class JudgeFragment extends SelectFragment implements RadioGroup.OnCheckedChangeListener {

    @Override
    void setOnCheckedChangeListener() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    public JudgeFragment(Section section) {
        super(section);
    }

    @Override
    public List getTopics() {
        return DaoFactory.getInstance().getTopicDAO().getTopics(section, Topic.TYPE.JUDGE);
    }

    @Override
    public void lookAnswer() {
        canMark = false;

        clearCheck();

        Boolean answer = (Boolean) currentTopc.getAnswer();
        RadioButton radioButton;
        if (answer) {
            radioButton = (RadioButton) radioGroup.findViewWithTag(0);
        } else {
            radioButton = (RadioButton) radioGroup.findViewWithTag(1);
        }
        radioButton.setChecked(true);
        radioButton.setTextColor(Color.RED);
        canMark = true;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (checkedId == -1 || !canMark) {
            return;
        }
        Boolean answer = (Boolean) currentTopc.getAnswer();

        boolean right = checkedId == 0;
        if (answer.equals(right)) {
            showMessage("你做对了!   ✓");
            next();
        } else {
            long[] p = {100, 400};
            vibrator.cancel();
            vibrator.vibrate(p, -1);
            showMessage("你做错了!   ✗");
        }


    }
}
