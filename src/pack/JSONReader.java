package pack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONReader {
    public static File speicherfile = new File("C:/Users/Pete Louis Benz/Documents/Gppcc/Store/Objects.json");

    public static void readJson(int level) {
        try {
            String speicher = new String(Files.readAllBytes(Paths.get(speicherfile.toURI())), "UTF-8");
            JSONArray all = new JSONArray(speicher);
            JSONArray Level = new JSONArray(all.get(0).toString());

            for (int i = 0; i < Level.length(); i++) {
                JSONObject Object = Level.getJSONObject(i);

                String name = Object.getString("name");
                switch (name) {
                    case "Enemy":

                        break;
                }
            }


        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public static void writeJSON(String name,int X,int Y,int Room){
        try {
            String speicher = new String(Files.readAllBytes(Paths.get(speicherfile.toURI())), "UTF-8");
            JSONArray all = new JSONArray(speicher);
            JSONArray Level = new JSONArray(all.get(Room).toString());

            JSONObject Object = new JSONObject();
            Object.put("name",name);
            Object.put("X",X);
            Object.put("Y",Y);

            Level.put(Object);
            all.put(Room,Level);
            System.out.println(all.toString());

            FileWriter fileWriter = new FileWriter(speicherfile);
            fileWriter.write(all.toString());
            //fileWriter.write(jsonObject.toString());

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
