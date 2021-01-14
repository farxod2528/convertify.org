package uz.document.converter.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.document.converter.exception.CustomException;
import uz.document.converter.model.Request;
import uz.document.converter.model.Response;
import uz.document.converter.service.BaseLocale;
import uz.document.converter.service.Converter;

@RestController
@RequestMapping(value = "/api/converter")
public class ConverterController extends BaseLocale {
    private final Converter converter;

    public ConverterController(Converter converter) {
        this.converter = converter;
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@ModelAttribute Request request){
        Response<Resource> response = new Response<>();
        try {
            response = converter.convertCorresponding(request);
        }catch (CustomException e){
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setCode(getCode("converter.code.server.error"));
            response.setMessage(getMessage("converter.server.error"));
        }

        if (response.isSuccess()){
            Resource resource = (Resource) response.getBody();
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                            resource.getFilename() + "\"")
                    .body(resource);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
