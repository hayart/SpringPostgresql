package am.hayk.harutyunyan.service.impl;

import am.hayk.harutyunyan.model.AffiliateClientMap;
import am.hayk.harutyunyan.model.AffiliateTransactions;
import am.hayk.harutyunyan.model.FailedCalls;
import am.hayk.harutyunyan.payload.request.ConversionRequest;
import am.hayk.harutyunyan.payload.request.TapConversionRequest;
import am.hayk.harutyunyan.payload.response.ConversionResponse;
import am.hayk.harutyunyan.repository.AffiliateClientMapRepository;
import am.hayk.harutyunyan.repository.AffiliateTransactionsRepository;
import am.hayk.harutyunyan.repository.FailedCallsRepository;
import am.hayk.harutyunyan.service.ConversionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ConversionServiceImpl implements ConversionService {

    @Value("${currency}")
    private String currency;

    private UUID conversionId;

    private final AffiliateTransactionsRepository affiliateTransactionsRepository;

    private final AffiliateClientMapRepository affiliateClientMapRepository;

    private final FailedCallsRepository failedCallsRepository;

    private final RestTemplate restTemplate;

    @Override
    public ConversionResponse process(ConversionRequest request) {

        AffiliateClientMap informationFromDB = this.getInformationFromDB();

        UUID clickId = informationFromDB.getClickId();
        String userAgent = informationFromDB.getUserAgent();
        String ip = "";//informationFromDB.getIp(); there is no Ip field in

         boolean isCallSuccessfully = call(
                 TapConversionRequest.builder()
                        .clickId(clickId)
                        .externalId(request.getOrderId())
                        .amount(null) //there is no amount in payload
                        .currency(currency)
                        .customerId(request.getClientId())
                        .userAgent(userAgent)
                        .ip(ip)
                        .build()
        );

         if (isCallSuccessfully) {
             saveSuccessIntoDB(request);
         } else {
             saveFailIntoDB(request);
         }

        return new ConversionResponse(conversionId);
    }

    private boolean call(TapConversionRequest request) {
        String url = "http://localhost:8080/exercise/tap/conversions";
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
                    isSuccessCall = true;
                    break;
                }
            } catch(org.springframework.web.client.RestClientException ex){
                isSuccessCall = false;
            }
        }
        return isSuccessCall;
    }

    private AffiliateClientMap getInformationFromDB(){
        List<AffiliateClientMap> all = affiliateClientMapRepository.findAll();
        AffiliateClientMap affiliateClientMap = all.get(0);
        return affiliateClientMap;
    }

    private void saveSuccessIntoDB(ConversionRequest request){
        AffiliateTransactions entity = new AffiliateTransactions();
        entity.setClientId(request.getClientId());
        entity.setReferralCode(null);//should take
        entity.setOrderId(request.getOrderId());
        affiliateTransactionsRepository.save(entity);
        this.conversionId = entity.getId();
    }

    private void saveFailIntoDB(ConversionRequest request){
        FailedCalls entity = new FailedCalls();
        entity.setClientId(request.getClientId());
        entity.setRequestType("createConversion");
        entity.setPayload(request.toString());
        entity.setReasonOfFailure("Random value always 2");
        entity.setTimeOfFailure(LocalDateTime.now().toString());
        entity.setProcesed("false");
        failedCallsRepository.save(entity);
    }
}
