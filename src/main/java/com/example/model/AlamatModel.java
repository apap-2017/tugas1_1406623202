package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlamatModel
{
	private String alamat;
	private String rt;
    private String rw;
    private String kelurahan;

    private String kecamatan;
    private String kota;
    
    private int id_kelurahan;
    private int id_kecamatan;
    private int id_kota;
    
    private String kode_kecamatan;
    
}
