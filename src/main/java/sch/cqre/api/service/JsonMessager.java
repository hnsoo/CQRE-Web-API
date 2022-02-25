package sch.cqre.api.service;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class JsonMessager {
    public ResponseEntity errStr(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        jsonObject.put("status", "error");
        return ResponseEntity.badRequest().body(jsonObject);
    }

    public ResponseEntity successStr(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        jsonObject.put("status", "success");
        return ResponseEntity.ok().body(jsonObject);
    }

    public ResponseEntity successArray(JSONArray message) {
        return ResponseEntity.ok().body(message);
    }


}
