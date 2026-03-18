package com.betaproduct.Kalender;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import java.util.Calendar;

@DesignerComponent(
        version = 3,
        description = "Kalender Hybrid (Layout & Pop-up) - SKP Click Pro",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")
@SimpleObject(external = true)

public class Kalender extends AndroidNonvisibleComponent {
    private Context context;
    private CalendarView calendarView;

    public Kalender(ComponentContainer container) {
        super(container.$form());
        this.context = container.$context();
        this.calendarView = new CalendarView(context);
    }

    // --- FUNGSI 1: KALENDER DALAM LAYOUT (Lama) ---
    @SimpleFunction(description = "Menampilkan kalender ke dalam layout (Arrangement)")
    public void CreateCalendar(AndroidViewComponent view) {
        if (calendarView.getParent() != null) {
            ((ViewGroup) calendarView.getParent()).removeView(calendarView);
        }
        
        ViewGroup viewGroup = (ViewGroup) view.getView();
        viewGroup.addView(calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                DayChanged(year, month + 1, dayOfMonth);
            }
        });
    }

    // --- FUNGSI 2: KALENDER MELAYANG (Baru/Pop-up) ---
    @SimpleFunction(description = "Munculkan Kalender Pop-up Asli Android (Melayang)")
    public void TampilkanPopUp() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        DayChanged(year, monthOfYear + 1, dayOfMonth);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    @SimpleEvent(description = "Terpanggil saat tanggal dipilih (Berlaku untuk Pop-up & Layout)")
    public void DayChanged(int year, int month, int dayOfMonth) {
        EventDispatcher.dispatchEvent(this, "DayChanged", year, month, dayOfMonth);
    }

    @SimpleProperty
    public void Date(long value) {
        calendarView.setDate(value);
    }

    @SimpleProperty
    public long Date() {
        return calendarView.getDate();
    }
}
