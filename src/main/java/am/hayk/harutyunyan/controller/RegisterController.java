package am.hayk.harutyunyan.controller;

import am.hayk.harutyunyan.payload.request.ClientRequest;
import am.hayk.harutyunyan.payload.request.ConversionRequest;
import am.hayk.harutyunyan.service.ClientService;
import am.hayk.harutyunyan.service.ConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterController {

    private final ClientService clientService;
    private final ConversionService conversionService;

    @PostMapping("/client")
    public ResponseEntity<?> saveClient(@RequestBody ClientRequest request){
        return ResponseEntity.ok().body(clientService.process(request));
    }

    @PostMapping("/conversion")
    public ResponseEntity<?> saveConversion(@RequestBody ConversionRequest request){
        return ResponseEntity.ok().body(conversionService.process(request));
    }
}
