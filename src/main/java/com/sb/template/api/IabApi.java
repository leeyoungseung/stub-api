package com.sb.template.api;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sb.template.property.IabApiProperty;

@RestController
@RequestMapping(path = "/stub-iab")
public class IabApi {

	@Autowired
	private IabApiProperty prop;

	@RequestMapping(
			method = RequestMethod.POST ,
			path = "/o/oauth2/token"
			)
	public ResponseEntity<?> stubOAuth() throws ParseException, IOException {

		System.out.println("stubOAuth");
		String path = prop.resBodyOAuth;
		String jsonStr = getJsonStr(path);
		return ResponseEntity.ok().body(toJsonObj(jsonStr));
	}


	@RequestMapping(
			method = RequestMethod.GET ,
			path = "/v3/applications/{packageName}/purchases/subscriptions/{subscriptionId}/tokens/{token}"
			)
	public ResponseEntity<JSONObject> stubPurchasesSbscGet (
			@PathVariable String packageName,
			@PathVariable String subscriptionId,
			@PathVariable String token
			) throws ParseException, IOException {
		System.out.println("stubPurchasesSbscGet");
		String path = prop.resBodyPurchasesSbscGet;
		String jsonStr = getJsonStr(path);
		return ResponseEntity.ok().body(toJsonObj(jsonStr));
	}


	@RequestMapping(
			method = RequestMethod.POST ,
			path = "/v3/applications/{packageName}/purchases/subscriptions/{subscriptionId}/tokens/{token}:acknowledge"
			)
	public ResponseEntity<?> stubPurchasesSbscAcknowledge () {
		System.out.println("stubPurchasesSbscAcknowledge");
		return ResponseEntity.ok().body(null);
	}


	private JSONObject toJsonObj(String jsonStr) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonStr);
		return jsonObject;
	}


	private String getJsonStr(String path) throws IOException {
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        byte[] bytes  = new byte[(int)file.length()];

        int offset = 0;
        while (offset < bytes.length)
        {
            int result = in.read(bytes, offset, bytes.length - offset);
            if (result == -1) {
                break;
            }
            offset += result;
        }

        return new String(bytes, Charset.forName("UTF-8"));
	}

}
