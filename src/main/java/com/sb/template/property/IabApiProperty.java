package com.sb.template.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IabApiProperty {

	@Value("${property.app.res-body-oauth}")
	public String resBodyOAuth;

	@Value("${property.app.res-body-purchases-sbsc-get}")
	public String resBodyPurchasesSbscGet;

}
