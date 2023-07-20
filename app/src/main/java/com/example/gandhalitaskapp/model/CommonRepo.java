package com.example.gandhalitaskapp.model;

import java.util.ArrayList;
import java.util.List;

public class CommonRepo {
    public List<Facility> facilities;
    public List<List<Object>> exclusions;

    public boolean isExpand;

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<List<Object>> getExclusions() {
        return exclusions;
    }

    public void setExclusions(List<List<Object>> exclusions) {
        this.exclusions = exclusions;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
