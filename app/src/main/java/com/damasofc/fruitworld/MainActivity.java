package com.damasofc.fruitworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    GridView gridView;
    List <Frutas> fruits;
    int cont = 0;
    MyAdapter myAdapter;
    MenuItem itemGrid;
    MenuItem itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }
    private void initComponents(){
        fruits = new ArrayList<Frutas>();
        fruits.add(new Frutas("Banana","Gualala",R.drawable.ic_banana));
        fruits.add(new Frutas("Strawberry","Tegucigalpa",R.drawable.ic_strawberry));
        fruits.add(new Frutas("Orange","Progreso",R.drawable.ic_orange));
        fruits.add(new Frutas("Apple","Quimistan",R.drawable.ic_apple));
        fruits.add(new Frutas("Cherry","SPS",R.drawable.ic_cherry));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_fruit);
        listView = (ListView)findViewById(R.id.listView);
        gridView = (GridView)findViewById(R.id.gridView);
        listView.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.INVISIBLE);
        myAdapter = new MyAdapter(this,R.layout.model_listview,fruits);
        listView.setAdapter(myAdapter);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(this);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);
        itemGrid = menu.findItem(R.id.changeGrid);
        itemList = menu.findItem(R.id.changeList);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                this.fruits.add(new Frutas("Added n°"+ ++cont,"SPS",R.drawable.ic_strawberry));
                this.myAdapter.notifyDataSetChanged();
                return true;
            case R.id.changeGrid:
                listView.setVisibility(View.INVISIBLE);
                gridView.setVisibility(View.VISIBLE);
                myAdapter = new MyAdapter(this,R.layout.model_gridview,fruits);
                gridView.setAdapter(myAdapter);
                itemGrid.setVisible(false);
                itemList.setVisible(true);
                registerForContextMenu(gridView);
                return true;
            case R.id.changeList:
                listView.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.INVISIBLE);
                myAdapter = new MyAdapter(this,R.layout.model_listview,fruits);
                listView.setAdapter(myAdapter);
                itemGrid.setVisible(true);
                itemList.setVisible(false);
                registerForContextMenu(listView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.fruits.get(info.position).getNombre());
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete:
                this.fruits.remove(info.position);
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(fruits.get(position).getNombre().startsWith("Add")){
            Toast.makeText(this, "Sorry, we don't have many info about "+fruits.get(position).getNombre(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "The best fruit from "+fruits.get(position).getOrigen()+" is "+ fruits.get(position).getNombre(), Toast.LENGTH_SHORT).show();
        }
    }
}
