package kr.co.tjeit.yogiyocopy;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.co.tjeit.yogiyocopy.adapter.OrderAdapter;
import kr.co.tjeit.yogiyocopy.adapter.StoreAdapter;
import kr.co.tjeit.yogiyocopy.data.MenuData;
import kr.co.tjeit.yogiyocopy.data.OrderData;
import kr.co.tjeit.yogiyocopy.data.StoreData;
import kr.co.tjeit.yogiyocopy.util.GlobalData;

public class MainActivity extends BaseActivity {

    TabHost myTabHost;

    private ListView storeListView;

    StoreAdapter storeAdapter;

    private ListView orderListView;

    List<OrderData> orderDataList = new ArrayList<>();

    OrderAdapter orderAdapter;

    List<MenuData> menuDatas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();


    }


    @Override
    public void setupEvents() {
        storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ViewStoreInfoActivity.class);
                intent.putExtra("가게정보데이터", GlobalData.storeDataList.get(position));
                startActivity(intent);
            }
        });

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ViewOrderInfoActivity.class);
                intent.putExtra("주문정보데이터",orderDataList.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

        makeTabHost();
        addListDatas();
        storeAdapter = new StoreAdapter(MainActivity.this, GlobalData.storeDataList);
        storeListView.setAdapter(storeAdapter);

        orderAdapter = new OrderAdapter(MainActivity.this, orderDataList);
        orderListView.setAdapter(orderAdapter);
    }

    private void addMenu(){
        menuDatas.clear();
        menuDatas.add(new MenuData("활력 도시락",true, 23900,"https://www.yogiyo.co.kr/media/img/test_images/franchise_photo_menu/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD/2017-08-07/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD_%ED%99%9C%EB%A0%A5%EB%8F%84%EC%8B%9C%EB%9D%BD_170807_menuitem_original.jpg"));
        menuDatas.add(new MenuData("울릉도 한상 도시락",true, 10900,"https://www.yogiyo.co.kr/media/img/test_images/franchise_photo_menu/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD/2017-03-14/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD_%EC%9A%B8%EB%A6%89%EB%8F%84%ED%95%9C%EC%83%81%EB%8F%84%EC%8B%9C%EB%9D%BD_170313_menuitem_original.jpg"));
        menuDatas.add(new MenuData("일품불고기 도시락",false, 10900,"https://www.yogiyo.co.kr/media/img/test_images/franchise_photo_menu/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD/2017-03-14/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD_%EB%8B%A4%EC%9D%B4%EC%96%B4%ED%8A%B8%EB%8B%AD%EA%B0%80%EC%8A%B4%EC%82%B4_170313_menuitem_original.jpg"));
        menuDatas.add(new MenuData("숯불제육구이쌈밥 도시락",true, 8500,"https://www.yogiyo.co.kr/media/img/test_images/franchise_photo_menu/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD/2017-03-14/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD_%EC%88%AF%EB%B6%88%EC%A0%9C%EC%9C%A1%EC%8C%88%EB%B0%A5_170313_menuitem_original.jpg"));
    }


    private void addListDatas() {


        GlobalData.storeDataList.clear();
        GlobalData.storeDataList.add(new StoreData("https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/kyochon_logo.jpg", "교촌치킨-대학로점", 4.2f, 1200, 2330, 15000, true,"123-45-67890","교촌치킨대학로점"));
        GlobalData.storeDataList.add(new StoreData("https://s3.ap-northeast-2.amazonaws.com/slws3/imgs/tje_practice/one_logo.jpg", "원할머니보쌈-종로5가점", 3.8f, 1100, 300, 25000, false,"234-46-67890","원할머니보쌈종로5점"));
        GlobalData.storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EB%96%A1%EB%B3%B6%EC%9D%B4%EC%88%9C%EB%8C%80%EC%84%B8%ED%8A%B801_20131128_FoodAD_crop_200x200_yjnTv3z.jpg", "스쿨스토어-종로점", 4.2f, 1200, 2330, 15000, true,"199-45-67890","스쿨스토어종로점"));
        GlobalData.storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EB%B3%B8%EB%8F%84%EC%8B%9C%EB%9D%BD_20150825_Franchise%EC%9D%B4%EB%AF%B8%EC%A7%80%EC%95%BD%EC%A0%95%EC%84%9C_crop_200x200_zY4cB53.jpg", "본도시락-서울시청점", 3.8f, 1100, 300, 25000, false,"123-45-67889","본도시락서울시청점"));
        GlobalData.storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%EC%89%AC%EB%A6%BC%ED%94%84_%ED%94%BC%EC%9E%9001_20131128_FoodAD_crop_200x200.jpg", "훼미리피자", 4.2f, 1200, 2330, 15000, true,"123-45-78740","훼미리피자"));
        GlobalData.storeDataList.add(new StoreData("https://www.yogiyo.co.kr/media/restaurant_logos/%ED%83%95%EC%88%98%EC%9C%A103_20131128_FoodAD_crop_200x200_Rn9zt25.jpg", "남경-남대문시장점", 3.8f, 1100, 300, 25000, false,"123-45-66890","남경남대문점"));

        addMenu();
        GlobalData.storeDataList.get(3).setMenuDatas(menuDatas);

        orderDataList.add(new OrderData(GlobalData.storeDataList.get(0), Calendar.getInstance(), "종로 3가", 15000));
        orderDataList.add(new OrderData(GlobalData.storeDataList.get(0), Calendar.getInstance(), "종로 1가", 10000));
        orderDataList.add(new OrderData(GlobalData.storeDataList.get(0), Calendar.getInstance(), "을지로 3가", 30000));

//         원할머니 보쌈 2번, 훼미리피자 1번, 스쿨스토어 2번
        orderDataList.add(new OrderData(GlobalData.storeDataList.get(1), Calendar.getInstance(), "종로 1가", 30000));
        orderDataList.add(new OrderData(GlobalData.storeDataList.get(1), Calendar.getInstance(), "종로 2가", 15000));
        orderDataList.add(new OrderData(GlobalData.storeDataList.get(4), Calendar.getInstance(), "종로 3가", 15000));
        orderDataList.add(new OrderData(GlobalData.storeDataList.get(2), Calendar.getInstance(), "종로 4가", 11000));
        orderDataList.add(new OrderData(GlobalData.storeDataList.get(2), Calendar.getInstance(), "종로 5가", 10000));
    }

    private void makeTabHost() {
        myTabHost.setup();

        TabHost.TabSpec spec1 = myTabHost.newTabSpec("tab1").setIndicator("가게 목록");
        spec1.setContent(R.id.tab1);
        myTabHost.addTab(spec1);

        TabHost.TabSpec spec2 = myTabHost.newTabSpec("tab2").setIndicator("주문내역");
        spec2.setContent(R.id.tab2);
        myTabHost.addTab(spec2);

        TabHost.TabSpec spec3 = myTabHost.newTabSpec("tab3").setIndicator("더보기");
        spec3.setContent(R.id.tab3);
        myTabHost.addTab(spec3);

        TabHost.TabSpec spec4 = myTabHost.newTabSpec("tab4").setIndicator("...");
        spec4.setContent(R.id.tab4);
        myTabHost.addTab(spec4);
    }


    @Override
    public void bindViews() {
        myTabHost = (TabHost)findViewById(R.id.myTabHost);
        orderListView = (ListView)findViewById(R.id.orderListView);
        storeListView = (ListView)findViewById(R.id.storeListView);






    }
}
