package com.classes;

import java.util.Objects;

public class PlaceListenerImpl implements PlaceListener {
    //Called whenever the visible state of a place has changed
    public void placeChanged() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof RoadListener)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
}
