package com.example.crudbiodataagustus;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.crudbiodataagustus.model.ResponseGetBiodata;
import com.example.crudbiodataagustus.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class InsertActivity extends AppCompatActivity {

    EditText editTextNama, editTextAlamat;
    Spinner spinnerHobi;
    RadioGroup rgJenisKelamin;
    RadioButton rbLaki, rbPerempuan;
    Button buttonInsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editTextNama = findViewById(R.id.etNama);
        editTextAlamat = findViewById(R.id.etAlamat);
        rbLaki = findViewById(R.id.rbLakilaki);
        rbPerempuan = findViewById(R.id.rbPerempuan);
        buttonInsert = findViewById(R.id.btnInsert);
        spinnerHobi = findViewById(R.id.spinnerHobi);

        String hobi [] = {"Pilih hobi","Memanah", "Membaca", "Berenang","Bermain", "Makan" };
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hobi);
        spinnerHobi.setAdapter(adapterSpinner);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = editTextNama.getText().toString().trim();
                String alamat = editTextAlamat.getText().toString().trim();
                String hobi = spinnerHobi.getSelectedItem().toString();

                String jenkel = null;
                if (rbLaki.isChecked()){
                    jenkel = rbLaki.getText().toString();
                } else if (rbPerempuan.isChecked()) {
                    jenkel = rbPerempuan.getText().toString();
                }

                // todo buat validasi
                if (TextUtils.isEmpty(nama)){
                    editTextNama.setError("Nama can't be empty");
                } else if (TextUtils.isEmpty(jenkel)){
                    Toast.makeText(InsertActivity.this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show();
                } else if (hobi.equals("Pilih hobi")){
                    Toast.makeText(InsertActivity.this, "pilih hobi", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(alamat)){
                    editTextAlamat.setError("alamat can't be empty");
                } else {
                    insertBiodata(nama, jenkel, hobi, alamat);
                }

            }
        });

    }

    private void insertBiodata(String nama, String jenkel, String hobi, String alamat){
        ApiClient.service.actionInsert(nama, jenkel, hobi, alamat).enqueue(new Callback<ResponseGetBiodata>() {
            @Override
            public void onResponse(Call<ResponseGetBiodata> call, Response<ResponseGetBiodata> response) {
                if (response.isSuccessful()){

                    String pesan = response.body().getPesan();
                    int status = response.body().getStatus();

                    if (status == 1) {
                        Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (status == 0){
                        Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBiodata> call, Throwable t) {
                Toast.makeText(InsertActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
