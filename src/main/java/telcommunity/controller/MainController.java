package telcommunity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import telcommunity.model.Group;
import telcommunity.service.GroupService;

@Controller
class MainController {
    @Autowired
    GroupService groupService;
    
    @GetMapping("/")
    public String home(Model model) {
        List<Group> groups = groupService.getAllGroup();
        model.addAttribute("groups", groups);
        return "home";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/channel")
    public String channel() {
        return "channel";
    }
}
