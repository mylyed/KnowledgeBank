package whimsicalgl.knowledgebank.db.dao;

import java.util.List;

import whimsicalgl.knowledgebank.model.Section;

/**
 * Created by lilei on 2015/11/11.
 */
public interface SectionDAO {
    /**
     * 获取所有的章节
     **/
    List<Section> getAllSection();
}
