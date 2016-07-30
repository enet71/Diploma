package com.diploma.dataLoad;

import com.diploma.dataBase.Connect;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.imageio.ImageIO;
import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Export {
    public static void dataFile(File file) throws IOException {
        Connect connect = new Connect();
        try {
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM WAVE");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    bufferedWriter.write(resultSet.getString(i) + "#");
                    System.out.println(resultSet.getFetchSize());

                }
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connect.closeConnect();
        }

    }

    public static void excelFile(File file) throws IOException, SQLException {
        Workbook wb = new HSSFWorkbook();
        Sheet personSheet = wb.createSheet("Wave List");
        Row headerRow = personSheet.createRow(0);
        Cell idHeaderCell = headerRow.createCell(0);
        Cell sensorHeaderCell = headerRow.createCell(1);
        Cell timewaveHeaderCell = headerRow.createCell(2);
        Cell depthwaveHeaderCell = headerRow.createCell(3);
        Cell energyHeaderCell = headerRow.createCell(4);
        Cell tapewaveHeaderCell = headerRow.createCell(5);
        Cell magnitudeHeaderCell = headerRow.createCell(6);

        idHeaderCell.setCellValue("Id");
        sensorHeaderCell.setCellValue("Sensor");
        timewaveHeaderCell.setCellValue("Time");
        depthwaveHeaderCell.setCellValue("Depth");
        energyHeaderCell.setCellValue("Energy");
        tapewaveHeaderCell.setCellValue("Type");
        magnitudeHeaderCell.setCellValue("Magnitude");

        Connect connect = new Connect();
        ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM WAVE");

        int row = 1;
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int sensor = resultSet.getInt(2);
            String timewave = resultSet.getString(3);
            int depthwave = resultSet.getInt(4);
            int energy = resultSet.getInt(5);
            String tapewave = resultSet.getString(6);
            int magnitude = resultSet.getInt(7);

            Row dataRow = personSheet.createRow(row);

            Cell dataIdCell = dataRow.createCell(0);
            dataIdCell.setCellValue(id);

            Cell dataSensorCell = dataRow.createCell(1);
            dataSensorCell.setCellValue(sensor);

            Cell dataTimeWaveCell = dataRow.createCell(2);
            dataTimeWaveCell.setCellValue(timewave);

            Cell dataDepthWaveCell = dataRow.createCell(3);
            dataDepthWaveCell.setCellValue(depthwave);

            Cell dataEnergyCell = dataRow.createCell(4);
            dataEnergyCell.setCellValue(energy);

            Cell dataTypeWaveCell = dataRow.createCell(5);
            dataTypeWaveCell.setCellValue(tapewave);

            Cell dataMegnitudeCell = dataRow.createCell(6);
            dataMegnitudeCell.setCellValue(magnitude);

            row = row + 1;
        }

        FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
        wb.write(fileOut);
        fileOut.close();
    }

    public static void imageFile(File file, BorderPane borderPane) throws IOException {
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                Platform.runLater(() -> {
                    try {
                        WritableImage wim = new WritableImage((int)borderPane.getWidth(),(int) borderPane.getHeight());
                        SnapshotParameters snapshotParameters = new SnapshotParameters();
                        borderPane.snapshot(snapshotParameters, wim);
                        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "data/png", file);
                    } catch (Exception s) {
                    }
                });

                return null;
            }
        };
        Thread th = new Thread(task);
        th.start();
    }
}
