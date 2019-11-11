package messages.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import messages.management.entities.Message;

public interface MessagesRepository extends JpaRepository<Message, Integer>
{
	@Query("SELECT m FROM Message m WHERE m.conversation.id= :id order by m.sendingDate ASC")
	public Page<Message> getConversationMessages(@Param("id") int id, Pageable pageable);
}
