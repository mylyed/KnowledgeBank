package whimsicalgl.knowledgebank.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import whimsicalgl.knowledgebank.R;
import whimsicalgl.knowledgebank.model.Section;

/**
 * Created by lilei on 2015/11/11.
 */
public class SectionAdapter extends BaseAdapter {
    private Context context;
    private List<Section> sectionList;

    public SectionAdapter(Context context, List<Section> sectionList) {
        this.context = context;
        this.sectionList = sectionList;
    }

    @Override
    public int getCount() {
        return sectionList.size();
    }

    @Override
    public Object getItem(int position) {
        return sectionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.choose_item, null);
            holder.button = (TextView) convertView.findViewById(R.id.choose_item_btn);
            //--------------
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Section section = sectionList.get(position);

        holder.button.setText(section.getSection_name());

        return convertView;
    }

    static class ViewHolder {
        TextView button;
    }

}
