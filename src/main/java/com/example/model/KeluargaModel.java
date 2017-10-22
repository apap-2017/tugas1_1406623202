package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel
{
	private String nomor_kk;
    private AlamatModel alamat;
    
    private List<PendudukModel> anggota_keluarga;
}
