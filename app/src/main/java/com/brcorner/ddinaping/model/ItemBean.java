package com.brcorner.ddinaping.model;

/**
 * Created by Auser on 2015/3/13.
 */
public class ItemBean {
    private int itemId;
    private int itemImgId;
    private String itemName;
    private String itemContent;
    private String itemPrice;
    private String selledNum;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getSelledNum() {
        return selledNum;
    }

    public void setSelledNum(String selledNum) {
        this.selledNum = selledNum;
    }

    public int getItemImgId() {
        return itemImgId;
    }

    public void setItemImgId(int itemImgId) {
        this.itemImgId = itemImgId;
    }
}
