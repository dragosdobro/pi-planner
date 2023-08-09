package com.demo.piplanner.domain.valueobject;

import static java.math.RoundingMode.HALF_EVEN;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.math.BigDecimal;
import java.util.StringJoiner;

public class Estimate {

  protected BigDecimal dev;

  protected BigDecimal ct;

  protected BigDecimal ft;

  public static final Estimate ZERO = builder().withDev(BigDecimal.ZERO).withCt(BigDecimal.ZERO)
      .withFt(BigDecimal.ZERO).build();

  public static Builder builder() {
    return Builder.newBuilder();
  }

  public Estimate add(final Estimate estimate) {
    return builder()
        .withDev(this.dev.add(estimate.dev))
        .withCt(this.ct.add(estimate.ct))
        .withFt(this.ft.add(estimate.ft))
        .build();
  }

  public Estimate subtract(final Estimate estimate) {
    return builder()
        .withDev(this.dev.subtract(estimate.dev))
        .withCt(this.ct.subtract(estimate.ct))
        .withFt(this.ft.subtract(estimate.ft))
        .build();
  }

  public Estimate subtractToZero(final Estimate estimate) {
//    return builder()
//        .withDev(this.dev > estimate.dev ? this.dev - estimate.dev : 0)
//        .withCt(this.ct > estimate.ct ? this.ct - estimate.ct : 0)
//        .withFt(this.ft > estimate.ft ? this.ft - estimate.ft : 0)
//        .build();
    return builder()
        .withDev(this.dev.subtract(estimate.dev).max(BigDecimal.ZERO))
        .withCt(this.ct.subtract(estimate.ct).max(BigDecimal.ZERO))
        .withFt(this.ft.subtract(estimate.ft).max(BigDecimal.ZERO))
        .build();
  }

  public boolean canFullyFitInside(final Estimate availableCapacity) {
    return this.dev.compareTo(availableCapacity.dev) <= 0
        && (this.dev.add(this.ct)).compareTo(availableCapacity.ct) <= 0
        && (this.dev.add(this.ft)).compareTo(availableCapacity.ft) <= 0;
  }

  public Estimate divide(final Estimate estimate) {
    return builder()
        .withDev(this.dev.divide(estimate.dev, HALF_EVEN))
        .withCt(this.ct.divide(estimate.ct, HALF_EVEN))
        .withFt(this.ft.divide(estimate.ft, HALF_EVEN))
        .build();
  }

  public Estimate multiply(final Estimate estimate) {
    return builder()
        .withDev(this.dev.multiply(estimate.dev))
        .withCt(this.ct.multiply(estimate.ct))
        .withFt(this.ft.multiply(estimate.ft))
        .build();
  }

  public Estimate percentageOf(final Estimate estimate) {
    return builder()
        .withDev(this.dev.multiply(new BigDecimal("100")).divide(estimate.dev, HALF_EVEN))
        .withCt(this.ct.multiply(new BigDecimal("100")).divide(estimate.ct, HALF_EVEN))
        .withFt(this.ft.multiply(new BigDecimal("100")).divide(estimate.ft, HALF_EVEN))
        .build();
  }

  public boolean greaterThan(final Estimate estimate) {
    return this.dev.compareTo(estimate.dev) > 0
        && this.ct.compareTo(estimate.ct) > 0
        && this.ft.compareTo(estimate.ft) > 0;
  }

  public boolean greaterOrEqualThan(final Estimate estimate) {
    return this.dev.compareTo(estimate.dev) >= 0
        && this.ct.compareTo(estimate.ct) >= 0
        && this.ft.compareTo(estimate.ft) >= 0;
  }

  public boolean lessThan(final Estimate estimate) {
    return this.dev.compareTo(estimate.dev) < 0
        && this.ct.compareTo(estimate.ct) < 0
        && this.ft.compareTo(estimate.ft) < 0;
  }

  public boolean lessOrEqualThan(final Estimate estimate) {
    return this.dev.compareTo(estimate.dev) <= 0
        && this.ct.compareTo(estimate.ct) <= 0
        && this.ft.compareTo(estimate.ft) <= 0;
  }

  public boolean isZero() {
    return this.dev.compareTo(BigDecimal.ZERO) == 0
        && this.ct.compareTo(BigDecimal.ZERO) == 0
        && this.ft.compareTo(BigDecimal.ZERO) == 0;
  }

  public BigDecimal sumUp() {
    return this.dev.add(this.ct).add(this.ft);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Estimate)) {
      return false;
    }

    final Estimate otherEstimate = (Estimate) o;

    return this.dev.compareTo(otherEstimate.dev) == 0
        && this.ct.compareTo(otherEstimate.ct) == 0
        && this.ft.compareTo(otherEstimate.ft) == 0;
  }

  @Override
  public int hashCode() {
    int result = dev != null ? dev.hashCode() : 0;
    result = 31 * result + (ct != null ? ct.hashCode() : 0);
    result = 31 * result + (ft != null ? ft.hashCode() : 0);
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

    private BigDecimal dev;
    private BigDecimal ct;
    private BigDecimal ft;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder withDev(final BigDecimal dev) {
      this.dev = defaultIfNull(dev, BigDecimal.ZERO);
      return this;
    }

    public Builder withCt(final BigDecimal ct) {
      this.ct = defaultIfNull(ct, BigDecimal.ZERO);
      return this;
    }

    public Builder withFt(final BigDecimal ft) {
      this.ft = defaultIfNull(ft, BigDecimal.ZERO);
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
