package telcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import telcommunity.model.ClassChannel;
import telcommunity.model.Group;
import telcommunity.model.Ormawa;
import telcommunity.model.User;
import telcommunity.model.UserChat;
import telcommunity.model.UserClassChannel;
import telcommunity.model.UserOrmawaChannel;
import telcommunity.repository.UserRepository;
import telcommunity.service.ChannelService;
import telcommunity.service.GroupService;
import telcommunity.service.OrmawaService;
import telcommunity.service.UserChatService;
import telcommunity.service.UserClassChannelService;
import telcommunity.service.UserOrmawaChannelService;

@Controller
class MainController {
    @Autowired
    GroupService groupService;

    @Autowired
    UserChatService userChatService;

    @Autowired
    ChannelService channelService;

    @Autowired
    OrmawaService ormawaService;

    @Autowired
    UserClassChannelService userClassChannelService;

    @Autowired
    UserOrmawaChannelService userOrmawaChannelService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        try {
            // get user loggedin data
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            model.addAttribute("user", user);

            List<Group> groups = groupService.getAllGroup();
            List<UserOrmawaChannel> userOrmawaChannels = userOrmawaChannelService.getUserOrmawaChannels();
            if ("MAHASISWA".equals(user.getRole())) {
                List<UserClassChannel> userClassChannels = userClassChannelService.getUserClassChannels();
                model.addAttribute("userClassChannels", userClassChannels);

                List<UserChat> recentChats = userChatService.getRecentChats();
                model.addAttribute("recentChats", recentChats);

            } else if ("DOSEN".equals(user.getRole())) {
                List<ClassChannel> dosenClassChannels = channelService.getDosenClassChannels();
                model.addAttribute("dosenClassChannels", dosenClassChannels);

                List<UserChat> recentChats = userChatService.getRecentChats();
                model.addAttribute("recentChats", recentChats);
            }
            model.addAttribute("userOrmawaChannels", userOrmawaChannels);
            model.addAttribute("groups", groups);

            return "home";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login?role=mahasiswa";
        }
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/helpdesk")
    public String helpdesk(Model model) {
        List<Ormawa> ormawas = ormawaService.getAllOrmawa();
        model.addAttribute("ormawas", ormawas);
        return "helpdesk";
    }

    @PostMapping("/helpdesk")
    public String helpdeskPost(
            @RequestParam(name = "ormawa", defaultValue = "defaultType") Ormawa ormawa,
            Model model) {

        // Get Authenticated User
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        ormawaService.requestKetuaOrmawa(ormawa, user);

        // if ("MAHASISWA".equals(role)) {
        // return "redirect:/login?role=mahasiswa";
        // }
        // else if ("dosen".equals(role)) {
        // return "redirect:/login?role=dosen";
        // }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
