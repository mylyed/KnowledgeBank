package whimsicalgl.knowledgebank.model;

import java.io.Serializable;

/**
 * 章节
 */
public class Section implements Serializable {

    private String id;
    private String section_name;
    private String count_radio;
    private String count_judge;
    private String count_multi;

    public Section() {
    }

    public Section(String id, String section_name, String count_radio, String count_judge, String count_multi) {
        this.id = id;
        this.section_name = section_name;
        this.count_radio = count_radio;
        this.count_judge = count_judge;
        this.count_multi = count_multi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getCount_radio() {
        return count_radio;
    }

    public void setCount_radio(String count_radio) {
        this.count_radio = count_radio;
    }

    public String getCount_judge() {
        return count_judge;
    }

    public void setCount_judge(String count_judge) {
        this.count_judge = count_judge;
    }

    public String getCount_multi() {
        return count_multi;
    }

    public void setCount_multi(String count_multi) {
        this.count_multi = count_multi;
    }


    @Override
    public String toString() {
        return "Section{" +
                "id='" + id + '\'' +
                ", section_name='" + section_name + '\'' +
                ", count_radio='" + count_radio + '\'' +
                ", count_judge='" + count_judge + '\'' +
                ", count_multi='" + count_multi + '\'' +
                '}';
    }
}
