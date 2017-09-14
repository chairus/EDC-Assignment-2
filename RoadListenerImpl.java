package com.classes;

import java.util.Objects;

public class RoadListenerImpl implements RoadListener {
    //Called whenever the visible state of a road has changed
    public void roadChanged() {

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
