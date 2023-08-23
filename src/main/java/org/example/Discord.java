package org.example;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

import java.io.IOException;

public class Discord {
    private String YOUR_TOKEN = "paste YOUR_DISCORD_BOT_TOKEN here";
    private String question = "";
    private GatewayDiscordClient client = DiscordClientBuilder.create(YOUR_TOKEN).build().login().block();
    private OpenAIIntegration openai = new OpenAIIntegration();
    private String users = "";
    public void connectDiscord() {
        OpenAIIntegration openai = new OpenAIIntegration();
        GatewayDiscordClient client = DiscordClientBuilder.create(YOUR_TOKEN).build().login().block();
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> {
                    question = message.getContent().substring(8);
                    return message.getContent().startsWith("/openai");
                })
                .flatMap(Message::getChannel)
                .flatMap(channel -> {
                    try {
                        String response = openai.generateResponse(question, 300);
                        return channel.createMessage(openai.saveResponseToFile(response).substring(1));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .subscribe();
        client.onDisconnect().block();
    }

//    public void getAllHumanUsers(){
//
//        client.getEventDispatcher().on(MessageCreateEvent.class)
//                .map(MessageCreateEvent::getMessage)
//                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
//                .filter(message -> {
//                    users = String.valueOf(message.getAuthor());
//                    return true;
//                })
//                .flatMap(Message::getChannel)
//                .flatMap(channel -> {
//                        return channel.createMessage(users);
//                })
//                .subscribe();
//        client.onDisconnect().block();
//    }
//
//    public void redirectToMethod(){
//        client.getEventDispatcher().on(MessageCreateEvent.class)
//                .map(MessageCreateEvent::getMessage)
//                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
//                .filter(message -> {
//                    if(message.getContent().startsWith("/openai")){
//                        connectDiscord();
//                        return message.getContent().startsWith("/openai");
//                    }
//                    else if(message.getContent().equalsIgnoreCase("/users")){
//                        getAllHumanUsers();
//                        return message.getContent().equalsIgnoreCase("/users");
//                    }
//                    else{
//                        return false;
//                    }
//                })
//                .flatMap(Message::getChannel)
//                .flatMap(channel -> channel.createMessage("Hello there!"))
//                .subscribe();
//        client.onDisconnect().block();
//    }

    public static void main(String[] args){
        Discord discord = new Discord();
        discord.connectDiscord();
    }
}
