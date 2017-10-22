package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel
{
	private int id;
    private String nik;
    private String nama;
    private int jenis_kelamin;
    
    private String tempat_lahir;
    private String tanggal_lahir;
    
    private int id_keluarga;
    private AlamatModel alamat;
    
    private String goldar;
    private String agama;
    private String is_married;
    private String status_keluarga;
    private String pekerjaan;
    private int kewarnegaraan;
    
    private int has_died;
}
