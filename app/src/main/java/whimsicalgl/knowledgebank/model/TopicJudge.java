package whimsicalgl.knowledgebank.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilei on 2015/11/9.
 * 多选
 */
public class TopicJudge<Boolean> extends Topic {
    public Boolean answer;

    @Override
    public Boolean getAnswer() {
        return answer;
    }


    @Override
    public void setAnswer(Object answer) {
        this.answer = (Boolean) answer;
    }

    public static final List<String> YES_NO = new ArrayList();

    static {
        YES_NO.add("正确");
        YES_NO.add("错误");
    }
}
