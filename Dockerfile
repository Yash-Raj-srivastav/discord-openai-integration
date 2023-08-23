FROM openjdk:19
ADD target/my-discord-bot-project.jar my-discord-bot-project.jar
ENTRYPOINT ["java", "-jar","my-discord-bot-project.jar"]
CMD ["javac" "Discord.java"]
EXPOSE 8080