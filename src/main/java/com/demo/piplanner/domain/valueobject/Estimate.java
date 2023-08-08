package com.demo.piplanner.domain.valueobject;

import java.util.StringJoiner;

public class Estimate {

  protected double dev;

  protected double ct;

  protected double ft;

  public static final Estimate ZERO = builder().withDev(0).withCt(0)
      .withFt(0).build();

  public static Builder builder() {
    return Builder.newBuilder();
  }

  public Estimate add(final Estimate estimate) {
    return builder()
        .withDev(this.dev + estimate.dev)
        .withCt(this.ct + estimate.ct)
        .withFt(this.ft + estimate.ft)
        .build();
  }

  public Estimate subtract(final Estimate estimate) {
    return builder()
        .withDev(this.dev - estimate.dev)
        .withCt(this.ct - estimate.ct)
        .withFt(this.ft - estimate.ft)
        .build();
  }

  public Estimate subtractToZero(final Estimate estimate) {
    return builder()
        .withDev(this.dev > estimate.dev ? this.dev - estimate.dev : 0)
        .withCt(this.ct > estimate.ct ? this.ct - estimate.ct : 0)
        .withFt(this.ft > estimate.ft ? this.ft - estimate.ft : 0)
        .build();
  }

  public boolean canFullyFitInside(final Estimate availableCapacity) {
    return this.dev <= availableCapacity.dev
        && (this.dev + this.ct) <= availableCapacity.ct
        && (this.dev + this.ft) <= availableCapacity.ft;
  }

  public Estimate divide(final Estimate estimate) {
    return builder()
        .withDev(this.dev / estimate.dev)
        .withCt(this.ct / estimate.ct)
        .withFt(this.ft / estimate.ft)
        .build();
  }

  public Estimate multiply(final Estimate estimate) {
    return builder()
        .withDev(this.dev * estimate.dev)
        .withCt(this.ct * estimate.ct)
        .withFt(this.ft * estimate.ft)
        .build();
  }

  public Estimate percentageOf(final Estimate estimate) {
    return builder()
        .withDev(this.dev * 100 / estimate.dev)
        .withCt(this.ct * 100 / estimate.ct)
        .withFt(this.ft * 100 / estimate.ft)
        .build();
  }

  public boolean greaterThan(final Estimate estimate) {
    return this.dev > estimate.dev && this.ct > estimate.ct && this.ft > estimate.ft;
  }

  public boolean greaterOrEqualThan(final Estimate estimate) {
    return this.dev >= estimate.dev && this.ct >= estimate.ct && this.ft >= estimate.ft;
  }

  public boolean lessThan(final Estimate estimate) {
    return this.dev < estimate.dev && this.ct < estimate.ct && this.ft < estimate.ft;
  }

  public boolean lessOrEqualThan(final Estimate estimate) {
    return this.dev <= estimate.dev && this.ct <= estimate.ct && this.ft <= estimate.ft;
  }

  public boolean isZero() {
    return this.dev == 0 && this.ct == 0 && this.ft == 0;
  }

  public double sumUp() {
    return this.dev + this.ct + this.ft;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Estimate estimate = (Estimate) o;

    if (Double.compare(estimate.dev, dev) != 0) {
      return false;
    }
    if (Double.compare(estimate.ct, ct) != 0) {
      return false;
    }
    return Double.compare(estimate.ft, ft) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(dev);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(ct);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(ft);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", "[", "]")
        .add("DEV=" + dev)
        .add("CT=" + ct)
        .add("FT=" + ft)
        .toString();
  }

  public static final class Builder {

    private double dev;
    private double ct;
    private double ft;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder withDev(final double dev) {
      this.dev = dev;
      return this;
    }

    public Builder withCt(final double ct) {
      this.ct = ct;
      return this;
    }

    public Builder withFt(final double ft) {
      this.ft = ft;
      return this;
    }

    public Estimate build() {
      final Estimate estimate = new Estimate();
      estimate.dev = this.dev;
      estimate.ct = this.ct;
      estimate.ft = this.ft;
      return estimate;
    }
  }
}
