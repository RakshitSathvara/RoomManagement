package com.fusionbit.roommanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<Adult> adultList = new ArrayList<>();
    private List<Child> childList = new ArrayList<>();

    public List<Adult> getAdultList() {
        return adultList;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();

        String childDetails = "Child Details: \n";
        String adultDetails = "Adult Details: \n";

        for (Child child : childList) {
            sb.append("Age => ");
            sb.append(String.valueOf(child.getAge()));
            sb.append("\n");
        }

        childDetails = childDetails + sb;

        for (Adult adult : adultList) {
            sb1.append("Age = ");
            sb1.append(String.valueOf(adult.getAge()));
            sb1.append("\n");
        }

        adultDetails = adultDetails + sb1;

        return adultDetails + childDetails;
    }
}
