import javax.swing.*;
import javax.swing.table.*;  
import java.awt.*;

public class TableData {
    public JTable table;
    public DefaultTableModel model;
    public JScrollPane scrollPane;

    public TableData(JPanel pnl){
        String[] columnNames =  {"Trip Name",
            "Bus Type",
            "Bus ID#",
            "# of People",
            "Trip ID#",
            "Time",
            "Trip Completed?"};

        model = new DefaultTableModel(columnNames,0);
        table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(780,300));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        pnl.add(scrollPane, BorderLayout.SOUTH);
        //pnl.add(table); //don't add this or column names won't show

        
    }
}
