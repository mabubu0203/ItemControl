package jp.co.valtech.items.interfaces.category.requests;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
@XmlRootElement(name = "request")
@ApiModel(description = "リクエスト情報")
public class CategorySearchRequest
        implements Serializable {

    private static final long serialVersionUID = 2833754991875538664L;

}
