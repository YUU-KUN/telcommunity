package telcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import telcommunity.model.ClassChannel;
import telcommunity.model.Dosen;
import telcommunity.model.User;
import telcommunity.service.ChannelService;
import telcommunity.service.CustomUserDetailsService;
import telcommunity.service.DosenService;

@Controller
public class DosenController {
    @Autowired
    DosenService dosenService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    ChannelService channelService;

    @GetMapping("init-dosen")
    public void initDosen() {
        User user = new User();
        user.setName("Dosen Indra");
        user.setUsername("dosenindraaa");
        user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
        customUserDetailsService.addUser(user);
        
        Dosen dosen = new Dosen();
        dosen.setUser(user);
        dosen.setNip("1301213133");
        dosen.setKode("DRA");
        dosenService.saveOrUpdate(dosen);

        ClassChannel classChannel = new ClassChannel();
        classChannel.setClass_name(null);
        classChannel.setLogo("/assets/img/groups/telu.png");
        channelService.addClassChannel(classChannel);

    }
    
}
