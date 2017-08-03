package kr.co.tjeit.yogiyocopy.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.yogiyocopy.R;
import kr.co.tjeit.yogiyocopy.data.StoreData;

/**
 * Created by tjoeun on 2017-08-02.
 */

public class StoreAdapter extends ArrayAdapter<StoreData>{

    Context mContext;
    List<StoreData> mList;
    LayoutInflater inf;
    List<ImageView> stars = new ArrayList<>();

    public StoreAdapter( Context context, List<StoreData> list) {
        super(context, R.layout.store_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if(row==null)
        {
            row = inf.inflate(R.layout.store_list_item, null);
            // 뷰를 새로 그려주는 작업
            // 재활용 하는 시점에는 들어오지 않는 부분
            // 절대 이 안에서 데이터 세팅 X
        }

        // 데이터를 찍어줄때 해야할 일들

        // 1. 데이터를 표시할 뷰들의 id 부여

        // 2. 표시할 데이터 가져오기
        // 저장된 위치? mList
        // 상황에 맞는 데이터 뽑아오기
        // 1) 뽑아온다? => mList.get
        // 2) 상황에 맞는? => 각 줄의 번호에 맞는 데이터를 가져오기.
        // 3) 뽑아낸 데이터를 변수에 저장. 어떤 변수? 다루는 자료형
        StoreData data = mList.get(position);

        // 3. 데이터를 출력할 뷰를 매핑
        // id를 부여한 객체들을 row.findViewById

        ImageView storeImg = (ImageView) row.findViewById(R.id.storeImg);
        TextView storeNameTxt = (TextView)row.findViewById(R.id.storeNameTxt);
        ImageView star1Img = (ImageView) row.findViewById(R.id.star1Img);
        ImageView star2Img = (ImageView) row.findViewById(R.id.star2Img);
        ImageView star3Img = (ImageView) row.findViewById(R.id.star3Img);
        ImageView star4Img = (ImageView) row.findViewById(R.id.star4Img);
        ImageView star5Img = (ImageView) row.findViewById(R.id.star5Img);
        TextView ratingAvgTxt = (TextView)row.findViewById(R.id.ratingAvgTxt);
        TextView openAndCloseTimeTxt = (TextView)row.findViewById(R.id.openAndCloseTimeTxt);
        TextView minCostTxt = (TextView)row.findViewById(R.id.minCostTxt);
        TextView payMethodTypeTxt = (TextView)row.findViewById(R.id.payMethodTypeTxt);
        ImageView cescoImg = (ImageView)row.findViewById(R.id.cescoImg);


        // 4. 실제 데이터를 뷰에 세팅.
        // set?? 를 해준다. ?? 는 뷰의 종류와 상황에 따라 다르다.
        // 실제 데이터? data (StoreData) 변수
        // data가 가진 메쏘드들 중 게터를 활용하는 경우가 많다.

        Glide.with(mContext).load(data.getImagePath()).into(storeImg);

        storeNameTxt.setText(data.getStoreName());
        ratingAvgTxt.setText(data.getAvgRating()+"점 / " +data.getReviews().size()+"개의 리뷰");


        int openHour = data.getOpenTime()/100;
        int openMinute = data.getOpenTime()%100;
        int closeHour = data.getCloseTime()/100;
        int closeMinute = data.getCloseTime()%100;

        stars.clear();
        stars.add(star1Img);
        stars.add(star2Img);
        stars.add(star3Img);
        stars.add(star4Img);
        stars.add(star5Img);

        for (ImageView iv : stars){
            iv.setImageResource(R.drawable.gray_star);
        }

        int avgRating = (int)data.getAvgRating();
        for(int i = 0; i < avgRating ;i++) {
            stars.get(i).setImageResource(R.drawable.fill_star);
        }

        String openColseStr = String.format("%02d:%02d - %02d:%02d",openHour,openMinute,closeHour,closeMinute);

        openAndCloseTimeTxt.setText(openColseStr);

        String minDeliveryCostStr = String.format("%,d원 이상 배달 가능", data.getMinCost());
        minCostTxt.setText(minDeliveryCostStr);
        if(data.isCesco()){
            cescoImg.setVisibility(View.VISIBLE);
        }
        else{
            cescoImg.setVisibility(View.INVISIBLE);
        }


        return row;
    }
}
