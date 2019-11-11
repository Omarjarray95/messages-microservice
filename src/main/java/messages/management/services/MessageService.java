package messages.management.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import messages.management.entities.Conversation;
import messages.management.entities.Message;
import messages.management.entities.User;
import messages.management.repositories.ConversationsRepository;
import messages.management.repositories.MessagesRepository;
import messages.management.repositories.UsersRepository;

@Service
public class MessageService 
{
	@Autowired
	private MessagesRepository MessagesRepository;
	
	@Autowired
	private UsersRepository UsersRepository;
	
	@Autowired
	private ConversationsRepository ConversationsRepository;
	
	public Message sendMessage(Message message, int sender, int receiver)
	{
		Date now = new Date();
		
		List<Conversation> existingConversations = ConversationsRepository
				.getConversation(sender, receiver, PageRequest.of(0, 1)).getContent();
		
		User firstMember = UsersRepository.findById(sender).get();
		User secondMember = UsersRepository.findById(receiver).get();
		
		if (existingConversations.size() == 0)
		{	
			Conversation conversation = new Conversation();
			conversation.setFirstMember(firstMember);
			conversation.setSecondMember(secondMember);
			conversation.setStartDate(now);
			
			Conversation newConversation = ConversationsRepository.save(conversation);
			
			message.setSender(firstMember);
			message.setSendingDate(now);
			message.setSeen(false);
			message.setConversation(newConversation);
		}
		else if (existingConversations.size() > 0)
		{			
			Conversation conversation = existingConversations.get(0);
			
			message.setSender(firstMember);
			message.setSendingDate(now);
			message.setConversation(conversation);
		}
		
		return MessagesRepository.save(message);
	}
	
	public Message unseeMessage(int message, boolean seen)
	{
		Message messageToTreat = MessagesRepository.findById(message).get();
		
		messageToTreat.setSeen(seen);
		
		return MessagesRepository.save(messageToTreat);
	}
	
	public String deleteMessage(int message)
	{
		boolean messageIsPresent = MessagesRepository.findById(message).isPresent();
		
		if (messageIsPresent)
		{
			MessagesRepository.deleteById(message);
			
			return "Message Deleted";
		}
		
		return "Message Can Not Be Found";
	}
}
