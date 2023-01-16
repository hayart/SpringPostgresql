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
public class ConversionRequest {
    private String orderId;
    private Double totalPrice;
    private UUID clientId;
    private String transactionType;
}
