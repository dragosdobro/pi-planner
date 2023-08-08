package com.demo.piplanner.domain.valueobject;

import static org.apache.commons.lang3.StringUtils.CR;
import static org.apache.commons.lang3.StringUtils.LF;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.repeat;

import org.javatuples.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Feature extends Story {

  protected Deque<Story> children;

  public Feature setChildren(final Deque<Story> children) {
    this.children = children;
    return this;
  }

  protected Deque<Story> children() {
    return children;
  }

  @Override
  public Pair<? extends Story, ? extends Story> split(final Estimate availableCapacity) {
    // no op
    return null;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Feature)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    final Feature feature = (Feature) o;

    return Objects.equals(children, feature.children);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (children != null ? children.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {

    final StringJoiner joiner = new StringJoiner(CR + LF);
    joiner.add("FEATURE " + id + " - " + description + " " + estimate + " Rank: " + priority);
    joiner.add(repeat(SPACE, 4) + "STORIES");
    joiner.add(allStoriesToString());

    return joiner.toString();
  }

  private String allStoriesToString() {

    final StringJoiner joiner = new StringJoiner(CR + LF);
    children.stream().forEach(story -> joiner.add(repeat(SPACE, 8) + story));

    return joiner.toString();
  }

  public static Builder builder() {
    return Builder.newBuilder();
  }

  public static class Builder extends Story.Builder {

    protected Deque<Story> children;

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder withChildren(final List<Story> children) {
      children.sort(Story::compareTo);
      this.children = new LinkedList<>(children);
      return this;
    }

    public Feature build() {
      final Feature feature = new Feature();
      feature.isMultiIteration = super.isMultiIteration;
      feature.estimate = totalEstimation();
      feature.children = children;
      feature.id = super.id;
      feature.priority = super.priority;
      feature.description = super.description;
      feature.partNumber = super.partNumber;
      feature.originalDescription = super.originalDescription;
      return feature;
    }

    protected Estimate totalEstimation() {
      return children.stream().map(story -> story.estimate).reduce(Estimate.ZERO, Estimate::add);
    }
  }
}
