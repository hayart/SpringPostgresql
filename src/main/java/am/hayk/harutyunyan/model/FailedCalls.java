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
public class FailedCalls implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "payload")
    private String payload;

    @Column(name = "reason_of_failure")
    private String reasonOfFailure;

    @Column(name = "time_of_failure")
    private String timeOfFailure;

    @Column(name = "procesed")
    private String procesed;

}