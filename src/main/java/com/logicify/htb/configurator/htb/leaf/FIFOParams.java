package com.logicify.htb.configurator.htb.leaf;

import com.logicify.htb.configurator.htb.DefaultHTBClassValues;
import com.logicify.htb.configurator.htb.SpeedInBytes;

/**
 * this class keeps parameters for simple FIFO queueing disciplines
 */
public class FIFOParams {
    SpeedInBytes limit;//Number of packets/bytes the queue can hold

    public FIFOParams() {
        limit = new SpeedInBytes();
        this.limit.setSpeed(DefaultHTBClassValues.DEFAULT_LIMIT_SPEED);
        this.limit.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
    }

    public FIFOParams(SpeedInBytes limit) {

        if (limit.getSpeed() < 0) {
            this.limit = new SpeedInBytes();
            this.limit.setSpeed(DefaultHTBClassValues.DEFAULT_LIMIT_SPEED);
            this.limit.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
        } else this.limit = limit;
    }

    public SpeedInBytes getLimit() {
        return limit;
    }

    public void setLimit(SpeedInBytes limit) {
        if (limit.getSpeed() < 0) {
            this.limit.setSpeed(DefaultHTBClassValues.DEFAULT_LIMIT_SPEED);
            this.limit.setUnit(DefaultHTBClassValues.DEFAULT_SPEED_UNIT);
        } else this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FIFOParams that = (FIFOParams) o;

        if (limit != null ? !limit.equals(that.limit) : that.limit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return limit != null ? limit.hashCode() : 0;
    }
}
