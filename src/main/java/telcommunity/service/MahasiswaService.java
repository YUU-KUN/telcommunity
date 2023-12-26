package telcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telcommunity.model.Mahasiswa;
import telcommunity.repository.MahasiswaRepository;

@Service
public class MahasiswaService {
    @Autowired
    MahasiswaRepository mahasiswaRepository;

    public List<Mahasiswa> getAllMahasiswa() {
        try {
            List<Mahasiswa> mahasiswas = new ArrayList<Mahasiswa>();
            mahasiswaRepository.findAll().forEach(mahasiswa -> mahasiswas.add(mahasiswa));
            return mahasiswas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Mahasiswa getMahasiswaById(String id) {
        try {
            return mahasiswaRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveOrUpdate(Mahasiswa mahasiswa) {
        try {
            mahasiswaRepository.save(mahasiswa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            mahasiswaRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
