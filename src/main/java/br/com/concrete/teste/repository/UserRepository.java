package br.com.concrete.teste.repository;

import br.com.concrete.teste.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Long countByEmail(String email);
    User findByEmail(String email);
    User findById(UUID id);

    @Query("SELECT u.last_login " +
            "FROM User u " +
            "WHERE u.id = :id ")
    LocalDateTime getLastLogin(@Param("id") UUID id);
}
