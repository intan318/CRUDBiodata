package com.example.crudbiodataagustus;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudbiodataagustus.model.DataBiodataItem;
import com.example.crudbiodataagustus.model.ResponseGetBiodata;
import com.example.crudbiodataagustus.network.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(i);
            }
        });

        actionGetBiodata();
    }

    private void actionGetBiodata(){

        //instance
        ApiClient.service.actionGetBiodata().enqueue(new Callback<ResponseGetBiodata>() {
            @Override
            public void onResponse(Call<ResponseGetBiodata> call, Response<ResponseGetBiodata> response) {
                if (response.isSuccessful()){
                    //response sesuai variabel di atas
                    String pesan = response.body().getPesan();
                    int status = response.body().getStatus(); //int karena di DataBiodataItem dia data type nya integer
                    List<DataBiodataItem> array = response.body().getDataBiodata();

                    BiodataAdapter adapter = new BiodataAdapter(MainActivity.this, array);
                    adapter.notifyDataSetChanged();

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);


//                    for(int i = 0; i < array.size(); i++){
//                        String nama = array.get(0).getNama();
//                        String alamat = array.get(0).getAlamat();
//                        Log.d("indexnol", nama + alamat);

                    }

                }


            @Override
            public void onFailure(Call<ResponseGetBiodata> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        actionGetBiodata();
    }
}
