package com.firstframework.data;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
        //Read Json
        String JSONContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")
                + "//src//test//java//com//firstframework//data//PurchaseOrder.json"));
        //String to HashMap - Jackson databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(JSONContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }
}
