package com.fusionbit.roommanagement.viewmodel;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.ViewGroup;

import com.fusionbit.roommanagement.model.Child;

public class ChildViewModel extends AppCompatEditText {

    private Child child;

    public ChildViewModel(Context context, Child child) {
        super(context);
        this.child = child;
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setHint("Enter Child Age");
        setInputType(InputType.TYPE_CLASS_TEXT);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (text.length() > 0) {
            try {
                int age = Integer.valueOf(text.toString());
                child.setAge(age);
            } catch (NumberFormatException e) {
                setError("Only Numbers Allowed");
            } catch (IllegalArgumentException e) {
                setError("Age must be below 18");
            }
        }
    }
}
