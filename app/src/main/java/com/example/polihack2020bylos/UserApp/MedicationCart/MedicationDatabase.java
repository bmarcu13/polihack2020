package com.example.polihack2020bylos.UserApp.MedicationCart;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.polihack2020bylos.Entities.Medication;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MedicationDatabase extends SQLiteAssetHelper {
    public static final String DB_NAME = "MedicationDatabase.db";
    public static final int DB_VER = 1;

    public MedicationDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Medication> getMedication() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id", "Name", "Price", "Quantity", "Description"};
        String sqlTable = "MedicationTable";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        List<Medication> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Medication medication = extractMedicationFromCursor(c);

                result.add(medication);
            } while (c.moveToNext());
        }

        return result;
    }

    private Medication extractMedicationFromCursor(Cursor c) {

        Medication medication = new Medication();
        medication.setMedicationId(c.getLong(c.getColumnIndex("Id")));
        medication.setMedicationName(c.getString(c.getColumnIndex("Name")));
        medication.setMedicationPrice(c.getDouble(c.getColumnIndex("Price")));
        medication.setMedicationQuantity(c.getInt(c.getColumnIndex("Quantity")));
        medication.setMedicationDescription(c.getString(c.getColumnIndex("Description")));

        return medication;
    }


    public void addToMedicationDatabase(Medication medication) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO MedicationTable(Id,Name,Price,Quantity,Description) VALUES('%s','%s','%s','%s','%s');",
                medication.getMedicationId(),
                medication.getMedicationName(),
                medication.getMedicationPrice(),
                medication.getMedicationQuantity(),
                medication.getMedicationDescription());
        db.execSQL(query);
    }

    public void cleanMedicationDatabase() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM MedicationTable");
        db.execSQL(query);
    }

    public void removeMedicationById(Long medicationId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Medicationtable where Id=%d", medicationId);
        db.execSQL(query);
    }

    public Medication getMedicationById(Long medicationId) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id", "Name", "Price", "Quantity", "Description"};
        String sqlTable = "MedicationTable";

        qb.setTables(sqlTable);
        String selection = String.format("Id=%d", medicationId);

        Cursor c = qb.query(db, sqlSelect, selection, null, null, null, null);

        Medication medication = null;

        if (c.moveToFirst()) {
            medication = extractMedicationFromCursor(c);
        }
        return medication;
    }


}
