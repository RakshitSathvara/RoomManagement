package com.fusionbit.roommanagement.viewmodel;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fusionbit.roommanagement.model.Adult;

public class AdultViewModel extends AppCompatEditText {

    private Adult adult;

    public AdultViewModel(Context context, Adult adult) {
        super(context);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        this.adult = adult;
        setHint("Enter Age");
        setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (text.length() > 0) {
            try {
                int age = Integer.valueOf(text.toString());
                adult.setAge(age);
            } catch (NumberFormatException e) {
                setError("Only Numbers Allowed");
            } catch (IllegalArgumentException e) {
                setError("Age must be above 18");
            }
        }
    }
}
