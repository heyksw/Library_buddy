package com.inha.longstone;

import com.google.android.gms.maps.model.LatLng;

public class MarkerItem {
    LatLng latLng;
    String univ;

    public MarkerItem(LatLng latLng, String univ){
        this.latLng = latLng;
        this.univ = univ;
    }

}
