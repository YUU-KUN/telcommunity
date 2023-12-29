package telcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import telcommunity.model.ClassChannel;
import telcommunity.model.User;
import telcommunity.model.UserClassChannel;
import telcommunity.repository.UserClassChannelRepository;
import telcommunity.repository.UserRepository;

@Service
public class UserClassChannelService {
    @Autowired
    UserClassChannelRepository userClassChannelRepository;

    @Autowired
    UserRepository userRepository;

    public List<UserClassChannel> getUserClassChannels() {
        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        List<UserClassChannel> userClassChannels = new ArrayList<UserClassChannel>();
        userClassChannelRepository.findAll().forEach(userClassChannel -> {
            if (userClassChannel.getUser().getId().equals(user.getId())) {
                userClassChannels.add(userClassChannel);
            }
        });
        return userClassChannels;
    }

    public void joinClassChannel(UserClassChannel userClassChannel) {
        try {
            userClassChannelRepository.save(userClassChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
