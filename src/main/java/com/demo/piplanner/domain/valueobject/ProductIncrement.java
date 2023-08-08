package com.demo.piplanner.domain.valueobject;

import static com.demo.piplanner.domain.valueobject.Estimate.ZERO;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Stream.concat;
import static org.apache.commons.lang3.StringUtils.CR;
import static org.apache.commons.lang3.StringUtils.LF;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class ProductIncrement {

  private String name;
  private List<Iteration> iterations;

  private Map<String, Estimate> backlogItemEstimatesTallyBeforePlanning;

  private Map<String, Estimate> piItemEstimatesTallyAfterPlanning;

  public void planBacklogInIterations(final Backlog backlog) {
    backlogItemEstimatesTallyBeforePlanning = backlog.itemEstimatesTally();

    for (final Iteration iteration : iterations) {
      iteration.planBacklogInIteration(backlog);
    }

    piItemEstimatesTallyAfterPlanning = itemEstimatesTally(backlog);
  }

  protected Map<String, Estimate> itemEstimatesTally(final Backlog unplannedBacklog) {

    final Stream<Story> plannedStoriesOrDefects = iterations.stream()
        .flatMap(iteration -> iteration.items.stream());
    final Stream<Story> notPlannedStoriesOrDefects = unplannedBacklog.features.stream()
        .map(feature -> (Feature) feature)
        .flatMap(feature -> feature.children().stream());

    return concat(plannedStoriesOrDefects, notPlannedStoriesOrDefects)
        .collect(groupingBy(storyOrDefect -> storyOrDefect.id))
        .entrySet().stream()
        .collect(
            toMap(Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(storyOrDefect -> storyOrDefect.estimate)
                    .reduce(ZERO, Estimate::add)
            ));
  }

  public boolean piPlanningIntegrityCheck() {

    final MapDifference<String, Estimate> difference = Maps.difference(
        backlogItemEstimatesTallyBeforePlanning, piItemEstimatesTallyAfterPlanning);


    System.out.println("Estimates that differ Backlog vs PI: ");
    difference.entriesDiffering()
        .forEach((key, value) -> System.out.println("key: " + key + " value: " + value));

    System.out.println("Estimates that are only in Backlog: ");
    difference.entriesOnlyOnLeft()
        .forEach((key, value) -> System.out.println("key: " + key + " value: " + value));

    System.out.println("Estimates that are only in PI: ");
    difference.entriesOnlyOnRight()
        .forEach((key, value) -> System.out.println("key: " + key + " value: " + value));

    return backlogItemEstimatesTallyBeforePlanning.equals(piItemEstimatesTallyAfterPlanning);
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
    iterations.forEach(iteration -> joiner.add(iteration.toString()));

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
