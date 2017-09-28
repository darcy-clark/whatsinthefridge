package savedata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
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
    private final static String FOOD_MAP_KEY = "foodItems";

    public static Map<String, StorageUnit> loadTreeMap() throws IOException {
        File saveFile = new File(PATH);
        //despite what intellij says, the constructor for initTreeMap is not redundant (see parameter)
        Map<String, StorageUnit> initTreeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        ObjectMapper mapper = new ObjectMapper();
        initTreeMap = mapper.readValue(saveFile, new TypeReference<TreeMap<String, StorageUnit>>(){});
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

    public static void saveToJson(FoodItem foodToSave) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File saveFile = new File(PATH);

        ObjectNode json = (ObjectNode) mapper.readTree(saveFile);
        ObjectNode foodMap = (ObjectNode) json.get(foodToSave.getLocation()).get(FOOD_MAP_KEY);
        ObjectNode foodItem = mapper.valueToTree(foodToSave);

        ArrayNode foodWithSameName;
        if(foodMap.get(foodToSave.getName()) == null) {
            JsonNodeFactory nf = JsonNodeFactory.instance;
            foodWithSameName = new ArrayNode(nf);
            foodMap.set(foodToSave.getName(), foodWithSameName);
            foodWithSameName.add(foodItem);
        } else {
            foodWithSameName = (ArrayNode) foodMap.get(foodToSave.getName());
            foodWithSameName.add(foodItem);
        }

        mapper.writeValue(saveFile, json);
    }

    public void retrieveFromJson(StorageUnit unitToGet) {

    }

    public void retrieveFromJson(FoodItem foodToGet) {

    }

    public static void deleteFromJson(StorageUnit unitToDelete) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File saveFile = new File(PATH);

        ObjectNode json = (ObjectNode) mapper.readTree(saveFile);
        json.remove(unitToDelete.getName());

        mapper.writeValue(saveFile, json);
    }

    public static void deleteFromJson(FoodItem itemToDelete) throws IOException {
        deleteFromJson(itemToDelete, false, null);
    }

    public static void deleteFromJson(FoodItem itemToDelete, boolean id, StorageUnit unit) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File saveFile = new File(PATH);

        ObjectNode json = (ObjectNode) mapper.readTree(saveFile);
        ObjectNode foodItems = (ObjectNode) json.get(itemToDelete.getLocation()).get(FOOD_MAP_KEY);
        if(id) {
            ArrayList<FoodItem> listOfFood = unit.getFoodItems().get(itemToDelete.getName());
            ArrayNode foodArray = mapper.valueToTree(listOfFood);
            foodItems.set(itemToDelete.getName(), foodArray);
        } else {
            foodItems.remove(itemToDelete.getName());
        }

        mapper.writeValue(saveFile, json);
    }

}
