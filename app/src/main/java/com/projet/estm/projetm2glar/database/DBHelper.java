package com.projet.estm.projetm2glar.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.projet.estm.projetm2glar.model.Produit;
import com.projet.estm.projetm2glar.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final String DB_FILE_NAME = "ecommerc.db";
    public static final int DB_VERSION = 1;
    private static final String ITEM_NAME = "ItemName";

    public DBHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,Produit.class);
//            TableUtils.createTable(connectionSource,User.class);
        } catch (Exception exception) {
            Log.e("DATABASE","Can't create Database",exception);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,Produit.class,true);
//            TableUtils.dropTable(connectionSource,User.class,true);
            onCreate(database,connectionSource);
            Log.i("DATABASE","onUpgrade invoked");
        } catch (Exception exception) {
            Log.e("DATABASE","Can't upgrade Database",exception);

        }

    }

    //methode pour creer un user
//    public void addUser(User user){
//        try {
//            Dao<User,Integer> dao = getDao(User.class);
//            dao.create(user);
//        } catch (SQLException e) {
//            Log.e("DATABASE","Can't create a user",e);
//        }
//    }
    //methode pour creer un produit
    public void addProduit(Produit produit){
        try {
            Dao<Produit,Integer> dao = getDao(Produit.class);
            dao.create(produit);
        } catch (SQLException e) {
            Log.e("DATABASE","Can't create a product",e);
        }
    }

    public List<Produit> getAllProduit(int category){
        if (category == 0) {
            try {
                Dao<Produit, Integer> dao = getDao(Produit.class);
                List<Produit> produits = dao.queryForAll();
                Log.i("DATABASE", "Recuperer les products");
                return produits;
            } catch (SQLException e) {
                Log.e("DATABASE", "Can't recup a product", e);
                return null;
            }
        }   else {
            try {
                Dao<Produit, Integer> dao = getDao(Produit.class);
                List<Produit> produits = Collections.singletonList(dao.queryForId(category));
                Log.i("DATABASE", "Filtre produit");
                return produits;
            } catch (SQLException e) {
                Log.e("DATABASE", "Can't get a product", e);
                return null;
            }
        }

    }
    public List<Produit> filterSearch(String query){
//        query = query.toLowerCase(Locale.getDefault());
        Dao<Produit, Integer> dao = null;
        if (TextUtils.isEmpty(query)) {
            try {
                dao = getDao(Produit.class);
                List<Produit> produits = dao.queryForAll();
                Log.i("DATABASE", "Recuperer les products");
                return produits;
            } catch (SQLException e) {
                Log.e("DATABASE", "Can't recup a product", e);
                return null;
            }
        }
        try {
            dao = getDao(Produit.class);

            List<Produit> produitLis =
                    dao.query(
                            dao.queryBuilder().where()
                                    .like("ItemName", "%"+query+"%")
                                    .prepare());
            Log.i("DATABASE", "Filtre produit");
            return  produitLis;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public long getDataItemsCount() {
//        return DatabaseUtils.queryNumEntries(mDatabase, "T_Produit");
        long nb=0;
        try {
            Dao<Produit, Integer> dao = getDao(Produit.class);
            nb = dao.countOf();
            Log.i("DATABASE", "Filtre produit");
            return nb;
        } catch (SQLException e) {
            Log.e("DATABASE", "Can't get a product", e);
            return nb;
        }
    }

    public void seedDatabase(List<Produit> dataItemList) {
        long numItems = getDataItemsCount();
        if (numItems == 0) {
            for (int i = 0; i < 1; i++) {

                for (Produit produit : dataItemList) {
                    try {
                        Dao<Produit, Integer> dao = getDao(Produit.class);
                        dao.create(produit);
                    } catch (SQLException e) {
                        Log.e("DATABASE", "Can't create a product", e);
                    }
                }//end for
            }//end if
        }
    }
}