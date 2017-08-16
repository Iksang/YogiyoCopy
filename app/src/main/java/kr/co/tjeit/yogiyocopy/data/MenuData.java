package kr.co.tjeit.yogiyocopy.data;

import java.io.Serializable;

/**
 * Created by tjoeun on 2017-08-16.
 */

public class MenuData implements Serializable {
    private String menuName;
    private boolean isSet;
    private int price;
    private String imagePath;

    public MenuData() {
    }

    public MenuData(String menuName, boolean isSet, int price, String imagePath) {
        this.menuName = menuName;
        this.isSet = isSet;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
