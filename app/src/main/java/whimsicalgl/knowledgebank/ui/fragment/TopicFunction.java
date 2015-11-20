package whimsicalgl.knowledgebank.ui.fragment;

/**
 * Created by lilei on 2015/11/10.
 */
public interface TopicFunction  {
    /**
     * 上一题
     **/
    void fallback();

    /**
     * 下一题
     */
    void next();

    /**
     * 查看答案
     */
    void lookAnswer();

    /**
     * 收藏
     */
    void collection();

}
