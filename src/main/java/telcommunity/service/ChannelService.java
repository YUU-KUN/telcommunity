package telcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import telcommunity.model.ClassChannel;
import telcommunity.model.ClassChannelChat;
import telcommunity.model.OrmawaChannel;
import telcommunity.model.OrmawaChannelChat;
import telcommunity.model.User;
import telcommunity.repository.ClassChannelChatRepository;
import telcommunity.repository.ClassChannelRepository;
import telcommunity.repository.OrmawaChannelRepository;
import telcommunity.repository.UserRepository;
import telcommunity.repository.OrmawaChannelChatRepository;

@Service
public class ChannelService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrmawaChannelRepository ormawaChannelRepository;

    @Autowired
    OrmawaChannelChatRepository ormawaChannelChatRepository;

    @Autowired
    ClassChannelRepository classChannelRepository;

    @Autowired
    ClassChannelChatRepository classChannelChatRepository;


    // Ormawa Channel
    public List<OrmawaChannel> getOrmawaChannels() {
        List<OrmawaChannel> ormawa_channels = new ArrayList<OrmawaChannel>();
        ormawaChannelRepository.findAll().forEach(ormawa_channel -> ormawa_channels.add(ormawa_channel));
        return ormawa_channels;
    }

    public OrmawaChannel getOrmawaChannelById(String ormawa_channel_id) {
        try {
            return ormawaChannelRepository.findById(ormawa_channel_id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrmawaChannelChat> getOrmawaChannelChats(String ormawa_channel_id) {
        try {
            return getOrmawaChannelById(ormawa_channel_id).getOrmawaChannelChats();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Class Channel
    public List<ClassChannel> getClassChannels() {
        List<ClassChannel> classChannels = new ArrayList<ClassChannel>();
        classChannelRepository.findAll().forEach(classChannel -> classChannels.add(classChannel));
        return classChannels;
    }

    public List<ClassChannel> getDosenClassChannels() {
        // get user loggedin data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        
        List<ClassChannel> classChannels = new ArrayList<ClassChannel>();
        if ("DOSEN".equals(user.getRole())) {
            classChannelRepository.findAll().forEach(classChannel -> {
                if (classChannel.getDosen().getId() == user.getDosen().getId() ) {
                    classChannels.add(classChannel);
                }
            });
        }
        return classChannels;
    }

    public void addClassChannel(ClassChannel classChannel) {
        try {
            classChannelRepository.save(classChannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addClassChannelChat(ClassChannelChat classChannelChat) {
        try {
            classChannelChatRepository.save(classChannelChat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOrmawaChannelChat(OrmawaChannelChat ormawaChannelChat) {
        try {
            ormawaChannelChatRepository.save(ormawaChannelChat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ClassChannel getClassChannelById(String class_channel_id) {
        return classChannelRepository.findById(class_channel_id).get();
    }

    public List<ClassChannelChat> getClassChannelChats(String class_channel_id) {
        return getClassChannelById(class_channel_id).getClassChannelChats();
    }
}
