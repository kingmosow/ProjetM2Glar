package com.projet.estm.projetm2glar.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "T_Produit")
public class Produit implements Serializable {
    @DatabaseField(columnName = "itemId", generatedId = true)
    private int itemId;
    //@DatabaseField(canBeNull = false,foreign = true,foreignColumnName = "userId",foreignAutoCreate = true)
    //private User user;
    @DatabaseField
    private String itemName;
    @DatabaseField
    private String description;
    @DatabaseField
    private Boolean stock;
    @DatabaseField
    private String category;
    @DatabaseField
    private int prix;
    @DatabaseField
    private String image;

    public Produit() {
    }

    public Produit( String itemName, String category, String description, Boolean stock, int prix, String image) {
        this.itemName = itemName;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.prix = prix;
        this.image = image;
    }


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                ", prix='" + prix + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
