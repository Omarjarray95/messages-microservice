package messages.management.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Message implements Serializable
{
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@ManyToOne
	private User sender;
	
	@ManyToOne
	private Conversation conversation;
	
	@Column
	private Date sendingDate;
	
	@Column
	private String content;
	
	@Column
	private boolean seen;
	
	@Column(nullable = true)
	private Date seenDate;

	public int getId() {
		return id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@JsonIgnore
	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Date getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(Date sendingDate) {
		this.sendingDate = sendingDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public Date getSeenDate() {
		return seenDate;
	}

	public void setSeenDate(Date seenDate) {
		this.seenDate = seenDate;
	}
}
