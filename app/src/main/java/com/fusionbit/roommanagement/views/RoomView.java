package com.fusionbit.roommanagement.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;

import com.fusionbit.roommanagement.model.Room;

public class RoomView extends LinearLayout {

    private AdultView adultView;

    private ChildView childView;

    private Room room;

    public RoomView(Context context, Room room) {
        super(context);

        this.room = room;

        setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setOrientation(VERTICAL);

        adultView = new AdultView(getContext(), room.getAdultList());
        childView = new ChildView(getContext(), room.getChildList());

        addView(adultView);
        addView(childView);

    }


}