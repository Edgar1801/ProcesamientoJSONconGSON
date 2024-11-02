import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class Carsale {
    String car;
    String price;
}

public class CarSalesViewer extends JFrame {

    public CarSalesViewer() {
        setTitle("Reporte de Ventas de Carros");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        List<Carsale> carSales = loadCarSales("ruta/del/archivo/car_sales.json");
        String[] columnNames = {"Car", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        for (Carsale sale : carSales) {
            String[] data = {sale.car, sale.price};
            tableModel.addRow(data);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
    private List<Carsale> loadCarSales(String filePath) {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Carsale>>() {}.getType();
            return gson.fromJson(new FileReader(filePath), listType);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo JSON", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarSalesViewer viewer = new CarSalesViewer();
            viewer.setVisible(true);
        });
    }
}