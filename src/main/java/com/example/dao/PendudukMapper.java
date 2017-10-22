package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;

import com.example.model.AlamatModel;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KeluargaModel2;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper
{
    @Select("select * from penduduk where nik = #{nik}")
	@Results(value = {
		@Result(property="id", column="id"),
		@Result(property="nik", column="nik"),
		@Result(property="nama", column="nama"),
		@Result(property="tempat_lahir", column="tempat_lahir"),
		@Result(property="tanggal_lahir", column="tanggal_lahir"),
		@Result(property="goldar", column="golongan_darah"),
		@Result(property="agama", column="agama"),
		@Result(property="is_married", column="status_perkawinan"),
		@Result(property="pekerjaan", column="pekerjaan"),
		
		@Result(property="kewarnegaraan", column="is_wni"),
		@Result(property="has_died", column="is_wafat"),
		@Result(property="id_keluarga", column="id_keluarga"),
		@Result(property="alamat", column="id_keluarga", javaType=AlamatModel.class, one = @One(select="selectAlamat"))
	})
    PendudukModel selectPenduduk (@Param("nik") String nik);
    

    @Select("select alamat, rt, rw, nama_kelurahan, nama_kecamatan, nama_kota, kode_kecamatan "
    		+ "from keluarga join kelurahan on id_kelurahan = kelurahan.id "
    		+ "join kecamatan on id_kecamatan = kecamatan.id "
    		+ "join kota on id_kota = kota.id "
    		+ "where keluarga.id = #{id_keluarga}")
	@Results(value = {
		@Result(property="alamat", column="alamat"),
		@Result(property="rt", column="rt"),
		@Result(property="rw", column="rw"),
		@Result(property="kelurahan", column="nama_kelurahan"),
		@Result(property="kecamatan", column="nama_kecamatan"),
		@Result(property="kota", column="nama_kota"),
		@Result(property="kode_kecamatan", column="kode_kecamatan"),
	})
    AlamatModel selectAlamat (@Param("id_keluarga") int id_keluarga);
    
    @Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	@Results(value = {
		@Result(property="nomor_kk", column="nomor_kk"),
		
		@Result(property="alamat", column="id", javaType=AlamatModel.class, one = @One(select="selectAlamat")),
		@Result(property="anggota_keluarga", column="id", javaType=List.class, many = @Many(select="selectAnggotaKeluarga"))
	})
    KeluargaModel selectKeluarga (@Param("nomor_kk") String nomor_kk);
    
    @Select("select * from penduduk where id_keluarga = #{id_keluarga}")
	@Results(value = {
		@Result(property="id", column="id"),
		@Result(property="nik", column="nik"),
		@Result(property="nama", column="nama"),
		@Result(property="tempat_lahir", column="tempat_lahir"),
		@Result(property="tanggal_lahir", column="tanggal_lahir"),
		@Result(property="jenis_kelamin", column="jenis_kelamin"),
		@Result(property="goldar", column="golongan_darah"),
		@Result(property="agama", column="agama"),
		@Result(property="is_married", column="status_perkawinan"),
		@Result(property="status_keluarga", column="status_dalam_keluarga"),
		@Result(property="pekerjaan", column="pekerjaan"),
		
		@Result(property="kewarnegaraan", column="is_wni"),
		@Result(property="has_died", column="is_wafat")
	})
    List<PendudukModel> selectAnggotaKeluarga (@Param("id_keluarga") String id_keluarga);

    @Insert ("insert into penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, "
    		+ "golongan_darah, is_wafat) values (#{nik}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{kewarnegaraan}, #{id_keluarga}, #{agama}, #{pekerjaan}, #{is_married}, #{status_keluarga}, " 
    		+ " #{goldar}, #{has_died})")
	void addPenduduk(PendudukModel pendaftar);
    
    @Update ("update penduduk set (nik=#{nik}, nama=#{nama}, tempat_lahir=#{tempat_lahir}, tanggal_lahir=#{tanggal_lahir}, jenis_kelamin=#{jenis_kelamin}, is_wni=#{kewarnegaraan}, "
    		+ "id_keluarga=#{id_keluarga}, agama=#{agama}, pekerjaan=#{pekerjaan}, status_perkawinan=#{is_married}, status_dalam_keluarga=#{status_keluarga} "
    		+ "golongan_darah=#{goldar}, is_wafat=#{has_died})")
	void editPenduduk(PendudukModel pendaftar);


    @Select("select * from kelurahan")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="id_kecamatan", column="id_kecamatan"),
    		@Result(property="kode_kelurahan", column="kode_kelurahan"),
    		@Result(property="nama_kelurahan", column="nama_kelurahan"),
    		@Result(property="kode_pos", column="kode_pos")
    	})
	List<KelurahanModel> selectAllKelurahan();
    
    @Select("select * from kelurahan where id = #{id_kelurahan}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="id_kecamatan", column="id_kecamatan"),
    		@Result(property="kode_kelurahan", column="kode_kelurahan"),
    		@Result(property="nama_kelurahan", column="nama_kelurahan"),
    		@Result(property="kode_pos", column="kode_pos")
    	})
	KelurahanModel selectKelurahan(@Param("id_kelurahan") int id_kelurahan);
    
    @Select ("select * from keluarga where nomor_kk = #{nomor_kk}")
	KeluargaModel2 selectKeluarga2(String nomor_kk);

    @Insert ("insert into keluarga (nomor_kk, alamat, RT, RW, id_kelurahan, is_tidak_berlaku)"
    		+ "values (#{nomor_kk}, #{alamat}, #{rt}, #{rw}, #{id_kelurahan}, 0)")
	void addKeluarga(KeluargaModel2 pendaftar);
    
    @Update ("update keluarga set nomor_kk=#{nomor_kk}, alamat=#{alamat}, RT#={rt}, RW=#{rw}, id_kelurahan=#{id_kelurahan}")
	void editKeluarga(KeluargaModel2 pendaftar);
    
    @Select("select count(nik) from penduduk where nik like CONCAT('%',${tanggal},'%')")
    int selectPendaftarPenduduk (@Param("tanggal") String tanggal);
    
    @Select("select count(nomor_kk) from keluarga where nomor_kk like CONCAT('%',${tanggal},'%')")
    int selectPendaftarKeluarga (@Param("tanggal") String tanggal);
}
