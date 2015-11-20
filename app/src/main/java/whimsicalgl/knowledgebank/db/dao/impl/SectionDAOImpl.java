package whimsicalgl.knowledgebank.db.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import whimsicalgl.knowledgebank.db.DataBaseHelper;
import whimsicalgl.knowledgebank.db.dao.SectionDAO;
import whimsicalgl.knowledgebank.model.Section;

/**
 * Created by lilei on 2015/11/11.
 */
public class SectionDAOImpl implements SectionDAO {

    DataBaseHelper dataBaseHelper;
    Context context;

    public SectionDAOImpl(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    @Override
    public List<Section> getAllSection() {
        List<Section> sections = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        String sql = "SELECT *  FROM  t_section ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("section_name"));
            String count_radio = cursor.getString(cursor.getColumnIndex("count_radio"));
            String count_judge = cursor.getString(cursor.getColumnIndex("count_judge"));
            String count_multi = cursor.getString(cursor.getColumnIndex("count_multi"));
            sections.add(new Section(id, name, count_radio, count_judge, count_multi));
        }
        cursor.close();
        sqLiteDatabase.close();
        dataBaseHelper.close();
        return sections;
    }
}
