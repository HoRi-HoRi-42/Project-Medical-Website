package com.example.project.controller;

import com.example.project.dto.UserRegistrationDto;
import com.example.project.model.ChatMessage;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.exit;

@Controller
@RequestMapping("/chat")
public class ChatController {
    public int num_chat = 0;

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.leftUser")
    @SendTo("/topic/public")
    public ChatMessage leftUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        num_chat -- ;
        System.out.println("num_chat" + num_chat);
        ChatMessage chatMessageLeft = new ChatMessage();
        chatMessageLeft.setSender(chatMessage.getSender());
        chatMessageLeft.setContent(chatMessage.getSender());
        chatMessageLeft.setType(ChatMessage.MessageType.LEAVE);
        return chatMessageLeft;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        User user  = userRepository.findByEmail(chatMessage.getSender());
        if(user != null) {
            System.out.println("EXISTA USERUL");
            num_chat++;
            if(num_chat <= 2) {
                // Add username in web socket session
                if(num_chat < 2) {
                    headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
                    return chatMessage;
                }
                else { //al doilea user din chat sa fie doctor ....de continuat

                    try {
                        FileWriter myWriter = new FileWriter("filename.txt");
                        myWriter.write("ocupat");
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                    return chatMessage;
                }
            }
            else
            {
                ChatMessage chatMessageOccupied = new ChatMessage();
                chatMessageOccupied.setType(ChatMessage.MessageType.OCCUPIED);
                chatMessageOccupied.setContent(chatMessage.getSender());
                num_chat --;

                return chatMessageOccupied;
            }
        }
        else
        {
            System.out.println(" NU EXISTA USERUL");
            ChatMessage chatMessageInvalidUser = new ChatMessage();
            chatMessageInvalidUser.setContent(chatMessage.getSender());
            chatMessageInvalidUser.setType(ChatMessage.MessageType.INVALID_USER);
            return chatMessageInvalidUser;
        }

    }
    @PostMapping
    public String verifyUser(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        User user = userRepository.findByEmail(registrationDto.getEmail());
        if(user == null) {
            //throw new UsernameNotFoundException("Existing user");
            return "redirect:/chat?error";
        }
        return "redirect:/chat?success";
    }


}