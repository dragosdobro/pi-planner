package com.demo.piplanner.domain.valueobject;

import static org.apache.commons.lang3.StringUtils.CR;
import static org.apache.commons.lang3.StringUtils.LF;

import java.util.List;
import java.util.StringJoiner;

public class ProductIncrement {
  private String name;
  private List<Iteration> iterations;

  public void planBacklogInIterations(final Backlog backlog) {
    for(final Iteration iteration : iterations) {
      iteration.planBacklogInIteration(backlog);
    }
  }

  @Override
  public String toString() {

    final StringJoiner joiner = new StringJoiner(CR + LF);
    joiner.add("PRODUCT INCREMENT " + name + CR + LF);
    joiner.add("ITERATIONS" + CR + LF);
    joiner.add(allIterationsToString());

    return joiner.toString();
  }

  private String allIterationsToString() {

    final StringJoiner joiner = new StringJoiner(CR + LF);
    iterations.stream().forEach(iteration -> joiner.add(iteration.toString()));

    return joiner.toString();
  }

  public static Builder builder() {
    return Builder.newBuilder();
  }

  public static final class Builder {

    private String name;
    private List<Iteration> iterations;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder withName(final String name) {
      this.name = name;
      return this;
    }

    public Builder withIterations(final List<Iteration> iterations) {
      this.iterations = iterations;
      return this;
    }

    public ProductIncrement build() {
      final ProductIncrement productIncrement = new ProductIncrement();
      productIncrement.iterations = this.iterations;
      productIncrement.name = this.name;
      return productIncrement;
    }
  }
}
