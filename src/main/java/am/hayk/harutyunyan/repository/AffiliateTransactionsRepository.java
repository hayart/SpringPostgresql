package am.hayk.harutyunyan.repository;

import am.hayk.harutyunyan.model.AffiliateTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffiliateTransactionsRepository extends JpaRepository<AffiliateTransactions, Long> {
}
