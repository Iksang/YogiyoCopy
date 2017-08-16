package kr.co.tjeit.yogiyocopy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import kr.co.tjeit.yogiyocopy.R;
import kr.co.tjeit.yogiyocopy.data.MenuData;
import kr.co.tjeit.yogiyocopy.data.StoreData;

/**
 * Created by tjoeun on 2017-08-16.
 */

public class MenuAdapter extends ArrayAdapter<MenuData> {
    private Context mContext;
    private List<MenuData> mList;
    LayoutInflater inf;

    public MenuAdapter(Context context, List<MenuData> list){
        super(context, R.layout.menu_list_item, list);
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row = inf.inflate(R.layout.menu_list_item, null);
        }

        MenuData data = mList.get(position);

        TextView meneNameTxt = (TextView)row.findViewById(R.id.meneNameTxt);
        TextView isSetTxt = (TextView)row.findViewById(R.id.isSetTxt);
        TextView priceTxt = (TextView)row.findViewById(R.id.priceTxt);
        ImageView menuImg = (ImageView)row.findViewById(R.id.menuImg);

        Glide.with(mContext).load(data.getImagePath()).into(menuImg);

        meneNameTxt.setText(data.getMenuName());
        if(data.isSet()){
            isSetTxt.setVisibility(View.VISIBLE);
        }
        else {
            isSetTxt.setVisibility(View.GONE);
        }

        priceTxt.setText(String.format(Locale.KOREA, "%,d원",data.getPrice()));


        return row;
    }


}
