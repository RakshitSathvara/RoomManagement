package com.fusionbit.roommanagement.views;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.fusionbit.roommanagement.Constants;
import com.fusionbit.roommanagement.R;
import com.fusionbit.roommanagement.model.Adult;
import com.fusionbit.roommanagement.viewmodel.AdultViewModel;

import java.util.List;

public class AdultView extends LinearLayout {

    private Spinner spinSelectedAdults;

    private LinearLayout llAdultAgeContainer;

    private List<Adult> adultList;

    private int previousSelectedChildCount = 0;

    public AdultView(final Context context, final List<Adult> adultList) {
        super(context);

        this.adultList = adultList;

        inflate(context, R.layout.adult_container, this);


        llAdultAgeContainer = findViewById(R.id.ll_adultAgeContainer);

        spinSelectedAdults = findViewById(R.id.sp_adult);

        spinSelectedAdults.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Constants.NO_OF_ADULTS));

        spinSelectedAdults.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int currentSelectedChildCount, long l) {
                if (currentSelectedChildCount > previousSelectedChildCount) {
                    final int adultDetailsToBeAdded = currentSelectedChildCount - llAdultAgeContainer.getChildCount();
                    for (int i = 0; i < adultDetailsToBeAdded; i++) {
                        final Adult adult = new Adult();
                        adultList.add(adult);
                        llAdultAgeContainer.addView(new AdultViewModel(context, adult));
                    }
                } else if (currentSelectedChildCount < previousSelectedChildCount) {
                    for (int i = llAdultAgeContainer.getChildCount() - 1; i >= currentSelectedChildCount; i--) {
                        adultList.remove(i);
                        llAdultAgeContainer.removeViewAt(i);
                    }
                }


                previousSelectedChildCount = currentSelectedChildCount;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinSelectedAdults.setSelection(0);

    }

}
