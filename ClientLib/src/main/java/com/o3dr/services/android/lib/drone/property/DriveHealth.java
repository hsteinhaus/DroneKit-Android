package com.o3dr.services.android.lib.drone.property;

import android.os.Parcel;

/**
 * Reports the drivetrain health
 * Created by Holger Steinhaus on 3/9/16.
 */
public class DriveHealth implements DroneAttribute {
    /*
       Currently only mixer limits are implemented. Other information, e.g. current and temperature
       warnings from two-way capable ESCs will follow
     */

    /**
     * motor number following ArudCopters numbering convention (actual position may depend on frame type)
     */
    private int motorNumber;

    /**
     * true if ArduCopter reports an upper mixer limit condition
     */
    private boolean lowerLimit;

    /**
     * true if ArduCopter reports an upper mixer limit condition
     */
    private boolean upperLimit;

    public DriveHealth(){}

    public DriveHealth(int motorNumber, boolean lowerLimit, boolean upperLimit ) {
        this.motorNumber = motorNumber;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int getMotorNumber() { return motorNumber; }

    public void setMotorNumber(int motorNumber) {
        this.motorNumber = motorNumber;
    }

    public boolean getLowerLimit() { return lowerLimit; }

    public void setLowerLimit(boolean lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public boolean getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(boolean upperLimit) {
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriveHealth)) return false;

        DriveHealth driveHealth = (DriveHealth) o;

        if (motorNumber != driveHealth.motorNumber) return false;
        if (lowerLimit != driveHealth.lowerLimit) return false;
        if (upperLimit != driveHealth.upperLimit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31*motorNumber + (lowerLimit?1:0) + 2*(upperLimit?1:0);
    }

    @Override
    public String toString() {
        return "DriveHealth{" +
                "motorNumber=" + motorNumber +
                "lowerLimit=" + lowerLimit +
                "upperLimit=" + upperLimit +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) this.motorNumber);
        dest.writeByte((byte) (this.lowerLimit ? 1 : 0));
        dest.writeByte((byte) (this.upperLimit ? 1 : 0));
    }

    protected DriveHealth(Parcel in) {
        this.motorNumber= in.readByte();
        this.lowerLimit = in.readByte() == 1;
        this.upperLimit = in.readByte() == 1;
    }

    public static final Creator<DriveHealth> CREATOR = new Creator<DriveHealth>() {
        public DriveHealth createFromParcel(Parcel source) {
            return new DriveHealth(source);
        }

        public DriveHealth [] newArray(int size) {
            return new DriveHealth[size];
        }
    };
}
