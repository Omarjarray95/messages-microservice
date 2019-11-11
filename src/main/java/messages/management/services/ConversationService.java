package messages.management.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import messages.management.entities.Conversation;
import messages.management.entities.Message;
import messages.management.repositories.ConversationsRepository;
import messages.management.repositories.MessagesRepository;

@Service
public class ConversationService 
{
	@Autowired
	private ConversationsRepository ConversationsRepository;
	
	@Autowired
	private MessagesRepository MessagesRepository;
	
	public List<Message> showConversation(int sender, int receiver)
	{
		List<Conversation> existingConversations = ConversationsRepository
				.getConversation(sender, receiver, PageRequest.of(0, 1)).getContent();
		
		if (existingConversations.size() > 0)
		{
			Conversation conversation = existingConversations.get(0);
			
			int id = conversation.getId();
			
			List<Message> conversationMessages = MessagesRepository
					.getConversationMessages(id, PageRequest.of(0, 10)).getContent();
			
			return conversationMessages;
		}
		
		return null;
	}
	
	public String seeConversationMessages(int conversation, boolean seen)
	{	
		List<Message> conversationMessages = MessagesRepository
				.getConversationMessages(conversation, PageRequest.of(0, Integer.MAX_VALUE)).getContent();
		
		conversationMessages.stream().forEach(message -> 
		{
			message.setSeen(seen);
			message.setSeenDate(new Date());
			MessagesRepository.save(message);
		});
		
		return "Conversation Seen";
	}
	
	public String deleteConversation(int conversation)
	{
		boolean conversationIsPresent = ConversationsRepository.findById(conversation).isPresent();
		
		if (conversationIsPresent)
		{
			ConversationsRepository.deleteById(conversation);
			
			return "Conversation Deleted";
		}
		
		return "Conversation Can Not Be Found";
	}
}
