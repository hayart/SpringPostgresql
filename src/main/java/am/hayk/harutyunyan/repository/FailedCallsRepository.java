package am.hayk.harutyunyan.repository;

import am.hayk.harutyunyan.model.FailedCalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedCallsRepository extends JpaRepository<FailedCalls, Long> {
}
