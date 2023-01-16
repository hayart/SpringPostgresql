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
@Table(name = "AFFILIATE_CLIENT_MAP")
public class AffiliateClientMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "referral_code")
    private String referralCode;

    @Column(name = "click_id")
    private UUID clickId;

    @Column(name = "user_agent")
    private String userAgent;


}