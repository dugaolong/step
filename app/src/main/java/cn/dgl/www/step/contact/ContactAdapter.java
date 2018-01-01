package cn.dgl.www.step.contact;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.dgl.www.step.R;


/**
 * Created by dugaolong on 16/7/17.
 */
public class ContactAdapter extends BaseExpandableListAdapter {

    public Context mContext;
    private HashMap<String,List<String>> map;
    //设置组视图的显示文字
    private List<String> generalsTypes =new ArrayList<String>();

    public ContactAdapter(Context context,
                          HashMap<String,List<String>> map, List<String> generalsTypes ) {
        this.mContext= context;
        this.generalsTypes = generalsTypes;
        this.map = map;
    }

    //自己定义一个获得文字信息的方法
    TextView getTextView() {
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, 64);
        TextView textView = new TextView(
                mContext);
        textView.setLayoutParams(lp);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(36, 0, 0, 0);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        return textView;
    }


    //重写ExpandableListAdapter中的各个方法
    public int getGroupCount() {
        return generalsTypes.size();
    }

    public Object getGroup(int groupPosition) {
        return generalsTypes.get(groupPosition);
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public int getChildrenCount(int groupPosition) {
        generalsTypes.get(groupPosition);
        return map.get(generalsTypes.get(groupPosition)).size();
    }

    public Object getChild(int groupPosition, int childPosition) {
        return map.get(generalsTypes.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public boolean hasStableIds() {
        return true;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_group_item, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_group_item);
        textView.setTextColor(Color.BLACK);
        textView.setText(getGroup(groupPosition).toString());

        return view;
    }

    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_child_item,null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120);
        view.setLayoutParams(layoutParams);
        TextView textView = (TextView) view.findViewById(R.id.tv_child_item);
        String cityName = (String) getChild(groupPosition, childPosition);
        textView.setTextColor(Color.BLACK);
        textView.setText(cityName);
        return view;
    }

    public boolean isChildSelectable(int groupPosition,
                                     int childPosition) {
        return true;
    }

    public int getPositionForSelection(int selection) {
        for (int i = 0; i < generalsTypes.size(); i++) {
            char first = generalsTypes.get(i).toUpperCase().charAt(0);
            if (first == selection) {
                return i;
            }
        }
        return -1;

    }
}
