package telcommunity.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import telcommunity.model.ChatMessage;
import telcommunity.model.ClassChannel;
import telcommunity.model.ClassChannelChat;
import telcommunity.model.Group;
import telcommunity.model.GroupChat;
import telcommunity.model.OrmawaChannel;
import telcommunity.model.OrmawaChannelChat;
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
import telcommunity.service.UserService;

@Controller
public class ChatController {
    @Autowired
    ChannelService channelService;

    @Autowired
    GroupService groupService;

    @Autowired
    UserClassChannelService userClassChannelService;

    @Autowired
    UserOrmawaChannelService userOrmawaChannelService;

    @Autowired
    UserService userService;
    
    @Autowired
    UserChatService userChatService;

    @Autowired
    UserRepository userRepository;

    private User authenticatedUser;

    public ChatController() {
        try {
            // get user loggedin data
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            authenticatedUser = userRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/chat")
    public String chat(
            @RequestParam(name = "group_id", required = false) String groupId,
            @RequestParam(name = "ormawa_channel_id", required = false) String ormawaChannelId,
            @RequestParam(name = "class_channel_id", required = false) String classChannelId,
            @RequestParam(name = "user_id", required = false) String userId,
            Model model) {

        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        authenticatedUser = userRepository.findByUsername(username);

        model.addAttribute("user", authenticatedUser);

        // Groups
        List<Group> groups = groupService.getAllGroup();
        model.addAttribute("groups", groups);

        // Channels
        // Mahasiswa
        List<UserClassChannel> userClassChannels = userClassChannelService.getUserClassChannels();
        model.addAttribute("userClassChannels", userClassChannels);
        // Dosen
        List<ClassChannel> dosenClassChannels = channelService.getDosenClassChannels();
        model.addAttribute("dosenClassChannels", dosenClassChannels);

        // Ormawa
        List<UserOrmawaChannel> userOrmawaChannels = userOrmawaChannelService.getUserOrmawaChannels();
        model.addAttribute("userOrmawaChannels", userOrmawaChannels);

        // Personal
        List<User> contacts = userService.getContacts();
        model.addAttribute("userContacts", contacts);
        // User contact = new User();
        // contact.getName()

        if (classChannelId != null) {
            ClassChannel classChannel = channelService.getClassChannelById(classChannelId);
            model.addAttribute("chatHeaderName", classChannel.getClass_name());
            model.addAttribute("chatHeaderLogo", classChannel.getLogo());
            List<ClassChannelChat> classChannelChats = channelService.getClassChannelChats(classChannelId);
            model.addAttribute("classChannelChats", classChannelChats);
        } else if (groupId != null) {
            Group group = groupService.getGroupById(groupId);
            model.addAttribute("chatHeaderName", group.getGroup_name());
            model.addAttribute("chatHeaderLogo", group.getLogo());
            List<GroupChat> groupChats = groupService.getGroupChats(groupId);
            GroupChat GroupChat = new GroupChat();
            GroupChat.getMessage();
            model.addAttribute("groupChats", groupChats);
        } else if (ormawaChannelId != null) {
            OrmawaChannel ormawaChannel = channelService.getOrmawaChannelById(ormawaChannelId);
            model.addAttribute("chatHeaderName", ormawaChannel.getChannel_name());
            model.addAttribute("chatHeaderLogo", ormawaChannel.getOrmawa().getLogo());
            List<OrmawaChannelChat> ormawaChannelChats = channelService.getOrmawaChannelChats(ormawaChannelId);
            model.addAttribute("ormawaChannelChats", ormawaChannelChats);
        } else if (userId != null) { //receiver_id
            User receiver = userService.getUserById(userId);
            model.addAttribute("chatHeaderName", receiver.getName());
            model.addAttribute("chatHeaderLogo", "/assets/img/nav-profile.png"); //TODO: Change to user profile
            List<UserChat> personalChats = userChatService.getUserChats(userId);

            model.addAttribute("personalChats", personalChats);
        }

        return "chat";
    }

    // For Dosen
    @PostMapping("/chat")
    public String chatPost(@RequestParam(name = "message", required = true) String message,
            @RequestParam(name = "user_id", required = true) String userId,
            Model model) {

        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        authenticatedUser = userRepository.findByUsername(username);

        if (message != null && !message.isEmpty()) {

            // Personal Chat
            if (userId != null) {
                User user = userService.getUserById(userId);
                UserChat userChat = new UserChat();
                userChat.setMessage(message);
                userChat.setReceiver(user);
                userChat.setSender(authenticatedUser);
                userChat.setCreatedAt(LocalDateTime.now());
                userService.addUserChat(userChat);
                return "redirect:/chat?user_id=" + userId;
            }
        }
        return "chat";
    }

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ClassChannelChat register(@Payload ClassChannelChat classChannelChat,
            SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username",
                classChannelChat.getClassChannel().getDosen().getUser().getName());
        return classChannelChat;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ClassChannelChat sendMessage(@Payload ClassChannelChat classChannelChat) {

        ClassChannel classChannel = new ClassChannel();
        classChannel.setId("822bd189-ca6f-4886-b04f-73de1c554554");
        classChannelChat.setClassChannel(classChannel);
        return classChannelChat;
    }

}
