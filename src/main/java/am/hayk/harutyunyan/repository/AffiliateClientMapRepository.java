package am.hayk.harutyunyan.repository;

import am.hayk.harutyunyan.model.AffiliateClientMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateClientMapRepository extends JpaRepository<AffiliateClientMap, Long> {
}
