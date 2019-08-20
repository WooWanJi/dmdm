package com.example.dmdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public DatabaseAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }
    public class DatabaseViewHolder extends RecyclerView.ViewHolder{

        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new DatabaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        if(!mCursor.move(position)){
            return;
        }
        //long p_id = mCursor.getLong(mCursor.getColumnIndex(Data))
       // String name = mCursor.getString(mCursor.getColumnIndex(DatabaseCon))
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }
        mCursor= newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }

}
