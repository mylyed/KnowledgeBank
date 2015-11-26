package whimsicalgl.knowledgebank.db.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import whimsicalgl.knowledgebank.db.DataBaseHelper;
import whimsicalgl.knowledgebank.db.dao.TopicDAO;
import whimsicalgl.knowledgebank.model.Section;
import whimsicalgl.knowledgebank.model.Topic;
import whimsicalgl.knowledgebank.model.TopicJudge;
import whimsicalgl.knowledgebank.model.TopicRadio;

/**
 * Created by lilei on 2015/11/14.
 */
public class TopicDAOImpl implements TopicDAO {
    DataBaseHelper dataBaseHelper;
    Context context;

    public TopicDAOImpl(Context context) {
        this.context = context;
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    @Override
    public List<Topic> getTopics(Section s, Topic.TYPE type) {
        List<Topic> topics = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        String sql = "SELECT " +
                "t_topic.id, " +
                "t_topic.type, " +
                "t_topic.content, " +
                "t_topic.answer, " +
                "t_topic.section_id " +
                "FROM " +
                "t_topic " +
                "WHERE " +
                "t_topic.section_id = ?  AND " +
                "t_topic.type = ? ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{s.getId(), Integer.valueOf(type.getValue()).toString()});
        while (cursor.moveToNext()) {
            Topic topic = null;
            String id = cursor.getString(cursor.getColumnIndex("id")).trim();
            String content = cursor.getString(cursor.getColumnIndex("content")).trim();
            String answer = cursor.getString(cursor.getColumnIndex("answer")).trim();
            // 设置答案
            switch (type) {
                case RADIO:
                    topic = new TopicRadio();
                    topic.setAnswer(answer);
                    topic.setOptions(getOptions(sqLiteDatabase, id));
                    break;
                case MULTISELECT:
                    topic = new TopicRadio();
                {
                    int length = answer.length();
                    Set<String> asw = new HashSet<>(length);
                    for (int i = 0; i < length; i++) {
                        asw.add(answer.substring(i, i + 1).toUpperCase());
                    }
                    topic.setAnswer(asw);
                    topic.setOptions(getOptions(sqLiteDatabase, id));
                }
                break;
                case JUDGE:
                    topic = new TopicRadio();
                    topic.setAnswer(new Boolean(answer.substring(0, 1).equals("1")));
                    topic.setOptions(TopicJudge.YES_NO);
                    break;
            }
            topic.setId(id);
            topic.setContent(content);
            topic.setType(type.getValue());
            topics.add(topic);
        }
        cursor.close();
        sqLiteDatabase.close();
        dataBaseHelper.close();
        return topics;
    }

    @Override
    public Topic getTopic(String topicID) {
        {
            Topic topic = null;
            SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
            String sql = "SELECT " +
                    "t_topic.id, " +
                    "t_topic.type, " +
                    "t_topic.content, " +
                    "t_topic.answer, " +
                    "t_topic.section_id " +
                    "FROM " +
                    "t_topic " +
                    "WHERE " +
                    "t_topic.id = ?";
            Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{topicID});
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("id")).trim();
                String content = cursor.getString(cursor.getColumnIndex("content")).trim();
                String answer = cursor.getString(cursor.getColumnIndex("answer")).trim();
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                Topic.TYPE t = Topic.TYPE.get(type);
                // 设置答案
                switch (t) {
                    case RADIO:
                        topic = new TopicRadio();
                        topic.setAnswer(answer);
                        topic.setOptions(getOptions(sqLiteDatabase, id));
                        break;
                    case MULTISELECT:
                        topic = new TopicRadio();
                    {
                        int length = answer.length();
                        Set<String> asw = new HashSet<>(length);
                        for (int i = 0; i < length; i++) {
                            asw.add(answer.substring(i, i + 1).toUpperCase());
                        }
                        topic.setAnswer(asw);
                        topic.setOptions(getOptions(sqLiteDatabase, id));
                    }
                    break;
                    case JUDGE:
                        topic = new TopicRadio();
                        topic.setAnswer(new Boolean(answer.substring(0, 1).equals("1")));
                        topic.setOptions(TopicJudge.YES_NO);
                        break;
                }
                topic.setId(id);
                topic.setContent(content);
                topic.setType(type);
            }
            cursor.close();
            sqLiteDatabase.close();
            dataBaseHelper.close();
            return topic;
        }
    }

    @Override
    public boolean collection(Topic topic) {
        String sql = "INSERT INTO 't_collection' ('topoc_id', 'collection_time') VALUES (?, ?)";
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{topic.getId(), new Date().toString()});
        sqLiteDatabase.close();
        dataBaseHelper.close();
        return true;
    }

    private List<String> getOptions(SQLiteDatabase sqLiteDatabase, String topic_id) {
        List<String> topics = new ArrayList<>();
        String sql = "SELECT " +
                "t_select_options.id, " +
                "t_select_options.options_value, " +
                "t_select_options.options_key, " +
                "t_select_options.topc_id " +
                "FROM " +
                "t_select_options " +
                "WHERE " +
                "t_select_options.topc_id = ? ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{topic_id});
        while (cursor.moveToNext()) {
            String options_value = cursor.getString(cursor.getColumnIndex("options_value"));
            String options_key = cursor.getString(cursor.getColumnIndex("options_key"));
            topics.add(new StringBuilder(options_key).append(".").append(options_value).toString());
        }
        cursor.close();
        return topics;

    }
}
