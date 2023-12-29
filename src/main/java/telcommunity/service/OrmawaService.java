package telcommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telcommunity.model.Ormawa;
import telcommunity.model.OrmawaChannel;
import telcommunity.repository.OrmawaChannelRepository;
import telcommunity.repository.OrmawaRepository;

@Service
public class OrmawaService {
    @Autowired 
    OrmawaRepository ormawaRepository;

    @Autowired
    OrmawaChannelRepository ormawaChannelRepository;
    
    public void initOrmawa() {
        Ormawa ormawa = new Ormawa();
        ormawa.setOrmawa_name("AL-FATH");
        ormawa.setLogo("/assets/img/channels/alfath.png");
        ormawaRepository.save(ormawa);

        OrmawaChannel ormawaChannel = new OrmawaChannel();
        ormawaChannel.setOrmawa(ormawa);
        ormawaChannel.setChannel_name(ormawa.getOrmawa_name());
        ormawaChannelRepository.save(ormawaChannel);
    }
}
