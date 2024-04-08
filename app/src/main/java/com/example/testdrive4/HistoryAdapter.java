package com.example.testdrive4;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class HistoryAdapter extends ArrayAdapter<ArrayList<String>> {

    private ArrayList<ArrayList<String>> historyList;
    private Context mContext;

    public HistoryAdapter(Context context, ArrayList<ArrayList<String>> historyList) {
        super(context, 0, historyList);
        this.mContext = context;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public ArrayList<String> getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void delete(int position){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Saves", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //Toast.makeText(.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
        editor.remove(String.valueOf(position+1));
        editor.apply();
//        Map<String, ?> allEntries = sharedPreferences.getAll();
//        int sizeKeys = allEntries.size();
//        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//            Object value = entry.getValue();
//            if(entry.getValue().toString().equals("fd")){
//                editor.remove(entry.getKey());
//                editor.apply();
//            }
//        }
        historyList.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.layout_history, parent, false);
        }
        ArrayList<String> currentItem = historyList.get(position);

        listItem.findViewById(R.id.buttonHistoryDelete).setOnClickListener(view -> delete(position));

        TextView transportType = listItem.findViewById(R.id.historyTransportType);
        TextView transportNumber = listItem.findViewById(R.id.historyTransportNumber);
        TextView time = listItem.findViewById(R.id.historyTime);
        TextView transportFullness = listItem.findViewById(R.id.historyTransportFullness);
        TextView passengersIn = listItem.findViewById(R.id.historyPassengersIn);
        TextView passengersOut = listItem.findViewById(R.id.historyPassengersOut);
        TextView stopName = listItem.findViewById(R.id.historyStopName);
        TextView stopFullness = listItem.findViewById(R.id.historyStopFullness);

        // Заполните текстовые поля данными из currentItem
        transportType.setText(currentItem.get(0));
        transportNumber.setText(currentItem.get(1));
        time.setText(currentItem.get(2));
        transportFullness.setText(currentItem.get(3));
        passengersIn.setText(currentItem.get(4));
        passengersOut.setText(currentItem.get(5));
        stopName.setText(currentItem.get(6));
        stopFullness.setText(currentItem.get(7));

        return listItem;
    }
}
