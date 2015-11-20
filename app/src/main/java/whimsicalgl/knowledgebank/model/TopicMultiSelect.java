package whimsicalgl.knowledgebank.model;

import java.util.Set;

/**
 * Created by lilei on 2015/11/9.
 */
public class TopicMultiSelect<Set> extends Topic {
    private Set answer;

    public Set getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(Object answer) {
        this.answer = (Set) answer;
    }

}
