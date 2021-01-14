package uz.document.converter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.document.converter.model.Request;
import uz.document.converter.model.Response;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Optional;

import static uz.document.converter.constant.AppConstants.FILE_LOCATION;

@Component
public abstract class AbstractConverter extends BaseLocale{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init(){
        File base = new File(FILE_LOCATION);
        if (!base.exists()){
            boolean created = base.mkdir();
            if (!created){
                logger.debug("Can not create base folder!");
            }
        }
    }
    public Response<Resource> process(Request source){
        Response<Resource> response = new Response<>();
        Optional<Resource> resource = convert(source);

        if (resource.isPresent()){
            response.setBody(resource.get());
            response.setCode(getCode("converter.code.success"));
            response.setMessage(getMessage("converter.success"));
        } else {
            response.setCode(getCode("converter.code.fail"));
            response.setMessage(getMessage("converter.fail"));
        }
        return response;
    }

    protected abstract Optional<Resource> convert(Request source);

}
