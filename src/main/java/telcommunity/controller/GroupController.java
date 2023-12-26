package telcommunity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import telcommunity.model.Group;
import telcommunity.service.GroupService;

@Controller
public class GroupController {
    @Autowired
    GroupService groupService;

    @GetMapping("init-group")
    public String initGroup() {
        Group group = new Group();
        group.setGroup_name("Telkom University");
        group.setLogo("/assets/img/groups/telu.png");
        groupService.saveOrUpdate(group);

        Group group2 = new Group();
        group2.setGroup_name("Fakultas Informatika");
        group2.setLogo("/assets/img/groups/himaif.png");
        groupService.saveOrUpdate(group2);

        Group group3 = new Group();
        group3.setGroup_name("S1 Informatika");
        group3.setLogo("/assets/img/groups/himaif.png");
        groupService.saveOrUpdate(group3);

        return "home";
    }
}
