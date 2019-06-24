package client;

import client.localization.LocalizationManager;
import moominClasses.Moomin;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Vector;

public class MoominTable extends JTable {
    private static LocalizationManager localizationManager = ClientApp.localizationManager;

    private static MoominModel moominModel = new MoominModel();
    private static TableRowSorter<TableModel> sorter;

    @Override
    public int convertRowIndexToView(int modelRowIndex) {
        return super.convertRowIndexToView(modelRowIndex);
    }

    MoominTable() {
        this.setModel(moominModel);

        for (Moomin moomin : ClientApp.moomins) {
            moominModel.addMoomin(moomin);
        }

        sorter = new TableRowSorter<>(moominModel);
        this.setRowSorter(sorter);

        this.getSelectionModel().addListSelectionListener(e -> {
            int rowIndex = this.getSelectedRow();

            if (rowIndex == -1) return;

            if (moominModel.isCellEditable(sorter.convertRowIndexToModel(rowIndex))) {
                this.setSelectionBackground(new Color(55, 144, 88));
            } else {
                this.setSelectionBackground(Color.GRAY);
            }

            ClientApp.setMoomin(moominModel.getMoominAtRow(rowIndex), moominModel.isCellEditable(sorter.convertRowIndexToModel(rowIndex)));
        });
    }

    void updateModel() {
        moominModel.updateData();
    }

    static class MoominModel extends AbstractTableModel {
        static final int columnCount = 9;

        private Vector<Moomin> data;

        MoominModel() {
            data = new Vector<>();
        }

        void updateData() {
            data.clear();

            for (Moomin moomin : ClientApp.moomins) {
                addMoomin(moomin);
            }
        }

        void addMoomin(Moomin moomin) {
            data.add(moomin);
            this.fireTableDataChanged();
        }


        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        boolean isCellEditable(int rowIndex) {
            int own = ClientApp.clientId;
            return data.get(rowIndex).getOwnerId() == own;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return localizationManager.getNativeTitle(LocalizationManager.TITLE_NAME);
                case 1:
                    return localizationManager.getNativeTitle(LocalizationManager.TITLE_TYPE);
                case 2:
                    return localizationManager.getNativeTitle(LocalizationManager.TITLE_FIELD);
                case 3:
                    return localizationManager.getNativeTitle(LocalizationManager.TITLE_GENDER);
                case 4:
                    return localizationManager.getNativeTitle(LocalizationManager.TITLE_CONDITION);
                case 5:
                    return localizationManager.getNativeTitle(LocalizationManager.TITLE_POSITION);
                case 6:
                    return localizationManager.getNativeTitle(LocalizationManager.TITLE_TIME);
                case 7:
                    return "X";
                case 8:
                    return "Y";
            }

            return "";
        }

        @Override
        public Class<?> getColumnClass(int column) {
            Class returnValue;
            if ((column == 7) || (column == 8)) {
                returnValue = Double.class;
            } else if ((column >= 0) && (column < getColumnCount())) {
                returnValue = String.class;
            } else {
                returnValue = Object.class;
            }
            return returnValue;
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        private Moomin getMoominAtRow(int row) {
            return data.get(sorter.convertRowIndexToModel(row));
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Moomin moomin = data.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return moomin.getName();
                case 1:
                    return moomin.getType().toString();
                case 2:
                    return moomin.getField().toString();
                case 3:
                    return moomin.getGender().toString();
                case 4:
                    return moomin.getCondition().toString();
                case 5:
                    return moomin.getPosition().toString();
                case 6:
                    return moomin.getCreationTime().toString();
                case 7:
                    return moomin.getX();
                case 8:
                    return moomin.getY();
                default:
                    return null;
            }
        }
    }
}

