package am.hayk.harutyunyan.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TapClicksRequest {
    private String referralCode;
    private String landingPage;
    private String userAgent;
    private String ip;
}
