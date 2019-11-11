package messages.management.rest.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import messages.management.entities.Message;
import messages.management.services.MessageService;

@RestController
@RequestMapping(value = "/messages")
public class MessagesRestAPI 
{
	@Autowired(required=true)
	private MessageService MessageService;
	
	@RequestMapping("/hello")
	public String sayHello() 
	{
		System.out.println("Welcome To Pinterest");
		return "Welcome To Pinterest";
	}
	
	@PostMapping(value = "/send/{sender}/{receiver}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Message> createMessage(@RequestBody Message message, 
			@PathVariable(value = "sender") int sender, @PathVariable(value = "receiver") int receiver) 
	{
		return new ResponseEntity<>(MessageService.sendMessage(message, sender, receiver)
				, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteMessage(@PathVariable(value = "id") int id) 
	{
		return new ResponseEntity<>(MessageService.deleteMessage(id), HttpStatus.OK);
	}
	
	@PutMapping(value = "/unsee/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Message> unseeMessage(@PathVariable(value = "id") int id)
	{
		return new ResponseEntity<>(MessageService.unseeMessage(id, false), HttpStatus.OK);
	}
}
