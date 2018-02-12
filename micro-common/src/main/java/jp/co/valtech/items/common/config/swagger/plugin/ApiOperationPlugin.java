package jp.co.valtech.items.common.config.swagger.plugin;

import com.google.common.base.Optional;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ApiOperationPlugin implements OperationBuilderPlugin {

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(OperationContext context) {
        Optional<ApiResponses> apiResponses = context.findAnnotation(ApiResponses.class);
        if (apiResponses.isPresent()) {
            Set<ResponseMessage> responseMessages = new HashSet<>();
            ApiResponse[] reses = apiResponses.get().value();
            for (ApiResponse res : reses) {
                HttpStatus status = HttpStatus.valueOf(res.code());
                ResponseMessage response = new ResponseMessage(res.code(), status.getReasonPhrase(), null, null, Collections.emptyList());
                responseMessages.add(response);
            }
            OperationBuilder builder = context.operationBuilder();
            builder.responseMessages(responseMessages);
        }
    }
}
