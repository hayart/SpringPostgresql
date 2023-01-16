package am.hayk.harutyunyan.service;

import am.hayk.harutyunyan.payload.request.ClientRequest;
import am.hayk.harutyunyan.payload.response.ClientResponse;

public interface ClientService {

    ClientResponse process(ClientRequest request);

}
