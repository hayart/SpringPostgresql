package am.hayk.harutyunyan.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "AFFILIATE_TRANSACTIONS")
public class AffiliateTransactions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "click_id")
    private String clickId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "conversion_amount")
    private String conversionAmount;

    @Column(name = "order_amount")
    private String orderAmount;

    @Column(name = "transaction_type")
    private String transactionType;

}