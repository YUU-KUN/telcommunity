package telcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import telcommunity.model.User;
import telcommunity.model.UserClassChannel;
import telcommunity.model.UserOrmawaChannel;
import telcommunity.repository.UserOrmawaChannelRepository;
import telcommunity.repository.UserRepository;

@Service
public class UserOrmawaChannelService {
    @Autowired
    UserOrmawaChannelRepository userOrmawaChannelRepository;

    @Autowired
    UserRepository userRepository;

    public List<UserOrmawaChannel> getUserOrmawaChannels() {

        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        List<UserOrmawaChannel> userOrmawaChannels = new ArrayList<UserOrmawaChannel>();
        userOrmawaChannelRepository.findAll().forEach(UserOrmawaChannel -> {
            if (UserOrmawaChannel.getUser().getId().equals(user.getId())) {
                userOrmawaChannels.add(UserOrmawaChannel);
            }
        });
        return userOrmawaChannels;
    }

    public void joinOrmawaChannel(UserOrmawaChannel userOrmawaChannel) {
        try {
            userOrmawaChannelRepository.save(userOrmawaChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
