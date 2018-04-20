/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Onga_Medica;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author t
 */
public class class_fungsi {
    void showMsg(String msg, String msgTitle, int msgType) {
        /*0=Error, 1=Info msg, 2 = exclamation*/
        JOptionPane.showMessageDialog(null, msg, msgTitle, msgType);
    }
    public void ExitMenu(JButton obj) {
        obj.setForeground(Color.white);
        obj.setOpaque(false);
        obj.getParent().setBackground(obj.getParent().getParent().getBackground());
    }
    public void ExitButton(JButton obj) {
        
        obj.setOpaque(false);
    }
    public void showTblGrid(JTable obj, String sql) throws SQLException {
        koneksi oconn = new koneksi();
        DefaultTableModel modTbl = new DefaultTableModel();
        
        ResultSet rs=oconn.getData(sql);
        ResultSetMetaData metaData=rs.getMetaData();
        
        // names of columns
        Vector<String> colNames = new Vector<String>();
        int colCount = metaData.getColumnCount();
        for (int i = 1; i <= colCount; i++) {
            colNames.add(metaData.getColumnLabel(i));
        }
        modTbl.setColumnIdentifiers(colNames);
        
        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> rowData = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= colCount; columnIndex++) {
                rowData.add(rs.getObject(columnIndex));
            }
            modTbl.addRow(rowData);
        }
        obj.setModel(modTbl);
    }
}
