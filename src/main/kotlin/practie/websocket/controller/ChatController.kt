package practie.websocket.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.stereotype.Controller
import practie.websocket.model.ChatMessage


@Controller
class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    fun sendMessage(@Payload chatMessage: ChatMessage) : ChatMessage{
        return chatMessage
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    fun addUser(@Payload chatMessage: ChatMessage
                , headerAccessor: SimpMessageHeaderAccessor) : ChatMessage{
        headerAccessor.sessionAttributes?.put("username", chatMessage.sender)
        return chatMessage
    }
}