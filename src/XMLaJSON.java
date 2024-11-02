import javax.swing.*;
import java.io.*;

public class XMLaJSON{
    public static void main(String[] args) {
        String xmlFilePath = "ruta/del/archivo/car_sales.xml";
        String jsonFilePath = "ruta/de/salida/car_sales.json";

        try {
            String xmlContent = readXMLFile(xmlFilePath);
            JSONObject json = XML.toJSONObject(xmlContent);
            writeJSONToFile(json, jsonFilePath);
            System.out.println("JSON generado:");
            System.out.println(json.toString());
            JOptionPane.showMessageDialog(null, "Conversión completada. JSON guardado en: " + jsonFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al convertir el archivo XML a JSON.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private static String readXMLFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
    private static void writeJSONToFile(JSONObject json, String filePath) throws IOException {
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(json.toString());
        }
    }
    private static class JSONObject {
    }
}