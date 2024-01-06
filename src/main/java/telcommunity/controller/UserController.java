package telcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import telcommunity.model.Mahasiswa;
import telcommunity.model.User;
import telcommunity.service.CustomUserDetailsService;
import telcommunity.service.MahasiswaService;

@Controller
public class UserController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    MahasiswaService mahasiswaService;


    @GetMapping("init-user")
    public String initUser() {
        User user = new User();
        user.setName("Indra Wahyu");
        user.setUsername("indraaa");
        user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
        customUserDetailsService.addUser(user);

        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setUser(user);
        mahasiswa.setNim("1301213135");
        mahasiswa.setAngkatan("21");
        mahasiswa.setFakultas("Informatika");
        mahasiswa.setJurusan("S1 Informatika");
        mahasiswa.setKelas("IF-45-05");
        mahasiswaService.saveOrUpdate(mahasiswa);

        return "home";
    }
}
