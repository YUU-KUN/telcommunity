package telcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telcommunity.model.KetuaOrmawa;
import telcommunity.model.Ormawa;
import telcommunity.model.OrmawaChannel;
import telcommunity.model.User;
import telcommunity.repository.KetuaOrmawaRepository;
import telcommunity.repository.OrmawaChannelRepository;
import telcommunity.repository.OrmawaRepository;

@Service
public class OrmawaService {
    @Autowired 
    OrmawaRepository ormawaRepository;

    @Autowired 
    KetuaOrmawaRepository ketuaOrmawaRepository;

    @Autowired
    OrmawaChannelRepository ormawaChannelRepository;
    
    public void initOrmawa() {
        try {
            Ormawa ormawa = new Ormawa();
            ormawa.setOrmawa_name("AL-FATH");
            ormawa.setLogo("/assets/img/channels/alfath.png");
            ormawaRepository.save(ormawa);
    
            OrmawaChannel ormawaChannel = new OrmawaChannel();
            ormawaChannel.setOrmawa(ormawa);
            ormawaChannel.setChannel_name(ormawa.getOrmawa_name());
            ormawaChannelRepository.save(ormawaChannel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Ormawa> getAllOrmawa() {
        List<Ormawa> ormawas = new ArrayList<Ormawa>();
        ormawaRepository.findAll().forEach(ormawa -> ormawas.add(ormawa));
        return ormawas;
    }

    public void requestKetuaOrmawa(Ormawa ormawa, User user) {
        try {
            KetuaOrmawa ketuaOrmawa = new KetuaOrmawa();
            ketuaOrmawa.setOrmawa(ormawa);
            ketuaOrmawa.setUser(user);
            ketuaOrmawa.setStatus("PENDING");
            ketuaOrmawaRepository.save(ketuaOrmawa);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<KetuaOrmawa> getRequestKetuaOrmawas() {
        List<KetuaOrmawa> listKetua = new ArrayList<KetuaOrmawa>();

        ketuaOrmawaRepository.findAll().forEach(ketua -> {
            if (ketua.getStatus().equals("PENDING")) {
                listKetua.add(ketua);
            }
        });

        return listKetua;
    }
}
