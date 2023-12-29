package telcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import telcommunity.model.ClassChannel;
import telcommunity.model.OrmawaChannel;
import telcommunity.model.User;
import telcommunity.model.UserClassChannel;
import telcommunity.model.UserOrmawaChannel;
import telcommunity.repository.UserRepository;
import telcommunity.service.ChannelService;
import telcommunity.service.UserClassChannelService;
import telcommunity.service.UserOrmawaChannelService;

@Controller
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @Autowired
    UserClassChannelService userClassChannelService;

    @Autowired
    UserOrmawaChannelService userOrmawaChannelService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/channel")
    public String channel(@RequestParam(name = "type", defaultValue = "defaultType") String type, Model model) {
        model.addAttribute("classChannels", channelService.getClassChannels());
        // ClassChannel classChannel = new ClassChannel();
        // classChannel.getLogo()
        model.addAttribute("ormawaChannels", channelService.getOrmawaChannels());
        return "/channel";
    }

    @GetMapping("/customize-channel")
    public String customizeChannel() {
        return "channel_add";
    }

    @GetMapping("/add-class")
    public String redirectToAddClass() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User authenticatedUser = userRepository.findByUsername(username);
        
        if (authenticatedUser.getRole().equals("MAHASISWA")) {
            return "redirect:channel?type=class";
        } else {
            return "redirect:customize-channel";
        }
    }

    @GetMapping("/add-ormawa")
    public String redirectToAddOrmawa() {
        return "redirect:channel?type=ormawa";
    }

    // @PostMapping("/add-class")
    // public String addClass(@RequestParam(name = "class_name") String class_name)
    // {
    // channelService.addClassChannel(class_name);
    // return "redirect:channel?type=class";
    // }

    @GetMapping("/join-class")
    public String joinClass(@RequestParam(name = "class_id") String class_id) {
        System.out.println(class_id);
        ClassChannel classChannel = channelService.getClassChannelById(class_id);

        UserClassChannel userClassChannel = new UserClassChannel();
        userClassChannel.setClassChannel(classChannel);

        // User user = new User();
        // user.setId("85833ba3-9f52-44bd-8045-6be9adc4e58a");

        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        userClassChannel.setUser(user);

        userClassChannelService.joinClassChannel(userClassChannel);

        return "home";
    }

    @GetMapping("/join-ormawa")
    public String joinOrmawa(@RequestParam(name = "ormawa_id") String ormawa_id) {
        System.out.println(ormawa_id);
        OrmawaChannel ormawaChannel = channelService.getOrmawaChannelById(ormawa_id);

        UserOrmawaChannel userOrmawaChannel = new UserOrmawaChannel();
        userOrmawaChannel.setOrmawaChannel(ormawaChannel);

        // User user = new User();
        // user.setId("85833ba3-9f52-44bd-8045-6be9adc4e58a");

        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        userOrmawaChannel.setUser(user);

        userOrmawaChannelService.joinOrmawaChannel(userOrmawaChannel);
        return "redirect:/";
    }
}
