package com.amr.gharseldin.gridviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private GridView gridView;
    private TextView textView;

    private static final String[][] items={
            {"lorem", "ipsum", "dolor","sit", "amet"},
            {"consectetuer", "adipiscing", "elit", "morbi", "vel"},
            {"ligula", "vitae", "arcu", "aliquet", "mollis"},
            {"etiam", "vel", "erat", "placerat", "ante"},
            {"porttitor", "sodales", "pellentesque", "augue", "purus"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        gridView = (GridView) findViewById(R.id.gridView);

        gridView.setAdapter(new customAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = (TextView)view.findViewById(R.id.bottom_view);
                textView.setText(item.getText());
            }
        });
    }

    private class customAdapter extends ArrayAdapter<String>{

        customAdapter(Context context){
            super(context, R.layout.cell, R.id.top_view, items[1]);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            ViewHolder holder = (ViewHolder)view.getTag();
            if(holder == null){
                holder = new ViewHolder(view);
                view.setTag(holder);
            }

            holder.bottomTextView.setText(position+"");

            return view;
        }

        class ViewHolder {
            TextView topTextView = null;
            TextView bottomTextView = null;

            ViewHolder(View row){
                this.topTextView = (TextView) row.findViewById(R.id.top_view);
                this.bottomTextView = (TextView) row.findViewById(R.id.bottom_view);
            }
        }
    }
}
