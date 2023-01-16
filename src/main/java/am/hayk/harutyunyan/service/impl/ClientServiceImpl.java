package am.hayk.harutyunyan.service.impl;

import am.hayk.harutyunyan.model.AffiliateClientMap;
import am.hayk.harutyunyan.model.FailedCalls;
import am.hayk.harutyunyan.payload.request.ClientRequest;
import am.hayk.harutyunyan.payload.request.TapClicksRequest;
import am.hayk.harutyunyan.payload.response.ClientResponse;
import am.hayk.harutyunyan.repository.AffiliateClientMapRepository;
import am.hayk.harutyunyan.repository.FailedCallsRepository;
import am.hayk.harutyunyan.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClientServiceImpl implements ClientService {

    private final AffiliateClientMapRepository affiliateClientMapRepository;

    private final FailedCallsRepository failedCallsRepository;

    private final RestTemplate restTemplate;

    private UUID clickId;

    @Override
    public ClientResponse process(ClientRequest request) {
         boolean isCallSuccessfully = call(
                TapClicksRequest.builder()
                        .referralCode(request.getReferralCode())
                        .landingPage(request.getLandingPage())
                        .userAgent(request.getUserAgent())
                        .ip(request.getIp())
                        .build()
        );

         if (isCallSuccessfully) {
             saveSuccessIntoDB(request, this.clickId);
         } else {
             saveFailIntoDB(request);
         }

        return new ClientResponse(clickId);
    }

    private boolean call(TapClicksRequest request) {
        String url = "http://localhost:8080/exercise/tap/clicks";
        boolean isSuccessCall = false;
        int tryingCallCount = 0;
        while(tryingCallCount ++ < 3) {
            log.info("Trying to call remote URL, attempt : {}", tryingCallCount);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<?> entity = new HttpEntity<>(request, headers);

            try {
                ResponseEntity<String> response =
                        restTemplate.postForEntity(url, entity, String.class);

                if (response.getStatusCode().equals(HttpStatus.OK)) {
                    JSONObject json = new JSONObject(response.getBody());
                    this.clickId = UUID.fromString(json.getString("id"));
                    isSuccessCall = true;
                    break;
                }
            } catch(org.springframework.web.client.RestClientException ex){
                isSuccessCall = false;
            }
        }
        return isSuccessCall;
    }

    private void saveSuccessIntoDB(ClientRequest request, UUID clickId){
        AffiliateClientMap entity = new AffiliateClientMap();
        //entity.setClientId(request.getClientId());
        entity.setClickId(clickId);
        entity.setReferralCode(request.getReferralCode());
        entity.setUserAgent(request.getUserAgent());
        affiliateClientMapRepository.save(entity);
    }

    private void saveFailIntoDB(ClientRequest request){
        FailedCalls entity = new FailedCalls();
        entity.setClientId(request.getClientId());
        entity.setRequestType("createClick");
        entity.setPayload(request.toString());
        entity.setReasonOfFailure("Random value always 1");
        entity.setTimeOfFailure(LocalDateTime.now().toString());
        entity.setProcesed("false");
        failedCallsRepository.save(entity);
    }
}
