package telcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telcommunity.model.Dosen;
import telcommunity.repository.DosenRepository;

@Service
public class DosenService {
    @Autowired
    DosenRepository dosenRepository;

    public void saveOrUpdate(Dosen dosen) {
        try {
            dosenRepository.save(dosen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
