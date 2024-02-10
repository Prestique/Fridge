package com.jszweda.kitchen;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import androidx.fragment.app.FragmentManager;

class HelperClass {

   public static void saveFood(EditText etQuantity, EditText etWeight, EditText etFoodName,
                               TextView tvExpDate, Context context, MyViewModel myViewModel){

      String quantity = etQuantity.getText().toString().strip();
      String weight = etWeight.getText().toString().strip();
      String foodName = etFoodName.getText().toString().strip();
      String stringExpDate = tvExpDate.getText().toString().strip();
      LocalDate expDate = LocalDate.now();

      String regexDate = "\\d{1,2}-\\d{1,2}-\\d{4,}";
      boolean matchesDate = stringExpDate.matches(regexDate);
      if (matchesDate) {
         expDate = LocalDate.parse(tvExpDate.getText().toString(), DateTimeFormatter.ofPattern("d-M-yyyy"));
      } else {
         tvExpDate.setText("Wybierz datę");
      }

      if (isFieldEmpty(etFoodName,"Wprowadź jedzenie")&&
              isFieldEmpty(etWeight,"Wprowadź wagę")&&
              isFieldEmpty(etQuantity,"Wybierz ilość") && matchesDate) {
         myViewModel.addFood(new Food(foodName,expDate,Integer.parseInt(weight), Integer.parseInt(quantity)));
         Toast.makeText(context, "Dodano "+ foodName, Toast.LENGTH_SHORT).show();
      }
   }

   public static void editFood(EditText etQuantity, EditText etWeight, EditText etFoodName,
                               TextView tvExpDate, Food selectedFood, MyViewModel myViewModel){

      String quantity = etQuantity.getText().toString().strip();
      String weight = etWeight.getText().toString().strip();
      String foodName = etFoodName.getText().toString().strip();
      String stringExpDate = tvExpDate.getText().toString().strip();
      LocalDate expDate = LocalDate.now();

      String regexDate = "\\d{1,2}-\\d{1,2}-\\d{4,}";
      boolean matchesDate = stringExpDate.matches(regexDate);
      if (matchesDate) {
         expDate = LocalDate.parse(tvExpDate.getText().toString(), DateTimeFormatter.ofPattern("d-M-yyyy"));
      } else {
         tvExpDate.setText("Wybierz datę");
      }

      if (isFieldEmpty(etQuantity,"Wprowadź ilość")&&
              isFieldEmpty(etWeight,"Wprowadź wagę")&&
              isFieldEmpty(etQuantity,"Wybierz ilość") && matchesDate) {
         selectedFood.setExpirationDate(expDate);
         selectedFood.setFoodName(foodName);
         selectedFood.setQuantity(Integer.parseInt(quantity));
         selectedFood.setWeight(Integer.parseInt(weight));
         myViewModel.updateFood(selectedFood);
      }
   }

   private static boolean isFieldEmpty(TextView view, String message){
      if (TextUtils.isEmpty(view.getText().toString())){
         view.setError(message);
         return false;
      }
      return true;
   }

   public static void setDateOnTextView(DatePickerFragment dpf, TextView textView, FragmentManager fragmentManager){

      dpf.setterOnDateSelectedListener(new OnDateSelectedListener() {
         @Override
         public void onDateSelected(int year, int month, int day) {
            String selectedDate = day + "-" + (month+1) + "-" + year;
            textView.setText(selectedDate);
         }
      });
      dpf.show(fragmentManager, "Wybierz datę:");
   }

}
