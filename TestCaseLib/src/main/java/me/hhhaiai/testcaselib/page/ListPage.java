package me.hhhaiai.testcaselib.page;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.hhhaiai.testcaselib.R;
import me.hhhaiai.testcaselib.defcase.ETestCase;
import me.hhhaiai.testcaselib.defcase.ETestSuite;
import me.hhhaiai.testcaselib.utils.L;


/**
 * @Copyright © 2021 analsys Inc. All rights reserved.
 * @Description: case展示页面
 * @Version: 1.0
 * @Create: 2021/03/62 17:36:09
 * @author: sanbo
 */
public class ListPage extends Activity {
    ExpandableListView listView;
    List<ETestSuite> allSuites;
    public static Activity mContext = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);
        mContext = this;

        try {
            setTitle(getString(R.string.page_title));
            listView = findViewById(R.id.list);
            allSuites = ECaseHolder.getInstance().getAllSuites();
            ExpandableListAdapter adapter = new MyAdapter();
            listView.setAdapter(adapter);
        } catch (Throwable e) {
            L.e(e);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    private class MyAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return allSuites.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return allSuites.get(groupPosition).getAllCasesList().size();
        }

        @Override
        public ETestSuite getGroup(int groupPosition) {
            return allSuites.get(groupPosition);
        }

        @Override
        public ETestCase getChild(int groupPosition, int childPosition) {
            return allSuites.get(groupPosition).getAllCasesList().get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View parentView = View.inflate(parent.getContext(), R.layout.parent_layout, null);
            TextView t = parentView.findViewById(R.id.text);
            t.setText(getGroup(groupPosition).getName());

            ImageView indicator = parentView.findViewById(R.id.indicator);
            if (isExpanded) {
                indicator.setImageResource(R.drawable.arrow_down);
            } else {
                indicator.setImageResource(R.drawable.arrow_up);
            }
            return parentView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final ETestCase child = getChild(groupPosition, childPosition);

            final View childView = View.inflate(parent.getContext(), R.layout.child_layout, null);
            final TextView title = childView.findViewById(R.id.tvLabel);
            title.setText(child.getName());

            Button test = childView.findViewById(R.id.btnPrepare);
            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        new Thread(() -> {
                            child.prepare();
                        }).start();
                    } catch (Throwable e) {
                        L.e(e);
                    }
                }
            });

            Button validate = childView.findViewById(R.id.btnValidate);
            validate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        boolean validate = child.validate();
                        final int color = v.getContext().getResources().getColor(validate ?
                                android.R.color.holo_green_light : android.R.color.holo_red_light);
                        childView.setBackgroundColor(color);
                    } catch (Throwable e) {
                        L.e(e);
                    }
                }
            });

            return childView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}