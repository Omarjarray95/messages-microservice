package messages.management.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private User firstMember;
	
	@ManyToOne
	private User secondMember;
	
	@Column
	private Date startDate;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="conversation")
	private List<Message> messages;

	public int getId() {
		return id;
	}

	public User getFirstMember() {
		return firstMember;
	}

	public void setFirstMember(User firstMember) {
		this.firstMember = firstMember;
	}

	public User getSecondMember() {
		return secondMember;
	}

	public void setSecondMember(User secondMember) {
		this.secondMember = secondMember;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
