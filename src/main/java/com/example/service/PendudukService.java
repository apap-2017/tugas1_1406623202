package com.example.service;

import java.util.List;

import com.example.model.AlamatModel;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KeluargaModel2;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

public interface PendudukService
{
    PendudukModel selectPenduduk (String nik);
    
    AlamatModel selectAlamat(int id_keluarga);
    
	KeluargaModel selectKeluarga(String nomor_kk);

	void addPenduduk(PendudukModel pendaftar);
	
	void editPenduduk(PendudukModel pendaftar);
	
	void addKeluarga(KeluargaModel2 pendaftar);
	
	KeluargaModel2 selectKeluarga2(String nomor_kk);
	
	void editKeluarga(KeluargaModel2 keluarga);
	
	
	List<KelurahanModel> selectAllKelurahan();
	
	KelurahanModel selectKelurahan(int id);
	
	int selectPendaftarPenduduk(String tanggal);
	
	int selectPendaftarKeluarga(String tanggal);
}
