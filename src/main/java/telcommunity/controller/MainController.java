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
import telcommunity.model.User;
import telcommunity.model.UserChat;
import telcommunity.model.UserClassChannel;
import telcommunity.model.UserOrmawaChannel;
import telcommunity.repository.UserRepository;
import telcommunity.service.ChannelService;
import telcommunity.service.GroupService;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User authenticatedUser = userRepository.findByUsername(username);

        model.addAttribute("user", authenticatedUser);
        return "helpdesk";
    }

    @PostMapping("/helpdesk")
    public String helpdeskPost(@RequestParam(name = "role", defaultValue = "defaultType") String role,
            @RequestParam(name = "ormawa_name", defaultValue = "defaultType") String ormawa_name,
            @RequestParam(name = "category", defaultValue = "defaultType") String caategory,
            Model model) {
        System.out.println(ormawa_name);
        System.out.println(caategory);

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
