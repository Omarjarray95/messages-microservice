package messages.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import messages.management.entities.User;

public interface UsersRepository extends JpaRepository<User, Integer> 
{

}
