package savedata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import foodstorage.FoodItem;
import foodstorage.StorageUnit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SaveManager {

    private final static String path = "../savedata/savefiles/";

    protected ArrayList<StorageUnit> allStorageUnits;

    public static void saveToJson(StorageUnit unitToSave) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File saveFile = new File(path + "saveFile.json");

        ArrayNode jsonArray = (ArrayNode) mapper.readTree(saveFile);
        ObjectNode jsonObject = mapper.valueToTree(unitToSave);
        jsonArray.add(jsonObject);

        mapper.writeValue(saveFile, jsonArray);
    }

    public void saveToJson(FoodItem foodToSave) {

    }

    public void retrieveFromJson(StorageUnit unitToGet) {

    }

    public void retrieveFromJson(FoodItem foodToGet) {

    }

}
