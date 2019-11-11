package messages.management.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable 
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String username;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="sender")
	private List<Message> messages;

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj) 
		{
			return true;
		}
		
		if (!(obj instanceof User)) 
		{
			return false;
		}
		
		User other = (User) obj;
		
		if (id != other.id) 
		{
			return false;
		}
		
		return true;
	}
}
