package telcommunity.service;

import java.util.ArrayList;
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
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserChatRepository userChatRepository;

    public List<User> getContacts() {
        try {
            // get user loggedin data
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);

            List<User> contacts = new ArrayList<User>();
            userRepository.findAll().forEach(contact -> {
                if (!contact.getId().equals(user.getId())) {
                    contacts.add(contact);
                }
            });
            return contacts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(String user_id) {
        try {
            return userRepository.findById(user_id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserChat> getUserChats(String receiver_id) { //receiver_id
        try {
            return userRepository.findById(receiver_id).get().getUserChats();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addUserChat(UserChat userChat) {
        try {
            userChatRepository.save(userChat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
