package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeluargaModel2
{
	private int id;
    private String nomor_kk;
    private String alamat;
    private String rt;
    private String rw;
    private int id_kelurahan;
    private int is_tidak_berlaku;
}
