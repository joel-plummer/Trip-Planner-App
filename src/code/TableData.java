package code;

import javax.swing.*;
import javax.swing.table.*;  
import java.awt.*;

/**
 * This class generates a table for displaying the trip data.
 */

public class TableData {
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    /**
     * This sets up the table to put in the given panel.
     * @param pnl is the panel where the table will be displayed.
     */
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

    /**
     * This returns the table's model. 
     */
    public DefaultTableModel getModel(){
        return model;
    }    
    
}
