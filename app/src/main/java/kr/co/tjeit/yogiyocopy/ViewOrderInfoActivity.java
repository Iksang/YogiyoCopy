package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

import kr.co.tjeit.yogiyocopy.data.OrderData;

public class ViewOrderInfoActivity extends BaseActivity {

    private android.widget.TextView storeNameTxt;
    private android.widget.TextView locationTxt;
    private android.widget.TextView costTxt;
    private OrderData mOrderData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_info);
        mOrderData = (OrderData)getIntent().getSerializableExtra("주문정보데이터");

        bindViews();
        setupEvents();
        setValues();
    }


    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        storeNameTxt.setText(mOrderData.getOrderStore().getStoreName());
        locationTxt.setText(mOrderData.getLocation());
        costTxt.setText(String.format(Locale.KOREA,"%,d원",mOrderData.getCost()));

    }


    @Override
    public void bindViews() {
        this.costTxt = (TextView) findViewById(R.id.costTxt);
        this.locationTxt = (TextView) findViewById(R.id.locationTxt);
        this.storeNameTxt = (TextView) findViewById(R.id.storeNameTxt);
    }
}
