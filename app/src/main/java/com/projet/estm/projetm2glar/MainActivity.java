package com.projet.estm.projetm2glar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projet.estm.projetm2glar.database.DBHelper;
import com.projet.estm.projetm2glar.model.Produit;
import com.projet.estm.projetm2glar.sample.SampleDataProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int SIGNIN_REQUEST = 1001;
    public static final String MY_GLOBAL_PREFS = "my_global_prefs";
    List<Produit> dataProduitList = SampleDataProvider.produitList;
    public static final String FORM_KEY = "form_key";

//    DataSource mDataSource;
    List<Produit> listFromDB;
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;

    ListView mDrawerList;
    String[] mCategories;
    RecyclerView mRecyclerView;
    DataItemAdapter mItemAdapter;
    DBHelper dbHelper;

    Context myContext;

    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myContext = this;
//recuperer les categories de produits
        mCategories = getResources().getStringArray(R.array.categories);

        //code pour toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//      Code to manage sliding navigation drawer

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //float ajouter et modifier nouveau produit
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext, FormActivity.class);
                intent.putExtra(FORM_KEY, "");
                myContext.startActivity(intent);
            }
        });

        // appel au bd
        dbHelper = new DBHelper(this);
        dbHelper.seedDatabase(dataProduitList);
        mRecyclerView = (RecyclerView) findViewById(R.id.rvItems);
        displayDataItems(0);
    }
    //gerer le retour sur le toolbar
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//
    @Override
    protected void onPause() {
        super.onPause();
        dbHelper.close();
    }
    //recuperer et filtrer par categorie
    private void displayDataItems(int category) {
//        listFromDB = mDataSource.getAllItems(category);
        listFromDB = dbHelper.getAllProduit(category);
        mItemAdapter = new DataItemAdapter(this, listFromDB);
        mRecyclerView.setAdapter(mItemAdapter);
    }
    private void displayBySearch(String query) {
        listFromDB = dbHelper.filterSearch(query);
        mItemAdapter = new DataItemAdapter(this, listFromDB);
        mRecyclerView.setAdapter(mItemAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper.isOpen();
//        mDataSource.open();
    }

    //afficher les menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // rechercher
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
//        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    displayBySearch("");
//                    mRecyclerView.clearTextFilter();
                }else {
                    if (newText.contains("'"))
                        displayBySearch(newText.replace("'","''"));
                    else
                        displayBySearch(newText);
                }
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
//        return super.onCreateOptionsMenu(menu);
    }

    //evenement sur les menus
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_signin:
                Intent intent = new Intent(this, SigninActivity.class);
                startActivityForResult(intent, SIGNIN_REQUEST);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //authentification
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == SIGNIN_REQUEST) {
            String email = data.getStringExtra(SigninActivity.EMAIL_KEY);
            Toast.makeText(this, "You signed in as " + email, Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor =
                    getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();
            editor.putString(SigninActivity.EMAIL_KEY, email);
            editor.apply();

        }

    }

    //drawable
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
