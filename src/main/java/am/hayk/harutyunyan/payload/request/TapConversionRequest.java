package am.hayk.harutyunyan.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TapConversionRequest {
    private UUID clickId;
    private String externalId;
    private Double amount;
    private String currency;
    private UUID customerId;
    private String userAgent;
    private String ip;

}
