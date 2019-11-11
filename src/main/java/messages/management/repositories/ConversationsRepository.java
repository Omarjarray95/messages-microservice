package messages.management.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import messages.management.entities.Conversation;

public interface ConversationsRepository extends JpaRepository<Conversation, Integer> 
{
	@Query("SELECT c FROM Conversation c WHERE (c.firstMember.id= :id1 AND c.secondMember.id= :id2)"
			+ " OR (c.firstMember.id= :id2 AND c.secondMember.id= :id1)")
	public Page<Conversation> getConversation(@Param("id1") int id1, @Param("id2") int id2, 
			Pageable pageable);
}
