package telcommunity.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import telcommunity.model.User;
import telcommunity.model.UserChat;
import telcommunity.repository.UserChatRepository;
import telcommunity.repository.UserRepository;

@Service
public class UserChatService {
    @Autowired
    UserChatRepository userChatRepository;

    @Autowired
    UserRepository userRepository;

    public List<UserChat> getUserChats(String receiver_id) {
        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        List<UserChat> userChats = new ArrayList<UserChat>();

        userChatRepository.findAll().forEach(userchat -> {
            boolean condition_1 = userchat.getReceiver().getId().equals(user.getId()) && userchat.getSender().getId().equals(receiver_id);
            boolean condition_2 = userchat.getReceiver().getId().equals(receiver_id) && userchat.getSender().getId().equals(user.getId());
            if (condition_1 || condition_2) {
                userChats.add(userchat);
            }
        });
        
        userChats.sort(Comparator.comparing(UserChat::getCreatedAt));
        return userChats;
    }
}
