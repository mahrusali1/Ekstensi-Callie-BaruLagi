package com.betaproduct.Kalender;

import android.app.DatePickerDialog;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import java.util.Calendar;

@DesignerComponent(
        version = 6, 
        description = "Kalender Material Luxury White Edition",
        category = ComponentCategory.EXTENSION,
        nonVisible = true)
@SimpleObject(external = true)

public class Kalender extends AndroidNonvisibleComponent {
    private Context context;

    public Kalender(ComponentContainer container) {
        super(container.$form());
        this.context = container.$context();
    }

    @SimpleFunction(description = "Munculkan Kalender Mewah Putih (Modern)")
    public void TampilkanPopUp() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Angka 4 atau 5 adalah kode untuk THEME_DEVICE_DEFAULT_LIGHT (Putih Modern)
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, 
            5, // INI KUNCINYA: Memaksa Tema Material Light
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    DayChanged(year, monthOfYear + 1, dayOfMonth);
                }
            }, year, month, day);

        datePickerDialog.show();
    }

    @SimpleEvent(description = "Hasil pilihan tanggal")
    public void DayChanged(int year, int month, int dayOfMonth) {
        EventDispatcher.dispatchEvent(this, "DayChanged", year, month, dayOfMonth);
    }
}
