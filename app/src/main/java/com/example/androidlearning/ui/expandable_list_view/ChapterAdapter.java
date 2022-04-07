package com.example.androidlearning.ui.expandable_list_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidlearning.R;

import java.util.List;

public class ChapterAdapter extends BaseExpandableListAdapter {

    private List<Chapter> mDatas;
    private LayoutInflater layoutInflater;

    public ChapterAdapter(Context context, List<Chapter> mDatas) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDatas.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mDatas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDatas.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 是否有稳定的ID，如有稳定的id对性能可能有帮助
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentViewHolder = null;
        if (convertView == null) {
//            convertView = layoutInflater.inflate(R.layout.item_parent_chapter, null, false);如果设置为null，则当前item的根布局的属性失效
            convertView = layoutInflater.inflate(R.layout.item_parent_chapter, parent, false);
            parentViewHolder = new ParentViewHolder();
            parentViewHolder.mTvName = convertView.findViewById(R.id.tv_name);
            parentViewHolder.mIvIndicator = convertView.findViewById(R.id.iv_indicator);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }
        Chapter chapter = mDatas.get(groupPosition);
        parentViewHolder.mTvName.setText(chapter.getName());
        parentViewHolder.mIvIndicator.setSelected(isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_child_chapter, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.mTvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        ChapterItem chapterItem = mDatas.get(groupPosition).getChildren().get(childPosition);
        childViewHolder.mTvName.setText(chapterItem.getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public static class ParentViewHolder {
        TextView mTvName;
        ImageView mIvIndicator;
    }

    public static class ChildViewHolder {
        TextView mTvName;
    }
}
