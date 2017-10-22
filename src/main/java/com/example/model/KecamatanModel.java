package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel
{
	private int id;
    private int id_kota;  
    private String kode_kecamatan;
    private String nama_kecamatan;
}
