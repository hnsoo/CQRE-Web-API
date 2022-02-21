package sch.cqre.api.service;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JsonMessager {


    public ResponseEntity err(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        jsonObject.put("status", "error");
        return ResponseEntity.badRequest().body(jsonObject);
    }


}
