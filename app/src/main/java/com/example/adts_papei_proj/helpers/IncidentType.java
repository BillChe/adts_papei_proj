package com.example.adts_papei_proj.helpers;

import com.example.adts_papei_proj.R;

public enum IncidentType {

    EARTHQUAKE(String.valueOf(R.string.earthquake)),
    FLOOD(String.valueOf(R.string.flood)),
    HEAVY_RAIN(String.valueOf(R.string.heavy_rain)),
    FIRE(String.valueOf(R.string.fire));


    private String value;

    IncidentType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
