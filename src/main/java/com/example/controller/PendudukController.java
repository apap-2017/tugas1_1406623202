package com.example.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.AlamatModel;
import com.example.model.KecamatanModel;
import com.example.model.KeluargaModel;
import com.example.model.KeluargaModel2;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;
import com.example.service.PendudukService;

@Controller
public class PendudukController {

    @Autowired
    PendudukService pendudukDAO;


    @RequestMapping("/")
    public String index () {
    	
        return "index";
    }


    @RequestMapping("/penduduk")
    public String selectPenduduk (Model model, @RequestParam(value = "nik") String nik, HttpServletRequest request,
			HttpServletResponse response) {
    	
        PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);

      //  if (penduduk != null) {
        
        
        model.addAttribute ("penduduk", penduduk);
        
        
        return "penduduk";
        
//        } else {
//            model.addAttribute ("nik", nik);
//            return "not-found";
//        }
    }
    
    @RequestMapping("/keluarga")
    public String selectKeluarga (Model model, @RequestParam(value = "nomor_kk") String nomor_kk, HttpServletRequest request,
			HttpServletResponse response) {
    	
        KeluargaModel keluarga = pendudukDAO.selectKeluarga (nomor_kk);

      //  if (penduduk != null) {
        
        model.addAttribute ("keluarga", keluarga);

        
        
        return "keluarga";
        
//        } else {
//            model.addAttribute ("nik", nik);
//            return "not-found";
//        }
    }
    
    @RequestMapping("/kelurahan")
    public String selectKelurahan (Model model, @RequestParam(value = "nomor_kk") String nomor_kk, HttpServletRequest request,
			HttpServletResponse response) {
    	
        KeluargaModel keluarga = pendudukDAO.selectKeluarga (nomor_kk);

      //  if (penduduk != null) {
        
        model.addAttribute ("keluarga", keluarga);

        
        
        return "keluarga";
        
//        } else {
//            model.addAttribute ("nik", nik);
//            return "not-found";
//        }
    }
    
    @RequestMapping("/penduduk/add")
    public String addPenduduk () {
    	
        return "add_penduduk";
    }
    
	@RequestMapping("/penduduk/add/submit")
	public String addPendudukSubmit(Model model, 
			@RequestParam(value="nama", required = false) String nama,
			@RequestParam(value="jenis_kelamin", required = false) int jenis_kelamin,
			@RequestParam(value="tempat_lahir", required = false) String tempat_lahir,
			@RequestParam(value="tanggal_lahir", required = false) String tanggal_lahir,
			@RequestParam(value="goldar", required = false) String goldar,
			@RequestParam(value="agama", required = false) String agama,
			@RequestParam(value="is_married", required = false) String is_married,
			@RequestParam(value="pekerjaan", required = false) String pekerjaan,
			@RequestParam(value="kewarnegaraan", required = false) int kewarnegaraan,
			@RequestParam(value="status_keluarga", required = false) String status_keluarga,
			@RequestParam(value="has_died", required = false) int has_died,
			@RequestParam(value="id_keluarga", required = false) int id_keluarga) throws ParseException
	{
		AlamatModel alamat = pendudukDAO.selectAlamat(id_keluarga);
        
		tanggal_lahir = tanggal_lahir.replaceAll("-", "").substring(2);
		
		int id = pendudukDAO.selectPendaftarPenduduk(tanggal_lahir) + 1;
		
		String nik = alamat.getKode_kecamatan() + tanggal_lahir + id;
		
		
		PendudukModel pendaftar = new PendudukModel (0, nik, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, id_keluarga, alamat, goldar, agama, is_married, status_keluarga, pekerjaan, kewarnegaraan, has_died);
		pendudukDAO.addPenduduk(pendaftar);
		
		model.addAttribute("pendaftar", pendaftar);
		
		return "redirect:/penduduk?nik=" + nik;
			
	}
	
	
	
    @RequestMapping("/keluarga/add")
    public String addKeluarga (Model model) {
    	List<KelurahanModel> kelurahan = pendudukDAO.selectAllKelurahan();
    	
    	model.addAttribute ("kelurahan", kelurahan);
    	
        return "add_keluarga";
    }

    
    @RequestMapping("/keluarga/add/submit")
    public String addKeluargaSubmit (Model model,
			@RequestParam(value="alamat", required = false) String alamat,
			@RequestParam(value="rt", required = false) String rt,
			@RequestParam(value="rw", required = false) String rw,
    	@RequestParam(value="id_kelurahan", required = false) int id_kelurahan) throws ParseException {
    	
    	KelurahanModel kelurahan = pendudukDAO.selectKelurahan(id_kelurahan);
    	
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        
        String currentDate = dateFormat.format(date).replaceAll("/", "");
        
        int id = pendudukDAO.selectPendaftarKeluarga(currentDate) + 1;
        
    	String nomor_kk= kelurahan.getKode_kelurahan().substring(0,6) + currentDate + id;
    	
    	KeluargaModel2 pendaftar = new KeluargaModel2 (0, nomor_kk, alamat, rt, rw, id_kelurahan, 0);
    	
    	pendudukDAO.addKeluarga(pendaftar);
        return "add_keluarga_success";
    }
    
    @RequestMapping("/keluarga/edit/{nik}")
    public String editKeluarga (Model model, @PathVariable(value = "nomor_kk") String nomor_kk) {
    	List<KelurahanModel> kelurahan = pendudukDAO.selectAllKelurahan();
    	
    	KeluargaModel2 keluarga = pendudukDAO.selectKeluarga2(nomor_kk);
    	
    	model.addAttribute ("keluarga", keluarga);
    	
        return "add_keluarga";
    }
    
    @RequestMapping("/keluarga/edit/submit")
    public String editKeluargaSubmit (Model model,
			@RequestParam(value="alamat", required = false) String alamat,
			@RequestParam(value="rt", required = false) String rt,
			@RequestParam(value="rw", required = false) String rw,
    	@RequestParam(value="id_kelurahan", required = false) int id_kelurahan) throws ParseException {
    	
    	KelurahanModel kelurahan = pendudukDAO.selectKelurahan(id_kelurahan);
    	
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        
        String currentDate = dateFormat.format(date).replaceAll("/", "");
        
        int id = pendudukDAO.selectPendaftarKeluarga(currentDate) + 1;
        
    	String nomor_kk= kelurahan.getKode_kelurahan().substring(0,6) + currentDate + id;
    	
    	KeluargaModel2 pendaftar = new KeluargaModel2 (0, nomor_kk, alamat, rt, rw, id_kelurahan, 0);
    	
    	pendudukDAO.addKeluarga(pendaftar);
        return "redirect:/keluarga?nomor_kk=" + nomor_kk;
    }
    
    @RequestMapping("/penduduk/edit/{nik}")
    public String editPenduduk (Model model, @PathVariable(value = "nik") String nik) {
    	
    	PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
    	model.addAttribute("penduduk", penduduk);
        
        return "edit_penduduk";
    }
    
    @RequestMapping("/penduduk/edit/submit")
    public String editPendudukSubmit (Model model, 
			@RequestParam(value="nama", required = false) String nama,
			@RequestParam(value="jenis_kelamin", required = false) int jenis_kelamin,
			@RequestParam(value="tempat_lahir", required = false) String tempat_lahir,
			@RequestParam(value="tanggal_lahir", required = false) String tanggal_lahir,
			@RequestParam(value="goldar", required = false) String goldar,
			@RequestParam(value="agama", required = false) String agama,
			@RequestParam(value="is_married", required = false) String is_married,
			@RequestParam(value="pekerjaan", required = false) String pekerjaan,
			@RequestParam(value="kewarnegaraan", required = false) int kewarnegaraan,
			@RequestParam(value="status_keluarga", required = false) String status_keluarga,
			@RequestParam(value="has_died", required = false) int has_died,
			@RequestParam(value="id_keluarga", required = false) int id_keluarga) throws ParseException {
    	
    	AlamatModel alamat = pendudukDAO.selectAlamat(id_keluarga);
        
		tanggal_lahir = tanggal_lahir.replaceAll("-", "").substring(2);
		
		int id = pendudukDAO.selectPendaftarPenduduk(tanggal_lahir) + 1;
		
		String nik = alamat.getKode_kecamatan() + tanggal_lahir + id;
		
		
		
		PendudukModel pendaftar = new PendudukModel (0, nik, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, id_keluarga, alamat, goldar, agama, is_married, status_keluarga, pekerjaan, kewarnegaraan, has_died);
		pendudukDAO.addPenduduk(pendaftar);
		
		model.addAttribute("pendaftr", pendaftar);
		
        return "redirect:/penduduk?nik=" + nik;
    }
}