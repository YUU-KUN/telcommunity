package telcommunity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telcommunity.model.Channel;
import telcommunity.model.ClassChannel;
import telcommunity.model.ClassChannelChat;
import telcommunity.model.OrmawaChannel;
import telcommunity.model.OrmawaChannelChat;
import telcommunity.repository.ChannelRepository;
import telcommunity.repository.ClassChannelChatRepository;
import telcommunity.repository.ClassChannelRepository;
import telcommunity.repository.OrmawaChannelRepository;

@Service
public class ChannelService {
    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    OrmawaChannelRepository ormawaChannelRepository;

    @Autowired
    ClassChannelRepository classChannelRepository;

    @Autowired
    ClassChannelChatRepository classChannelChatRepository;


    public List<Channel> getAllChannel() {
        List<Channel> channels = new ArrayList<Channel>();
        channelRepository.findAll().forEach(channel -> channels.add(channel));
        return channels;
    }


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
    
    public ClassChannel getClassChannelById(String class_channel_id) {
        return classChannelRepository.findById(class_channel_id).get();
    }

    public List<ClassChannelChat> getClassChannelChats(String class_channel_id) {
        return getClassChannelById(class_channel_id).getClassChannelChats();
    }
}
