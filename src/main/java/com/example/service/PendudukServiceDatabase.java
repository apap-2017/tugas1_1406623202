package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.AlamatModel;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KeluargaModel2;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService
{
    @Autowired
    private PendudukMapper pendudukMapper;


    @Override
    public PendudukModel selectPenduduk (String nik)
    {
        log.info ("select penduduk with nik {}", nik);
        return pendudukMapper.selectPenduduk (nik);
    }
    
    @Override
    public void addPenduduk(PendudukModel pendaftar)
    {
        log.info ("tambah penduduk with nik {}", pendaftar.getNik());
        pendudukMapper.addPenduduk (pendaftar);
    }
    
    @Override
    public void editPenduduk(PendudukModel pendaftar)
    {
        log.info ("tambah penduduk with nik {}", pendaftar.getNik());
        pendudukMapper.editPenduduk (pendaftar);
    }
    
    @Override
    public AlamatModel selectAlamat(int id_keluarga)
    {
        log.info ("select alamat with id_keluarga {}", id_keluarga);
        return pendudukMapper.selectAlamat (id_keluarga);
    }
    
    @Override
    public KeluargaModel selectKeluarga (String nomor_kk)
    {
        log.info ("select keluarga with nomor kk {}", nomor_kk);
        return pendudukMapper.selectKeluarga (nomor_kk);
    }
    
	public void addKeluarga(KeluargaModel2 pendaftar) {
		log.info ("add keluarga with nomor_kk {}", pendaftar.getNomor_kk());
		pendudukMapper.addKeluarga(pendaftar);
	}
    
    
    @Override
    public List<KelurahanModel> selectAllKelurahan ()
    {
        log.info ("select kelurahan");
        return pendudukMapper.selectAllKelurahan();
    }
    
    @Override
    public KelurahanModel selectKelurahan (int id)
    {
        log.info ("select kelurahan");
        return pendudukMapper.selectKelurahan(id);
    }
    
    @Override
    public int selectPendaftarPenduduk (String tanggal)
    {
        log.info ("select id terbesar");
        return pendudukMapper.selectPendaftarPenduduk(tanggal);
    }
    
    @Override
	public KeluargaModel2 selectKeluarga2(String nomor_kk) {
    	log.info ("select keluarga nomor_kk {}", nomor_kk);
    	return pendudukMapper.selectKeluarga2(nomor_kk);
    }
	
    @Override
	public void editKeluarga(KeluargaModel2 pendaftar) {
    	log.info ("select keluarga nomor_kk {}", pendaftar);
    	pendudukMapper.editKeluarga(pendaftar);
    }
    
    @Override
    public int selectPendaftarKeluarga (String tanggal)
    {
        log.info ("select id terbesar");
        return pendudukMapper.selectPendaftarKeluarga(tanggal);
    }
    
}
