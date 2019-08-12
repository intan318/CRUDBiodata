package com.example.crudbiodataagustus.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class DataBiodataItem implements Parcelable {

	@SerializedName("jekel")
	private String jekel;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private String id;

	@SerializedName("hobi")
	private String hobi;

	@SerializedName("alamat")
	private String alamat;

	public void setJekel(String jekel){
		this.jekel = jekel;
	}

	public String getJekel(){
		return jekel;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setHobi(String hobi){
		this.hobi = hobi;
	}

	public String getHobi(){
		return hobi;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	@Override
 	public String toString(){
		return 
			"DataBiodataItem{" + 
			"jekel = '" + jekel + '\'' + 
			",nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			",hobi = '" + hobi + '\'' + 
			",alamat = '" + alamat + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.jekel);
		dest.writeString(this.nama);
		dest.writeString(this.id);
		dest.writeString(this.hobi);
		dest.writeString(this.alamat);
	}

	public DataBiodataItem() {
	}

	protected DataBiodataItem(Parcel in) {
		this.jekel = in.readString();
		this.nama = in.readString();
		this.id = in.readString();
		this.hobi = in.readString();
		this.alamat = in.readString();
	}

	public static final Parcelable.Creator<DataBiodataItem> CREATOR = new Parcelable.Creator<DataBiodataItem>() {
		@Override
		public DataBiodataItem createFromParcel(Parcel source) {
			return new DataBiodataItem(source);
		}

		@Override
		public DataBiodataItem[] newArray(int size) {
			return new DataBiodataItem[size];
		}
	};
}