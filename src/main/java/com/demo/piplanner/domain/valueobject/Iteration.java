package com.demo.piplanner.domain.valueobject;

import static org.apache.commons.lang3.StringUtils.CR;
import static org.apache.commons.lang3.StringUtils.LF;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.repeat;

import org.javatuples.Pair;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringJoiner;

public class Iteration {

  protected int number;

  protected Deque<Story> items;

  protected Estimate capacity;

  protected Estimate recommendedLoad;

  protected Estimate load;

  protected Estimate remainingCapacity;

  protected Estimate loadPercentage;

  public void planBacklogInIteration(final Backlog backlog) {
    planFeaturesInIteration((Deque<Feature>) backlog.features);
  }

  private void planFeaturesInIteration(final Deque<Feature> features) {

    final Deque<Feature> leftovers = new LinkedList<>();

    while (!features.isEmpty()) {
      final Feature feature = features.removeFirst();
      final Feature leftover = planFeatureInIteration(feature);

      if (leftover != null) {
        leftovers.add(leftover);
      }
    }

    features.addAll(leftovers);
  }

  private Feature planFeatureInIteration(final Feature feature) {

    planStoriesInIteration(feature.children);

    return feature.children.isEmpty() ? null : feature;
  }

  private void planStoriesInIteration(final Deque<Story> stories) {

    final Deque<Story> leftovers = new LinkedList<>();

    while (!stories.isEmpty()) {
      final Story story = stories.removeFirst();
      final Story leftover = planStoryInIteration(story);

      if (leftover != null) {
        leftovers.add(leftover);
      }
    }

    stories.addAll(leftovers);
  }

  private Story planStoryInIteration(final Story story) {

    Story leftovers = null;

    if (story.estimate.canFullyFitInside(remainingCapacity)) {
      planWholeStoryInIteration(story);
    } else if (canFitPartOfTheStory(story)) {
      leftovers = planPartOfTheStoryInIteration(story);
    } else {
      leftovers = story;
    }

    return leftovers;
  }

  private void planWholeStoryInIteration(final Story story) {
    doCapacityCalculations(story);
    items.add(story);
  }

  private void doCapacityCalculations(final Story story) {
    remainingCapacity = remainingCapacity.subtractToZero(story.estimate);
    load = load.add(story.estimate);
    loadPercentage = load.percentageOf(capacity);
  }

  public boolean canFitPartOfTheStory(final Story story) {
//    return (remainingCapacity.dev > 0 && story.estimate.dev > 0)
//        || (story.estimate.dev == 0 && remainingCapacity.ct > 0 && story.estimate.ct > 0)
//        || (story.estimate.dev == 0 && remainingCapacity.ft > 0 && story.estimate.ft > 0);

   return (remainingCapacity.dev.compareTo(BigDecimal.ZERO) > 0 && story.estimate.dev.compareTo(BigDecimal.ZERO) > 0)
        || (story.estimate.dev.compareTo(BigDecimal.ZERO) == 0 && remainingCapacity.ct.compareTo(BigDecimal.ZERO) > 0 && story.estimate.ct.compareTo(BigDecimal.ZERO) > 0)
        || (story.estimate.dev.compareTo(BigDecimal.ZERO) == 0 && remainingCapacity.ft.compareTo(BigDecimal.ZERO) > 0 && story.estimate.ft.compareTo(BigDecimal.ZERO) > 0);
  }

  private Story planPartOfTheStoryInIteration(final Story story) {

    final Pair<? extends Story, ? extends Story> storySplit = story.split(remainingCapacity);
    final Story storyThatFits = storySplit.getValue0();
    final Story storyThatDoesNotFit = storySplit.getValue1();

    doCapacityCalculations(storyThatFits);
    items.add(storyThatFits);

    return storyThatDoesNotFit;
  }

  public static Builder builder() {
    return Builder.newBuilder();
  }

  @Override
  public String toString() {

    final StringJoiner joiner = new StringJoiner(CR + LF);
    joiner.add(repeat(SPACE, 4) + "ITERATION " + number + CR + LF);
    joiner.add(repeat(SPACE, 4) + "STORIES" + CR + LF);
    joiner.add(allFeaturesToString());
    joiner.add(CR + LF + repeat(SPACE, 4) + "Capacity: " + capacity
        + CR + LF + repeat(SPACE, 4) + "Recommended Load: " + recommendedLoad
        + CR + LF + repeat(SPACE, 4) + "Load: " + load
        + CR + LF + repeat(SPACE, 4) + "Remaining Capacity: " + remainingCapacity
        + CR + LF + repeat(SPACE, 4) + "Load Sum: " + load.sumUp()
        + CR + LF/* + repeat(SPACE, 4) + "Load Percentage: " + loadPercentage + "%" + CR + LF*/);

    return joiner.toString();
  }

  private String allFeaturesToString() {

    final StringJoiner joiner = new StringJoiner(repeat(CR + LF, 2));
    items.forEach(feature -> joiner.add(repeat(SPACE, 8) + feature.toString()));

    return joiner.toString();
  }

  public static final class Builder {

    private int number;
    protected Estimate capacity;
    private Estimate recommendedLoad;
    private Estimate occupiedCapacity;
    private Estimate remainingCapacity;
    private Estimate loadPercentage;

    private Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder withNumber(final int number) {
      this.number = number;
      return this;
    }

    public Builder withRecommendedLoad(final Estimate totalRecommendedCapacity) {
      this.recommendedLoad = totalRecommendedCapacity;
      this.remainingCapacity = totalRecommendedCapacity;
      this.occupiedCapacity = Estimate.ZERO;
      this.loadPercentage = Estimate.ZERO;
      return this;
    }

    public Builder withCapacity(final Estimate capacity) {
      this.capacity = capacity;
      return this;
    }

    public Iteration build() {
      final Iteration iteration = new Iteration();
      iteration.items = new LinkedList<>();
      iteration.number = this.number;
      iteration.recommendedLoad = this.recommendedLoad;
      iteration.capacity = this.capacity;
      iteration.remainingCapacity = this.remainingCapacity;
      iteration.loadPercentage = this.loadPercentage;
      iteration.load = this.occupiedCapacity;
      return iteration;
    }
  }
}
