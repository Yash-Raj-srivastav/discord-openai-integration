# Discord Bot with OpenAI Integration

This is a Java Discord bot that integrates with the OpenAI API to provide responses to user queries. The bot listens for specific commands in Discord channels and uses the OpenAI API to generate responses based on the provided input.

## Features

- Responds to user commands in Discord channels.
- Utilizes the OpenAI API for natural language generation.
- Customizable to handle various user queries and commands.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed.
- Maven build tool installed.
- Discord bot token and OpenAI API key.

## Installation

1. Clone the repository:
```git clone https://github.com/yourusername/discord-bot-openai.git```
2. Navigate to the project directory:
```cd discord-bot-openai```
3. Open the src/main/resources/application.properties file and replace the placeholders with your Discord bot token and OpenAI API key.
4. Build the project using Maven:
```mvn clean package```
Run the bot:
```java -jar target/discord-bot-1.0-SNAPSHOT.jar```

## Usage

To use the bot, invite it to your Discord server and prefix your commands with /openai. For example:
/openai How does photosynthesis work?
The bot will process your query using the OpenAI API and respond with the generated answer.

## Customization
You can customize the bot's behavior by modifying the code in the OpenAIExample class. You can adjust how queries are processed and responses are generated based on your specific use case.

## Contributing
If you'd like to contribute to this project, please follow these steps:

```
Fork the repository.
Create a new branch for your feature or bug fix.
Make your changes and commit them.
Push your changes to your fork.
Submit a pull request to the main repository.
```
## License
This project is licensed under the MIT License - see the LICENSE.md file for details.

### Acknowledgments
Thanks to Discord4J and OpenAI for providing the libraries and APIs used in this project.
