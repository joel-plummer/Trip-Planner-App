import javax.swing.*;
import javax.swing.table.*;  
import java.awt.*;

/**
 * Generates a table for displaying the data.
 */
public class TableData {
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    /*Setting up the Panel names */
    public TableData(JPanel pnl) {
        String[] columnNames =  {"Trip Name",
            "Bus Type",
            "Bus ID#",
            "# of People",
            "Trip ID#",
            "Time",
            "Trip Completed?"};

        model = new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        table.setEnabled(false);

        table.setPreferredScrollableViewportSize(new Dimension(780,300));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        pnl.add(scrollPane, BorderLayout.SOUTH);
        
    }

    public DefaultTableModel getModel(){
        return model;
    }    
    
}
