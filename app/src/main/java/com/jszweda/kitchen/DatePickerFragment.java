package com.jszweda.kitchen;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

   private int day = 0, year = 0, month = 0;
   private OnDateSelectedListener dateSelectedListener;

   public void setterOnDateSelectedListener(OnDateSelectedListener listener) {
      this.dateSelectedListener = listener;
   }

   @NonNull
   @Override
   public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
      Calendar calendar = Calendar.getInstance();
      int day = calendar.get(Calendar.DAY_OF_MONTH);
      int month = calendar.get(Calendar.MONTH);
      int year = calendar.get(Calendar.YEAR);

      return new DatePickerDialog(requireContext(), this, year, month, day);
   }

   @Override
   public void onDateSet(DatePicker datePicker, int year, int month, int day) {

      if (dateSelectedListener != null) {
         dateSelectedListener.onDateSelected(year, month, day);
      }

      Toast.makeText(getContext(), "Date set", Toast.LENGTH_SHORT).show();
      this.year = year;
      this.month = month;
      this.day = day;
   }

   public int getDay() {
      return day;
   }

   public void setDay(int day) {
      this.day = day;
   }

   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public int getMonth() {
      return month;
   }

   public void setMonth(int month) {
      this.month = month;
   }
}

