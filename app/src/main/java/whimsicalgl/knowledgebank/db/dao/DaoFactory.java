package whimsicalgl.knowledgebank.db.dao;

import android.content.Context;

import whimsicalgl.knowledgebank.db.dao.impl.SectionDAOImpl;
import whimsicalgl.knowledgebank.db.dao.impl.TopicDAOImpl;

/**
 * Created by lilei on 2015/11/11.
 */
public class DaoFactory {
    private static DaoFactory factory = null;
    private Context context;

    public static DaoFactory getInstance() {
        return factory;
    }

    private DaoFactory(Context context) {
        this.context = context;
    }

    public static void initFactory(Context context) {
        factory = new DaoFactory(context);
    }

    private SectionDAO section;

    public SectionDAO getSectionDAO() {
        if (section == null) {
            section = new SectionDAOImpl(context);
        }
        return section;
    }

    TopicDAO topic;

    public TopicDAO getTopicDAO() {
        if (topic == null) {
            topic = new TopicDAOImpl(context);
        }
        return topic;
    }
}
