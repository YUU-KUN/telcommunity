package telcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import telcommunity.model.Group;
import telcommunity.model.User;
import telcommunity.model.UserClassChannel;
import telcommunity.model.UserOrmawaChannel;
import telcommunity.repository.UserRepository;
import telcommunity.service.ChannelService;
import telcommunity.service.GroupService;
import telcommunity.service.UserClassChannelService;
import telcommunity.service.UserOrmawaChannelService;

@Controller
class MainController {
    @Autowired
    GroupService groupService;

    @Autowired
    UserClassChannelService userClassChannelService;

    @Autowired
    UserOrmawaChannelService userOrmawaChannelService;

    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/")
    public String home(Model model) {
        try {
            List<Group> groups = groupService.getAllGroup();
            List<UserClassChannel> userClassChannels = userClassChannelService.getUserClassChannels();
            List<UserOrmawaChannel> userOrmawaChannels = userOrmawaChannelService.getUserOrmawaChannels();
            model.addAttribute("userClassChannels", userClassChannels);
            model.addAttribute("userOrmawaChannels", userOrmawaChannels);
            model.addAttribute("groups", groups);
    
            // get user loggedin data
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            model.addAttribute("user", user);
    
            // UserClassChannel userClassChannels = new UserClassChannel();
            // userClassChannels.getClassChannel().getClass_name()
    
            // UserOrmawaChannel userOrmawaChannel = new UserOrmawaChannel();
            // userOrmawaChannel.getOrmawaChannel().getOrmawa().getLogo()
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
