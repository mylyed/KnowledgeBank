package whimsicalgl.knowledgebank.model;

import java.io.Serializable;
import java.util.List;

/**
 * 题目基类
 */
public abstract class Topic<E> implements Serializable {
    /*判断答案是否正确*/
    public abstract E getAnswer();

    /**
     * 设置答案
     */
    public abstract void setAnswer(Object answe);

    private String id;

    /**
     * 题目
     **/
    private String content;
    /**
     * 题目类型
     **/
    private int type;

    //答案选项
    private List<String> options;


    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static enum TYPE implements Serializable {
        /*单选*/
        RADIO(1),
        /*多选*/
        MULTISELECT(2),
        /*判断*/
        JUDGE(3);
        private int value;

        TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static TYPE get(int v) {

            return v == 1 ? RADIO : (v == 2 ? MULTISELECT : JUDGE);
        }

    }
}
