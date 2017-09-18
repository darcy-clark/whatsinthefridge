package savedata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import foodstorage.FoodItem;
import foodstorage.StorageUnit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SaveManager {

    private final static String PATH = "../savedata/savefiles/saveFile.json";


    public static Map<String, StorageUnit> loadTreeMap() throws IOException {
        File saveFile = new File(PATH);
        Map<String, StorageUnit> initTreeMap;
        ObjectMapper mapper = new ObjectMapper();
        initTreeMap = mapper.readValue(saveFile, new TypeReference<Map<String, StorageUnit>>(){});
        return initTreeMap;
    }

    public static void saveToJson(StorageUnit unitToSave) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File saveFile = new File(PATH);

        ObjectNode json = (ObjectNode) mapper.readTree(saveFile);
        ObjectNode jsonObject = mapper.valueToTree(unitToSave);
        json.set(unitToSave.getName(), jsonObject);

        mapper.writeValue(saveFile, json);
    }

    public void saveToJson(FoodItem foodToSave) {

    }

    public void retrieveFromJson(StorageUnit unitToGet) {

    }

    public void retrieveFromJson(FoodItem foodToGet) {

    }

}
