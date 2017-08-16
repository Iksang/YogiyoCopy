package kr.co.tjeit.yogiyocopy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.tjeit.yogiyocopy.adapter.MenuAdapter;
import kr.co.tjeit.yogiyocopy.adapter.StoreAdapter;
import kr.co.tjeit.yogiyocopy.data.MenuData;
import kr.co.tjeit.yogiyocopy.data.StoreData;

public class ViewStoreInfoActivity extends BaseActivity {

    private android.widget.ImageView storeImg;
    private android.widget.ImageView star1Img;
    private android.widget.ImageView star2Img;
    private android.widget.ImageView star3Img;
    private android.widget.ImageView star4Img;
    private android.widget.ImageView star5Img;
    private android.widget.TextView ratingAvgTxt;
    private android.widget.TextView minCostTxt;
    private TextView storeNameTxt;
    private StoreData mStoreData = null;
    private List<ImageView> stars = new ArrayList<>();
    private android.widget.TabWidget tabs;
    private android.widget.LinearLayout tab1;
    private android.widget.LinearLayout tab2;
    private android.widget.LinearLayout tab3;
    private android.widget.FrameLayout tabcontent;
    private android.widget.TabHost storeTabHost;
    private ImageView isCescoImg;
    private TextView isCescoTxt;
    private TextView minDeliveryCostInTabTxt;
    private TextView openAndCloseTimeTxt;
    private TextView corpNameTxt;
    private TextView corpNumTxt;
    private android.widget.ListView menuListView;

    // 메뉴의 경우에는, 가게에 따라 그 데이터가 전혀 달라지게됨
    // 즉 앱 전체적으로 공유하는 성질의 데이터가 아니므로
    // 액티비티 내부에서 선언하고 활용;
    private List<MenuData> menuDatas = new ArrayList<>();
    private MenuAdapter menuAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_store_info);



        // 인텐트에 첨부된 데이터를 실제로 받아오는 문장
        mStoreData = (StoreData) getIntent().getSerializableExtra("가게정보데이터");
        // 이 화면에 들어올때는 반드시 "가게데이터"라고 메모해서, StoreData 객체를 보내줘야함.
        bindViews();
        setupEvents();
        makeTabHost();
        setValues();

    }

    private void makeTabHost() {
        // TabHost를 사용하고 싶을땐 반드시 setup메쏘드부터 실행하자.
        storeTabHost.setup();

        // TabHost에 사용될 TabSpec들을 만들어준다.

        TabHost.TabSpec spec1 = storeTabHost.newTabSpec("tab1").setIndicator("메뉴");
        spec1.setContent(R.id.tab1);
        storeTabHost.addTab(spec1);

        TabHost.TabSpec spec2 = storeTabHost.newTabSpec("tab1").setIndicator("리뷰");
        spec2.setContent(R.id.tab2);
        storeTabHost.addTab(spec2);

        TabHost.TabSpec spec3 = storeTabHost.newTabSpec("tab1").setIndicator("정보");
        spec3.setContent(R.id.tab3);
        storeTabHost.addTab(spec3);

    }


    @Override
    public void setupEvents() {
        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ConfirmOrderActivity.class);
                intent.putExtra("메뉴데이터", mStoreData.getMenuDatas().get(position));
//                intent.putExtra("메뉴데이터",(ArrayList<MenuData>) mStoreData.getMenuDatas()));
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {
        storeNameTxt.setText(mStoreData.getStoreName());

        // 가계의 프로필 사진을 표시하는 부분
        Glide.with(mContext).load(mStoreData.getImagePath()).into(storeImg);
        // with => 어디서 글라이드를 쓸건지
        // load => 어떤 이미지를 불러올건지 (경로)
        // into => 어느 이미지뷰에다가 집어넣을건지

        //평점에 맞는는별 표시 부분
        // 별 다섯개를 리스트로 저장해두는 부분.
        // 왜 리스트? 0~마지막까지 돌면서 하나하나 별모양을 바꿔주려고 하는데.
        // 이를 지원하기에 제일 편한 자료구조가 List라서.
        stars.clear();
        stars.add(star1Img);
        stars.add(star2Img);
        stars.add(star3Img);
        stars.add(star4Img);
        stars.add(star5Img);

        // 모든 별들을 빼내서 회색으로 바꿔주는 반복문.
        // "모든" 걸 빼낼땐, for문의 다른형태로 하는게 편함.
        for (ImageView iv : stars) {
            iv.setImageResource(R.drawable.gray_star);
        }

        // 몇개의 별이 노랗게 칠해져야 하는지 구하는 부분.
        // 단순히 소수점 자리를 버리는것으로 대체.
        // 소수점자리를 버리는 제일 간단한 방법은 int로 캐스팅.
        // for문을 돌때도 int를 쓰는게 편하니까 int로 캐스팅.
        int rating = (int) mStoreData.getAvgRating();

        // 구해진 노랗게 칠할 별의 갯수만큼
        // 실제로 별을 칠해주는 부분.
        // 노란별은 끝까지 칠해지지 않는 경우도 있음.
        // 그러므로 전통적인 int i를 이용한 for문을 돌림.
        for (int i = 0; i < rating; i++) {
            stars.get(i).setImageResource(R.drawable.fill_star);
        }

//        ratingAvgTxt.setText(mStoreData.getAvgRating()+"");
        String avgRatingStr = String.format(Locale.KOREA, "%.1f", mStoreData.getAvgRating());
        ratingAvgTxt.setText(avgRatingStr);

        String minDeliveryCostStr = String.format(Locale.KOREA, "%,d원", mStoreData.getMinCost());
        minCostTxt.setText(minDeliveryCostStr);

        // 정보 탭에 최소 배달 금액을 설정하는 부분.
        // setValue 상단에 이미 최소 배달금액을 표시하는 부분이 있다.
        // 그 부분에서 String 변수로 만들어서 내용을 표시하고 있으므로
        // 그 변수를 그대로 setText하는걸로 마무리.
        minDeliveryCostInTabTxt.setText(minDeliveryCostStr);

        // mStoreData가 가진 openTime과 closeTime을 참조하여
        // 여는 시간:분 - 닫는 시간:분 을 표기하는 부분.

        // 시간 : /100으로 계산, 분: %100으로 계산
        int openHour = mStoreData.getOpenTime() / 100;
        int openMinute = mStoreData.getOpenTime() % 100;
        int closeHour = mStoreData.getCloseTime() / 100;
        int closeMinute = mStoreData.getCloseTime() % 100;

        // 열고 닫는시간과 분을, 무조건 두자리 정수로 표현하여 - 으로 연결하는 format
        // 일반적으로 정수를 표현하고 싶을땐 %d 그런데 자리수를 두자리로 하기 위해 %02d로 변경.
        // %02d => 정수를 무조건 두자리 (5=>05)
        String openColseStr = String.format(Locale.KOREA, "%02d:%02d - %02d:%02d", openHour, openMinute, closeHour, closeMinute);
        openAndCloseTimeTxt.setText(openColseStr);


        //세스코 가입 여부 표시
        if (mStoreData.isCesco()) {
            isCescoImg.setVisibility(View.VISIBLE);
            isCescoTxt.setText("세스코멤버스 사업장");
        } else {
            isCescoImg.setVisibility(View.GONE);
            isCescoTxt.setText("-");
        }

        corpNameTxt.setText(mStoreData.getCorpName());
        corpNumTxt.setText(mStoreData.getCorpNumber());

        menuAdapter = new MenuAdapter(mContext, mStoreData.getMenuDatas());
        menuListView.setAdapter(menuAdapter);


    }


    @Override
    public void bindViews() {
        this.storeTabHost = (TabHost) findViewById(R.id.storeTabHost);
        this.tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        this.tab3 = (LinearLayout) findViewById(R.id.tab3);
        this.corpNumTxt = (TextView) findViewById(R.id.corpNumTxt);
        this.corpNameTxt = (TextView) findViewById(R.id.corpNameTxt);
        this.minDeliveryCostInTabTxt = (TextView) findViewById(R.id.minDeliveryCostInTabTxt);
        this.isCescoTxt = (TextView) findViewById(R.id.isCescoTxt);
        this.isCescoImg = (ImageView) findViewById(R.id.isCescoImg);
        this.openAndCloseTimeTxt = (TextView) findViewById(R.id.openAndCloseTimeTxt);
        this.tab2 = (LinearLayout) findViewById(R.id.tab2);
        this.tab1 = (LinearLayout) findViewById(R.id.tab1);
        this.menuListView = (ListView) findViewById(R.id.menuListView);
        this.tabs = (TabWidget) findViewById(android.R.id.tabs);
        this.minCostTxt = (TextView) findViewById(R.id.minCostTxt);
        this.ratingAvgTxt = (TextView) findViewById(R.id.ratingAvgTxt);
        this.star5Img = (ImageView) findViewById(R.id.star5Img);
        this.star4Img = (ImageView) findViewById(R.id.star4Img);
        this.star3Img = (ImageView) findViewById(R.id.star3Img);
        this.star2Img = (ImageView) findViewById(R.id.star2Img);
        this.star1Img = (ImageView) findViewById(R.id.star1Img);
        this.storeImg = (ImageView) findViewById(R.id.storeImg);
        this.storeNameTxt = (TextView) findViewById(R.id.storeNameTxt);

    }

}
