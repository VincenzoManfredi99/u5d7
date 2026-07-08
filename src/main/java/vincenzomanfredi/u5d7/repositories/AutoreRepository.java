package vincenzomanfredi.u5d7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vincenzomanfredi.u5d7.entities.Autore;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Long> {
    boolean existsByEmail(String email);
}
