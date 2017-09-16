package savedata;

import com.fasterxml.jackson.databind.ObjectMapper;

import foodstorage.FoodItem;
import foodstorage.StorageUnit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SaveManager {

    private final static String path = "../savedata/savefiles/";

    protected ArrayList<StorageUnit> allStorageUnits;
    private ObjectMapper mapper = new ObjectMapper();
//test commit
    public void saveToJson(StorageUnit unitToSave) throws IOException {
        mapper.writeValue(new File(path + "test.json"), unitToSave);
    }

    public void saveToJson(FoodItem foodToSave) {

    }

    public void retrieveFromJson(StorageUnit unitToGet) {

    }

    public void retrieveFromJson(FoodItem foodToGet) {

    }

}
