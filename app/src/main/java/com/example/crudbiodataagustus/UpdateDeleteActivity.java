package com.example.crudbiodataagustus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crudbiodataagustus.model.DataBiodataItem;
import com.example.crudbiodataagustus.model.ResponseGetBiodata;
import com.example.crudbiodataagustus.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDeleteActivity extends AppCompatActivity {

    //var static
    public static String key_biodata = "key_biodata";

    EditText editUpdateNama, editUpdateAlamat;
    Button buttonUpdate, buttonDelete;
    Spinner spinnerUpdateHobi;
    RadioGroup rgUpdateJekel;
    RadioButton rbUpdatePerempuan, rbUpdateLakilaki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        editUpdateNama = findViewById(R.id.etUpdateNama);
        editUpdateAlamat = findViewById(R.id.etUpdateAlamat);
        buttonDelete = findViewById(R.id.btnDelete);
        buttonUpdate = findViewById(R.id.btnUpdate);
        spinnerUpdateHobi = findViewById(R.id.spinnerUpdateHobi);
        rbUpdateLakilaki = findViewById(R.id.rbUpdateLakilaki);
        rbUpdatePerempuan = findViewById(R.id.rbUpdatePerempuan);


        // todo terima data sekaligus menampilkan data
        DataBiodataItem data = getIntent().getParcelableExtra(key_biodata);
        editUpdateNama.setText(data.getNama());
        editUpdateAlamat.setText(data.getAlamat());

        if(data.getJekel().equals("Laki-laki")){
            rbUpdateLakilaki.setChecked(true);
        } else if (data.getJekel().equals("Perempuan")){
            rbUpdatePerempuan.setChecked(true);
        }

        String hobi [] = {"Pilih hobi","Memanah", "Membaca", "Berenang","Bermain", "Makan" };
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hobi);
        spinnerUpdateHobi.setAdapter(adapterSpinner);


        //todo set terima data hobi ke index spinner
        for (int i = 0; i<adapterSpinner.getCount(); i++){
            if (spinnerUpdateHobi.getItemAtPosition(i).equals(data.getHobi())){
                spinnerUpdateHobi.setSelection(i);
            }
        }

        final String terimaID = data.getId();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editUpdateNama.getText().toString().trim();
                String alamat = editUpdateAlamat.getText().toString().trim();
                String hobi = spinnerUpdateHobi.getSelectedItem().toString();

                String jenkel = null;
                if (rbUpdateLakilaki.isChecked()){
                    jenkel = rbUpdateLakilaki.getText().toString();
                } else if (rbUpdatePerempuan.isChecked()) {
                    jenkel = rbUpdatePerempuan.getText().toString();
                }

                // todo buat validasi
                if (TextUtils.isEmpty(nama)){
                    editUpdateNama.setError("Nama can't be empty");
                } else if (TextUtils.isEmpty(jenkel)){
                    Toast.makeText(UpdateDeleteActivity.this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show();
                } else if (hobi.equals("Pilih hobi")){
                    Toast.makeText(UpdateDeleteActivity.this, "pilih hobi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(alamat)){
                    editUpdateAlamat.setError("alamat can't be empty");
                } else {
                    //action update
                    updateBiodata(terimaID, nama, jenkel, hobi, alamat);

                }

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBiodata(terimaID);
            }
        });

    }

    private  void updateBiodata(String id, String nama, String jenkel, String hobi, String alamat){

        ApiClient.service.actionUpdate(id, nama, jenkel, hobi, alamat).enqueue(new Callback<ResponseGetBiodata>() {
            @Override
            public void onResponse(Call<ResponseGetBiodata> call, Response<ResponseGetBiodata> response) {
                if (response.isSuccessful()){

                    String pesan = response.body().getPesan();
                    int status = response.body().getStatus();

                    if (status == 1) {
                        Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (status == 0){
                        Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBiodata> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteBiodata(String id){
        ApiClient.service.actionDelete(id).enqueue(new Callback<ResponseGetBiodata>() {
            @Override
            public void onResponse(Call<ResponseGetBiodata> call, Response<ResponseGetBiodata> response) {
                if (response.isSuccessful()){

                    String pesan = response.body().getPesan();
                    int status = response.body().getStatus();

                    if (status == 1) {
                        Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (status == 0){
                        Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBiodata> call, Throwable t) {
                Toast.makeText(UpdateDeleteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
