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
import telcommunity.service.DosenService;
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

    @Autowired
    DosenService dosenService;

    @GetMapping("/channel")
    public String channel(@RequestParam(name = "type", defaultValue = "defaultType") String type, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User authenticatedUser = userRepository.findByUsername(username);

        model.addAttribute("classChannels", channelService.getClassChannels());
        model.addAttribute("ormawaChannels", channelService.getOrmawaChannels());
        model.addAttribute("user", authenticatedUser);

        return "/channel";
    }

    @GetMapping("/customize-channel")
    public String customizeChannel(@RequestParam(name = "is_allow_all", defaultValue = "false") String is_allow_all,
            Model model) {
        return "channel_add";
    }

    @GetMapping("/add-class")
    public String redirectToAddClass(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User authenticatedUser = userRepository.findByUsername(username);

        if (authenticatedUser.getRole().equals("MAHASISWA")) {
            return "redirect:channel?type=class";
        } else {
            // System.out.println(authenticatedUser.getName());
            // model.addAttribute("user", authenticatedUser);
            return "redirect:customize-channel?is_allow_all=true";
        }
    }

    @GetMapping("/add-ormawa")
    public String redirectToAddOrmawa() {
        return "redirect:channel?type=ormawa";
    }

    @PostMapping("/add-class")
    public String addClass(@RequestParam(name = "channel_name") String channel_name) {
        System.out.println(channel_name);
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User authenticatedUser = userRepository.findByUsername(username);
    
            ClassChannel classChannel = new ClassChannel();
            classChannel.setClass_name(channel_name);
            if ("".equals(channel_name)) {
                classChannel.setClass_name("New Class");
            }
            classChannel.setDosen(authenticatedUser.getDosen());
            classChannel.setLogo("/assets/img/groups/telu.png");
            channelService.addClassChannel(classChannel);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @GetMapping("/join-class")
    public String joinClass(@RequestParam(name = "class_id") String class_id) {
        System.out.println(class_id);
        ClassChannel classChannel = channelService.getClassChannelById(class_id);

        UserClassChannel userClassChannel = new UserClassChannel();
        userClassChannel.setClassChannel(classChannel);

        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        userClassChannel.setUser(user);

        userClassChannelService.joinClassChannel(userClassChannel);

        return "redirect:/";
    }

    @GetMapping("/join-ormawa")
    public String joinOrmawa(@RequestParam(name = "ormawa_id") String ormawa_id) {
        System.out.println(ormawa_id);
        OrmawaChannel ormawaChannel = channelService.getOrmawaChannelById(ormawa_id);

        UserOrmawaChannel userOrmawaChannel = new UserOrmawaChannel();
        userOrmawaChannel.setOrmawaChannel(ormawaChannel);

        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        userOrmawaChannel.setUser(user);

        userOrmawaChannelService.joinOrmawaChannel(userOrmawaChannel);
        return "redirect:/";
    }
}
