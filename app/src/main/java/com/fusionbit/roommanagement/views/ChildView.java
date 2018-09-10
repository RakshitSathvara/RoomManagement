package com.fusionbit.roommanagement.views;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.fusionbit.roommanagement.Constants;
import com.fusionbit.roommanagement.R;
import com.fusionbit.roommanagement.model.Child;
import com.fusionbit.roommanagement.viewmodel.ChildViewModel;

import java.util.List;

public class ChildView extends LinearLayout {

    private AppCompatSpinner spinSelectedChilds;

    private LinearLayout llChildAgeContainer;

    private List<Child> childList;

    private int previousSelectedChildCount = 0;

    public ChildView(final Context context, final List<Child> childList) {
        super(context);

        this.childList = childList;

        inflate(context, R.layout.
                child_container, this);


        llChildAgeContainer = findViewById(R.id.ll_childAgeContainer);

        spinSelectedChilds = findViewById(R.id.spin_selectedChilds);

        spinSelectedChilds.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Constants.NO_OF_CHILDS));

        spinSelectedChilds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int currentSelectedChildCount, long l) {
                if (currentSelectedChildCount > previousSelectedChildCount) {
                    //add room
                    final int childDetailsToBeAdded = currentSelectedChildCount - llChildAgeContainer.getChildCount();
                    for (int i = 0; i < childDetailsToBeAdded; i++) {
                        final Child child = new Child();
                        childList.add(child);
                        llChildAgeContainer.addView(new ChildViewModel(context, child));
                    }
                } else if (currentSelectedChildCount < previousSelectedChildCount) {
                    //remove room/s
                    for (int i = llChildAgeContainer.getChildCount() - 1; i >= currentSelectedChildCount; i--) {
                        childList.remove(i);
                        llChildAgeContainer.removeViewAt(i);
                    }
                }


                previousSelectedChildCount = currentSelectedChildCount;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinSelectedChilds.setSelection(0);

    }


}
