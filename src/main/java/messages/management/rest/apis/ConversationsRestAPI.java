package messages.management.rest.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import messages.management.entities.Message;
import messages.management.services.ConversationService;

@RestController
@RequestMapping(value = "/conversations")
public class ConversationsRestAPI 
{
	@Autowired(required=true)
	private ConversationService ConversationService;
	
	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteConversation(@PathVariable(value = "id") int id) 
	{
		return new ResponseEntity<>(ConversationService.deleteConversation(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/{sender}/{receiver}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Message>> getConversationMessages(@PathVariable(value = "sender") int sender, 
			@PathVariable(value = "receiver") int receiver) 
	{
		return new ResponseEntity<>(ConversationService.showConversation(sender, receiver), HttpStatus.OK);
	}
	
	@PutMapping(value = "/see/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> seeConversationMessages(@PathVariable(value = "id") int id)
	{
		return new ResponseEntity<>(ConversationService.seeConversationMessages(id, true), HttpStatus.OK);
	}
}
