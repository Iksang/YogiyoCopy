package kr.co.tjeit.yogiyocopy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import kr.co.tjeit.yogiyocopy.data.MenuData;

public class ConfirmOrderActivity extends BaseActivity {

    private android.widget.TextView menuNameTxt;
    private android.widget.TextView priceTxt;
    private android.widget.Button minusBtn;
    private android.widget.TextView quantityTxt;
    private android.widget.Button plusBtn;
    private android.widget.TextView totalPriceTxt;
    private MenuData mMenuData = null;
    private int mQuantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        mMenuData = (MenuData) getIntent().getSerializableExtra("메뉴데이터");
        bindViews();
        setupEvents();
        setValues();
    }


    @Override
    public void setupEvents() {

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuantity > 0) {
                    mQuantity--;
                    quantityTxt.setText(mQuantity + "");
                    String priceStr = String.format(Locale.KOREA, "%,d원", mMenuData.getPrice()*mQuantity);
                    totalPriceTxt.setText(priceStr);
                }
            }
        });
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQuantity++;
                quantityTxt.setText(mQuantity + "");
                String priceStr = String.format(Locale.KOREA, "%,d원", mMenuData.getPrice()*mQuantity);
                totalPriceTxt.setText(priceStr);
            }
        });
    }


    @Override
    public void setValues() {
        menuNameTxt.setText(mMenuData.getMenuName());
        String priceStr = String.format(Locale.KOREA, "%,d원", mMenuData.getPrice());
        priceTxt.setText(priceStr);
        totalPriceTxt.setText(priceStr);
    }

    @Override
    public void bindViews() {
        this.totalPriceTxt = (TextView) findViewById(R.id.totalPriceTxt);
        this.plusBtn = (Button) findViewById(R.id.plusBtn);
        this.quantityTxt = (TextView) findViewById(R.id.quantityTxt);
        this.minusBtn = (Button) findViewById(R.id.minusBtn);
        this.priceTxt = (TextView) findViewById(R.id.priceTxt);
        this.menuNameTxt = (TextView) findViewById(R.id.menuNameTxt);

    }
}
