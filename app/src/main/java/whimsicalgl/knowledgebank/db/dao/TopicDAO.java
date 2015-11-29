package whimsicalgl.knowledgebank.db.dao;

import java.util.List;

import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;

/**
 * Created by lilei on 2015/11/10.
 */
public interface TopicDAO {

    List<Topic> getTopics(Section s, Topic.TYPE type);

    Topic getTopic(String topicID);

    boolean collection(Topic topic);

    List<Topic> getCollectionTopics(Topic.TYPE judge);
}
