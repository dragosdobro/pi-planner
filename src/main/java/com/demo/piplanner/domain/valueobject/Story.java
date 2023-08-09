package com.demo.piplanner.domain.valueobject;

import org.javatuples.Pair;

import java.math.BigDecimal;
import java.util.Objects;

public class Story implements Comparable<Story> {

  protected String id;

  protected String originalDescription;

  protected String description;

  protected int priority;

  protected boolean isMultiIteration;

  protected int partNumber;

  protected String parent;

  protected Estimate estimate;

  public Pair<? extends Story, ? extends Story> split(final Estimate availableCapacity) {

    final Pair<? extends Story, ? extends Story> storySplit;

    if (estimate.canFullyFitInside(availableCapacity)) {
      storySplit = doNotSplitStory();
    } else {
      storySplit = splitStoryInTwoParts(availableCapacity);
    }

    return storySplit;
  }

  private Pair<? extends Story, ? extends Story> doNotSplitStory() {
    return Pair.with(this, null);
  }

  private Pair<? extends Story, ? extends Story> splitStoryInTwoParts(
      final Estimate availableCapacity) {

    final Story firstStory = Story.builder()
        .withId(id)
        .withOriginalDescription(originalDescriptionByMultiIteration())
        .withPartNumber(partNumberByMultiIterationForFirstStory())
        .withDescription(storyDescriptionWithPartsInformationForFirstStory())
        .withParent(parent)
        .withIsMultiIteration(true)
        .withPriority(priority)
        .withEstimate(Estimate.builder()
            .withDev(firstHalfStoryDevEstimate(estimate, availableCapacity))
            .withCt(firstHalfStoryCtEstimate(estimate, availableCapacity))
            .withFt(firstHalfStoryFtEstimate(estimate, availableCapacity))
            .build())
        .build();

    final Story secondStory = Story.builder()
        .withId(id)
        .withOriginalDescription(originalDescriptionByMultiIteration())
        .withPartNumber(partNumberByMultiIterationForSecondStory())
        .withDescription(storyDescriptionWithPartsInformationForSecondStory())
        .withParent(parent)
        .withIsMultiIteration(true)
        .withPriority(priority)
        .withEstimate(Estimate.builder()
            .withDev(secondHalfStoryDevEstimate(estimate, availableCapacity))
            .withCt(secondHalfStoryCtEstimate(estimate, availableCapacity))
            .withFt(secondHalfStoryFtEstimate(estimate, availableCapacity))
            .build())
        .build();

    return Pair.with(firstStory, secondStory);
  }

  private BigDecimal firstHalfStoryDevEstimate(final Estimate originalEstimate,
      final Estimate availableCapacity) {

    //return min(originalEstimate.dev, availableCapacity.dev);
    return originalEstimate.dev.min(availableCapacity.dev);
  }

  private BigDecimal firstHalfStoryCtEstimate(final Estimate originalEstimate,
      final Estimate availableCapacity) {

    BigDecimal ctEstimate = BigDecimal.ZERO;
//
//    if (originalEstimate.dev > availableCapacity.dev) {
//      ctEstimate = 0;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev >= availableCapacity.ct) {
//      ctEstimate = 0;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev < availableCapacity.ct
//        && originalEstimate.dev + originalEstimate.ct > availableCapacity.ct) {
//      ctEstimate = availableCapacity.ct - originalEstimate.dev;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev < availableCapacity.ct
//        && originalEstimate.dev + originalEstimate.ct <= availableCapacity.ct) {
//      ctEstimate = originalEstimate.ct;
//    }

    if (originalEstimate.dev.compareTo(availableCapacity.dev) > 0) {
      ctEstimate = BigDecimal.ZERO;
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ct) >= 0) {
      ctEstimate = BigDecimal.ZERO;
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ct) < 0
        && originalEstimate.dev.add(originalEstimate.ct).compareTo(availableCapacity.ct) > 0) {
      ctEstimate = availableCapacity.ct.subtract(originalEstimate.dev);
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ct) < 0
        && originalEstimate.dev.add(originalEstimate.ct).compareTo(availableCapacity.ct) <= 0) {
      ctEstimate = originalEstimate.ct;
    }

    return ctEstimate;
  }

  private BigDecimal firstHalfStoryFtEstimate(final Estimate originalEstimate,
      final Estimate availableCapacity) {

    BigDecimal ftEstimate = BigDecimal.ZERO;

//    if (originalEstimate.dev > availableCapacity.dev) {
//      ftEstimate = 0;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev >= availableCapacity.ft) {
//      ftEstimate = 0;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev < availableCapacity.ft
//        && originalEstimate.dev + originalEstimate.ft > availableCapacity.ft) {
//      ftEstimate = availableCapacity.ft - originalEstimate.dev;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev < availableCapacity.ft
//        && originalEstimate.dev + originalEstimate.ft <= availableCapacity.ft) {
//      ftEstimate = originalEstimate.ft;
//    }

    if (originalEstimate.dev.compareTo(availableCapacity.dev) > 0) {
      ftEstimate = BigDecimal.ZERO;
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ft) >= 0) {
      ftEstimate = BigDecimal.ZERO;
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ft) < 0
        && originalEstimate.dev.add(originalEstimate.ft).compareTo(availableCapacity.ft) > 0) {
      ftEstimate = availableCapacity.ft.subtract(originalEstimate.dev);
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ft) < 0
        && originalEstimate.dev.add(originalEstimate.ft).compareTo(availableCapacity.ft) <= 0) {
      ftEstimate = originalEstimate.ft;
    }

    return ftEstimate;
  }

  private BigDecimal secondHalfStoryDevEstimate(final Estimate originalEstimate,
      final Estimate availableCapacity) {
    //return originalEstimate.dev > availableCapacity.dev ? originalEstimate.dev -
    // availableCapacity.dev : 0;
    return originalEstimate.dev.compareTo(availableCapacity.dev) > 0
        ? originalEstimate.dev.subtract(availableCapacity.dev) : BigDecimal.ZERO;
  }

  private BigDecimal secondHalfStoryCtEstimate(final Estimate originalEstimate,
      final Estimate availableCapacity) {

    BigDecimal ctEstimate = BigDecimal.ZERO;

//    if (originalEstimate.dev > availableCapacity.dev) {
//      ctEstimate = originalEstimate.ct;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev >= availableCapacity.ct) {
//      ctEstimate = originalEstimate.ct;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev < availableCapacity.ct
//        && originalEstimate.dev + originalEstimate.ct > availableCapacity.ct) {
//      ctEstimate = originalEstimate.ct - (availableCapacity.ct - originalEstimate.dev);
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev < availableCapacity.ct
//        && originalEstimate.dev + originalEstimate.ct <= availableCapacity.ct) {
//      ctEstimate = 0;
//    }

    if (originalEstimate.dev.compareTo(availableCapacity.dev) > 0) {
      ctEstimate = originalEstimate.ct;
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ct) >= 0) {
      ctEstimate = originalEstimate.ct;
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ct) < 0
        && originalEstimate.dev.add(originalEstimate.ct).compareTo(availableCapacity.ct) > 0) {
      ctEstimate = originalEstimate.ct.subtract(availableCapacity.ct.subtract(originalEstimate.dev));
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ct) < 0
        && originalEstimate.dev.add(originalEstimate.ct).compareTo(availableCapacity.ct) <= 0) {
      ctEstimate = BigDecimal.ZERO;
    }

    return ctEstimate;
  }

  private BigDecimal secondHalfStoryFtEstimate(final Estimate originalEstimate,
      final Estimate availableCapacity) {

    BigDecimal ftEstimate = BigDecimal.ZERO;

//    if (originalEstimate.dev > availableCapacity.dev) {
//      ftEstimate = originalEstimate.ft;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev >= availableCapacity.ft) {
//      ftEstimate = originalEstimate.ft;
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev < availableCapacity.ft
//        && originalEstimate.dev + originalEstimate.ft > availableCapacity.ft) {
//      ftEstimate = originalEstimate.ft - (availableCapacity.ft - originalEstimate.dev);
//    } else if (originalEstimate.dev <= availableCapacity.dev
//        && originalEstimate.dev < availableCapacity.ft
//        && originalEstimate.dev + originalEstimate.ft <= availableCapacity.ft) {
//      ftEstimate = 0;
//    }

    if (originalEstimate.dev.compareTo(availableCapacity.dev) > 0) {
      ftEstimate = originalEstimate.ft;
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ft) >= 0) {
      ftEstimate = originalEstimate.ft;
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ft) < 0
        && originalEstimate.dev.add(originalEstimate.ft).compareTo(availableCapacity.ft) > 0) {
      ftEstimate = originalEstimate.ft.subtract(availableCapacity.ft.subtract(originalEstimate.dev));
    } else if (originalEstimate.dev.compareTo(availableCapacity.dev) <= 0
        && originalEstimate.dev.compareTo(availableCapacity.ft) < 0
        && originalEstimate.dev.add(originalEstimate.ft).compareTo(availableCapacity.ft) <= 0) {
      ftEstimate = BigDecimal.ZERO;
    }

    return ftEstimate;
  }

  private String originalDescriptionByMultiIteration() {
    return isMultiIteration ? originalDescription : description;
  }

  private int partNumberByMultiIterationForFirstStory() {

    int newPartNumber = 0;

    if (!isMultiIteration && partNumber == 0) {
      newPartNumber = partNumber + 1;
    } else if (isMultiIteration && partNumber > 0) {
      newPartNumber = partNumber;
    }

    return newPartNumber;
  }

  private String storyDescriptionWithPartsInformationForFirstStory() {
    return isMultiIteration ? originalDescription + " Part "
        + partNumberByMultiIterationForFirstStory()
        : description + " Part " + partNumberByMultiIterationForFirstStory();
  }

  private int partNumberByMultiIterationForSecondStory() {

    int newPartNumber = 0;

    if (!isMultiIteration && partNumber == 0) {
      newPartNumber = partNumber + 2;
    } else if (isMultiIteration && partNumber > 0) {
      newPartNumber = partNumber + 1;
    }

    return newPartNumber;
  }

  private String storyDescriptionWithPartsInformationForSecondStory() {
    return isMultiIteration ? originalDescription + " Part "
        + partNumberByMultiIterationForSecondStory()
        : description + " Part " + partNumberByMultiIterationForSecondStory();
  }

  @Override
  public String toString() {
    return "STORY " + id + " - " + description + " " + estimate + " Rank: " + priority
        + " Parent Feature: " + parent;
  }

  @Override
  public int compareTo(final Story that) {

    if (this.priority != that.priority) {
      return (this.priority < that.priority ? -1 : 1);
    }

    if (this.id == null && that.id == null) {
      return 0;
    } else if (this.id == null) {
      return -1;
    } else if (that.id == null) {
      return 1;
    } else {
      return this.id.compareTo(that.id);
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final Story story = (Story) o;

    if (priority != story.priority) {
      return false;
    }
    if (isMultiIteration != story.isMultiIteration) {
      return false;
    }
    if (partNumber != story.partNumber) {
      return false;
    }
    if (!Objects.equals(id, story.id)) {
      return false;
    }
    if (!Objects.equals(originalDescription, story.originalDescription)) {
      return false;
    }
    if (!Objects.equals(description, story.description)) {
      return false;
    }
    return Objects.equals(estimate, story.estimate);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (originalDescription != null ? originalDescription.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + priority;
    result = 31 * result + (isMultiIteration ? 1 : 0);
    result = 31 * result + partNumber;
    result = 31 * result + (estimate != null ? estimate.hashCode() : 0);
    return result;
  }

  public static Builder builder() {
    return Builder.newBuilder();
  }

  public static class Builder {

    protected String id;
    protected String originalDescription;
    protected String description;
    protected String parent;
    protected int priority;
    protected boolean isMultiIteration;
    protected int partNumber;
    private Estimate estimate;

    protected Builder() {
    }

    public static Builder newBuilder() {
      return new Builder();
    }


    public Builder withId(final String id) {
      this.id = id;
      return this;
    }

    protected Builder withOriginalDescription(final String originalDescription) {
      this.originalDescription = originalDescription;
      return this;
    }

    public Builder withDescription(final String description) {
      this.description = description;
      return this;
    }

    public Builder withParent(final String parent) {
      this.parent = parent;
      return this;
    }

    public Builder withPriority(final int priority) {
      this.priority = priority;
      return this;
    }

    protected Builder withIsMultiIteration(final boolean isMultiIteration) {
      this.isMultiIteration = isMultiIteration;
      return this;
    }

    protected Builder withPartNumber(final int partNumber) {
      this.partNumber = partNumber;
      return this;
    }

    public Builder withEstimate(final Estimate estimate) {
      this.estimate = estimate;
      return this;
    }

    public Story build() {
      final Story story = new Story();
      story.isMultiIteration = this.isMultiIteration;
      story.estimate = this.estimate;
      story.id = this.id;
      story.priority = this.priority;
      story.description = this.description;
      story.parent = this.parent;
      story.partNumber = this.partNumber;
      story.originalDescription = this.originalDescription;
      return story;
    }
  }
}
