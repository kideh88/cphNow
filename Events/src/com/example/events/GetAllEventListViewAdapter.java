package com.example.events;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class GetAllEventListViewAdapter extends BaseExpandableListAdapter {

	JSONArray dataArray;
    private Context context;

	public GetAllEventListViewAdapter(JSONArray dataArray , Context context)
	{
		this.dataArray = dataArray;
		this.context = context;
	}


    @Override
    public int getGroupCount() {
        return dataArray.length();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ListCell cell;

        if(convertView == null)
        {
            LayoutInflater inflator = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.event_list_group, null);

            cell = new ListCell();

            cell.eventName = (TextView) convertView.findViewById(R.id.event_name);
            cell.eventDescription = (TextView) convertView.findViewById(R.id.event_description);

            convertView.setTag(cell);
        }

        else
        {
            cell = (ListCell) convertView.getTag();
        }

        // Changing data of cell
        try{
            JSONObject jobject = dataArray.getJSONObject(groupPosition);

            cell.eventName.setText(jobject.getString("strEventName"));
            cell.eventDescription.setText(jobject.getString("strEventDescription"));


        }

        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ListChild child;

        if(convertView == null)
        {
            LayoutInflater inflator = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.event_list_item, null);

            child = new ListChild();

            child.child1 = (TextView) convertView.findViewById(R.id.child1);
            child.child2 = (TextView) convertView.findViewById(R.id.child2);
            child.child3 = (TextView) convertView.findViewById(R.id.child3);

            convertView.setTag(child);
        }
        else
        {
            child = (ListChild) convertView.getTag();
        }

        // Changing data of cell
        try{
            JSONObject jobject = dataArray.getJSONObject(groupPosition);

            child.child1.setText(jobject.getString("intEventTime"));
            child.child2.setText(jobject.getString("intTimePosted"));
            child.child3.setText(jobject.getString("strUsername"));


        }

        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    private class ListCell
	{
		TextView eventName;
		TextView eventDescription;
	}

    private class ListChild {
        TextView child1;
        TextView child2;
        TextView child3;
    }

}
