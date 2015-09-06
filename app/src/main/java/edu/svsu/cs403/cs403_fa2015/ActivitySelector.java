package edu.svsu.cs403.cs403_fa2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySelector extends AppCompatActivity {

    ExpandableListView elvPeople;
    ChaptersListAdapter elaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_selector);
        setupChaptersListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_selector, menu);
        return true;
    }

    private void setupChaptersListView() {
        elvPeople = (ExpandableListView)findViewById(R.id.elvPeople);
        elaAdapter = new ChaptersListAdapter();
        elvPeople.setAdapter(elaAdapter);
        elvPeople.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String exerciseTitle =  (String)elaAdapter.getChild(groupPosition, childPosition);
                Class<? extends Activity> exerciseClass = elaAdapter.getExerciseClass(groupPosition, childPosition, id);
                if (exerciseClass != null) {
                    Toast.makeText(ActivitySelector.this, exerciseTitle, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ActivitySelector.this, exerciseClass));
                } else {
                    Toast.makeText(ActivitySelector.this, "Exercise Not Available", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }

    private class ChaptersListAdapter extends BaseExpandableListAdapter {
        private String[] chapters = getResources().getStringArray(R.array.chapters);
        private String[][] exercises;

        public ChaptersListAdapter() {
            super();
            exercises = new String[chapters.length][];
            for (int i=0; i < exercises.length; i++) {
                int resId = getResources().getIdentifier("chap" + (i+1), "array", getPackageName());
                exercises[i] = getResources().getStringArray(resId);
            }
        }


        public Object getChild(int groupPosition, int childPosition) {
            return exercises[groupPosition][childPosition];
        }

        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        public int getChildrenCount(int groupPosition) {
            return exercises[groupPosition].length;
        }

        public TextView getGenericView() {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            TextView textView = new TextView(ActivitySelector.this);
            textView.setLayoutParams(lp);
            // Center the text vertically
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            textView.setPadding(60, 20, 20, 20);
            return textView;
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                                 View convertView, ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setPadding(80, 20, 20, 20);
            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
        }

        public Object getGroup(int groupPosition) {
            //return "Chapter " + (groupPosition + 1) + ": " + chapters[groupPosition];
            return chapters[groupPosition];
        }

        public int getGroupCount() {
            return chapters.length;
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                 ViewGroup parent) {
            TextView textView = getGenericView();
            textView.setText(getGroup(groupPosition).toString());
            return textView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        public boolean hasStableIds() {
            return true;
        }

        public Class<? extends Activity> getExerciseClass(int groupPosition, int childPosition, long id) {
            String exerciseId = "chap" + (groupPosition + 1) + "ex" + (childPosition + 1);
            return ExerciseActivityMapper.getExerciseClass(exerciseId);
        }
    }

}
