package com.noobdev.ciap;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ItemActivity extends FragmentActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    String Letra;
    String Categoria;
    DAL dal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_item);
        centerActionBarTitle();
        PreparaMenu(savedInstanceState);
        if(getIntent().getExtras() == null)
        {
            Intent intent = new Intent(getApplicationContext(), CategoriaActivity.class);
            startActivity(intent);
        }
        Categoria = getIntent().getExtras().getString("Categoria");
        Letra =getIntent().getExtras().getString("Letra");
        ((TextView)(findViewById(R.id.txtGrupo))).setText(Letra);
        ((TextView)(findViewById(R.id.txtTitulo))).setText(Categoria);

        dal = new DAL(this);
        List<Categoria>  ArrayCategorias= dal.GetItensBy(Categoria);
        FillList(ArrayCategorias);


        ((EditText)findViewById(R.id.txtPesquisaCategoria)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    String Pesquisa = ((EditText)findViewById(R.id.txtPesquisaCategoria)).getText().toString();
                    List<Categoria>  ArrayCategorias= dal.GetItensBy(Categoria,Pesquisa);
                    FillList(ArrayCategorias);
                }
                return false;
            }
        });


        ((ImageView)findViewById(R.id.btnSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pesquisa = ((EditText)findViewById(R.id.txtPesquisaCategoria)).getText().toString();
                List<Categoria>  ArrayCategorias= dal.GetItensBy(Categoria,Pesquisa);
                FillList(ArrayCategorias);
            }
        });

    }
    private void FillList(List<Categoria> Itens)
    {

        Categoria[] cats = Itens.toArray(new Categoria[Itens.size()]);
        final CategoriaAdapter adapter = new CategoriaAdapter(this,
                R.layout.listview_item_row_categoria, cats);
        ListView listView1 = (ListView) findViewById(R.id.lstCategoria);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Categoria Item = adapter.getItem(i);

                Intent intent = new Intent(getApplicationContext(), ItemDetalheActivity.class);
                intent.putExtra("Categoria",Categoria);
                intent.putExtra("Letra",Letra);
                intent.putExtra("Descricao", Item.Titulo);
                intent.putExtra("Grupo", Item.Descricao);
                startActivity(intent);
            }
        });
    }


    private void PreparaMenu(Bundle savedInstanceState) {

        List<MenuClass> ArrayMenu;
        ArrayMenu =  new ArrayList<MenuClass>();
        ArrayMenu.add(new MenuClass(R.drawable.catalogoicone,"Catálogo CIAP-2"));
        ArrayMenu.add(new MenuClass(R.drawable.sobreicone,"Sobre"));
        ArrayMenu.add(new MenuClass(R.drawable.contatoicone,"Contato"));

        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        MenuClass[] itens = ArrayMenu.toArray(new MenuClass[ArrayMenu.size()]);

        final MenuAdapter adapter = new MenuAdapter(this,
                R.layout.drawer_list_custom_item, itens);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0: {
                        Intent intent = new Intent(getApplicationContext(), CategoriaActivity.class);
                        startActivity(intent);
                    }break;
                    case 1: {
                        Intent intent = new Intent(getApplicationContext(), SobreActivity.class);
                        startActivity(intent);

                    }break;
                    case 2: {
                        Intent intent = new Intent(getApplicationContext(), ContatoActivity.class);
                        startActivity(intent);
                    }break;
                }

            }
        });


        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.menu,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /* The click listner for ListView in the navigation drawer */

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    private void centerActionBarTitle()
    {

        int titleId = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        }
        else
        {
            // This is the id is from your app's generated R class when ActionBarActivity is used
            // for SupportActionBar
            titleId = R.id.action_bar_title;
        }

        // Final check for non-zero invalid id
        if (titleId > 0)
        {
            TextView titleTextView = (TextView) findViewById(titleId);

            DisplayMetrics metrics = getResources().getDisplayMetrics();

            // Fetch layout parameters of titleTextView (LinearLayout.LayoutParams : Info from HierarchyViewer)
            LinearLayout.LayoutParams txvPars = (LinearLayout.LayoutParams) titleTextView.getLayoutParams();
            txvPars.gravity = Gravity.CENTER_HORIZONTAL;
            txvPars.width = metrics.widthPixels;
            titleTextView.setLayoutParams(txvPars);

            titleTextView.setGravity(Gravity.CENTER);
        }
    }

}
