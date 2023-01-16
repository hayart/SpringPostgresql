package am.hayk.harutyunyan.controller;

import am.hayk.harutyunyan.payload.request.TapClicksRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/exercise/tap/")
public class ClicksDummyController {

    @PostMapping(value = "/clicks", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object clicks(TapClicksRequest request){
        Random random = new Random();
        Integer generatedNumber = random.nextInt(2);
        if (generatedNumber.equals(1)) {
            log.info("Bad response because random generated value {}", generatedNumber);
            return ResponseEntity.badRequest();
        } else {
            return ResponseEntity.ok().body(Map.of("id", "ab885a1c-8ad9-11ea-bc55-0242ac130003"));
        }
    }

    @PostMapping(value = "/conversions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> conversions(TapClicksRequest request){
        Random random = new Random();
        Integer generatedNumber = random.nextInt(2);
        if (generatedNumber.equals(2)) {
            log.info("Bad response because random generated value {}", generatedNumber);
            return ResponseEntity.badRequest().body("");
        } else {
            try {
                ClassPathResource staticDataResource = new ClassPathResource("DummyJsonFile.json");
                String json = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> map = mapper.readValue(json, Map.class);
                return ResponseEntity.ok().body(map);
            } catch (Exception ex){
                log.info(ex.getMessage());
                return ResponseEntity.badRequest().body("");
            }
        }
    }

}
