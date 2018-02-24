package jp.co.valtech.items.interfaces.category.requests;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "request")
@ApiModel(description = "リクエスト情報")
public class CategorySearchRequest {

}
