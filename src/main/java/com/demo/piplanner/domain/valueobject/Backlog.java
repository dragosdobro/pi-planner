package com.demo.piplanner.domain.valueobject;

import static org.apache.commons.lang3.StringUtils.CR;
import static org.apache.commons.lang3.StringUtils.LF;
import static org.apache.commons.lang3.StringUtils.repeat;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Backlog{

  protected String name;
  protected Deque<? extends Story> features;

  @Override
  public String toString() {

    final StringJoiner joiner = new StringJoiner(CR + LF);
    joiner.add("BACKLOG " + name + CR + LF);
    joiner.add("FEATURES" + CR + LF);
    joiner.add(allFeaturesToString());

    return joiner.toString();
  }

  private String allFeaturesToString() {

    final StringJoiner joiner = new StringJoiner(repeat(CR + LF, 2));
    features.stream().forEach(feature -> joiner.add(feature.toString()));

    return joiner.toString();
  }

  public static Builder builder(){
    return Builder.newBuilder();
  }

  public static final class Builder {

    private String name;
    private Deque<? extends Story> features;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder withName(final String name) {
      this.name = name;
      return this;
    }

    public Builder withFeatures(final List<? extends Story> features) {
      features.sort(Story::compareTo);
      this.features = new LinkedList<>(features);
      return this;
    }

    public Backlog build() {
      final Backlog backlog = new Backlog();
      backlog.features = this.features;
      backlog.name = this.name;
      return backlog;
    }
  }
}
