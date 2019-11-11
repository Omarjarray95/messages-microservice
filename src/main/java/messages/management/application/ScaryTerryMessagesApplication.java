package messages.management.application;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import messages.management.entities.User;
import messages.management.repositories.ConversationsRepository;
import messages.management.repositories.UsersRepository;

@SpringBootApplication
public class ScaryTerryMessagesApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(ScaryTerryMessagesApplication.class, args);
	}
	
	@Bean
    ApplicationRunner init(UsersRepository UsersRepository, 
    		ConversationsRepository ConversationsRepository) 
	{
        return args -> {
            Stream.of("Omar", "Ridha", "El Mahdi", "Majid").forEach(username -> 
            {
            	User user = new User();
            	user.setUsername(username);
            	UsersRepository.save(user);
            });
            
            /*Conversation conversation = new Conversation();
            conversation.setFirstMember(UsersRepository.findById(1).get());
            conversation.setSecondMember(UsersRepository.findById(2).get());
            conversation.setStartDate(new Date());
            ConversationsRepository.save(conversation);*/
        };
    }
}
