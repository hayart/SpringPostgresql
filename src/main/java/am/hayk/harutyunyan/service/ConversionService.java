package am.hayk.harutyunyan.service;

import am.hayk.harutyunyan.payload.request.ConversionRequest;
import am.hayk.harutyunyan.payload.response.ConversionResponse;

public interface ConversionService {

    ConversionResponse process(ConversionRequest request);

}
