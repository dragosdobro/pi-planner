package com.demo.piplanner.domain.valueobject;

import org.javatuples.Pair;

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

  public Pair<? extends Story, ? extends Story> split(final Estimate remainingCapacity) {

    final Pair<? extends Story, ? extends Story> storySplit;

    if (estimate.lessOrEqualThan(remainingCapacity)) {
      storySplit = doNotSplitStory();
    } else {
      storySplit = splitStoryInTwoParts(remainingCapacity);
    }

    return storySplit;
  }

  private Pair<? extends Story, ? extends Story> doNotSplitStory() {
    return Pair.with(this, null);
  }

  private Pair<? extends Story, ? extends Story> splitStoryInTwoParts(final Estimate availableCapacity) {

    final Story firstStory = Story.builder()
        .withId(id)
        .withOriginalDescription(originalDescriptionByMultiIteration())
        .withPartNumber(partNumberByMultiIterationForFirstStory())
        .withDescription(storyDescriptionWithPartsInformationForFirstStory())
        .withParent(parent)
        .withIsMultiIteration(true)
        .withPriority(priority)
        .withEstimate(availableCapacity)
        .build();

    final Story secondStory = Story.builder()
        .withId(id)
        .withOriginalDescription(originalDescriptionByMultiIteration())
        .withPartNumber(partNumberByMultiIterationForSecondStory())
        .withDescription(storyDescriptionWithPartsInformationForSecondStory())
        .withParent(parent)
        .withIsMultiIteration(true)
        .withPriority(priority)
        .withEstimate(estimate.subtract(availableCapacity))
        .build();

    return Pair.with(firstStory, secondStory);
  }

  private String originalDescriptionByMultiIteration() {
    return isMultiIteration ? originalDescription : description;
  }

  private int partNumberByMultiIterationForFirstStory() {

    int newPartNumber = 0;

    if(!isMultiIteration && partNumber == 0) {
      newPartNumber = partNumber + 1;
    } else if(isMultiIteration && partNumber > 0) {
      newPartNumber = partNumber;
    }

    return newPartNumber;
  }

  private String storyDescriptionWithPartsInformationForFirstStory() {
    return isMultiIteration ? originalDescription + " Part " + partNumberByMultiIterationForFirstStory()
        : description + " Part " + partNumberByMultiIterationForFirstStory();
  }

  private int partNumberByMultiIterationForSecondStory() {

    int newPartNumber = 0;

    if(!isMultiIteration && partNumber == 0) {
      newPartNumber = partNumber + 2;
    } else if(isMultiIteration && partNumber > 0) {
      newPartNumber = partNumber + 1;
    }

    return newPartNumber;
  }

  private String storyDescriptionWithPartsInformationForSecondStory() {
    return isMultiIteration ? originalDescription + " Part " + partNumberByMultiIterationForSecondStory()
        : description + " Part " + partNumberByMultiIterationForSecondStory();
  }

  @Override
  public String toString() {
    return "STORY " + id + " - " + description + " " + estimate + " Rank: " + priority  + " Parent Feature: " + parent;
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
