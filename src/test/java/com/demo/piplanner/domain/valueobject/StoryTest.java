package com.demo.piplanner.domain.valueobject;

import static org.junit.Assert.assertEquals;

import org.javatuples.Pair;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoryTest {

  private Story originalStory;

  private Estimate moreThanEnoughEstimate;

  private Estimate enoughEstimate;

  private Estimate lessThanEnoughEstimate;

  @BeforeEach
  void setUp() {

    originalStory = Story.builder().withId("1").withDescription("Business Story").withPriority(1)
        .withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build())
        .build();

    moreThanEnoughEstimate = Estimate.Builder.newBuilder().withDev(11).withCt(11).withFt(11).build();
    enoughEstimate = Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build();
    lessThanEnoughEstimate = Estimate.Builder.newBuilder().withDev(3).withCt(3).withFt(3).build();
  }

  @Test
  void split_whenRemainingCapacityIsGreaterThenTheStory_thenTheStoryDoesNotSplit() {

    final Pair<? extends Story, ? extends Story> expectedStorySplit = Pair.with(originalStory, null);
    final Pair<? extends Story, ? extends Story> storySplit = originalStory.split(moreThanEnoughEstimate);

    assertEquals("Story split is incorrect", expectedStorySplit, storySplit);
  }

  @Test
  void split_whenRemainingCapacityIsEqualToTheStory_thenTheStoryDoesNotSplit() {

    final Pair<? extends Story, ? extends Story> expectedStorySplit = Pair.with(originalStory, null);
    final Pair<? extends Story, ? extends Story> storySplit = originalStory.split(enoughEstimate);

    assertEquals("Story split is incorrect", expectedStorySplit, storySplit);
  }

  @Test
  void split_whenRemainingCapacityIsLessThanTheStory_thenTheStorySplits() {

    final Pair<? extends Story, ? extends Story> expectedStorySplit = buildFirstExpectedStorySplitPair();
    final Pair<? extends Story, ? extends Story> storySplit = originalStory.split(lessThanEnoughEstimate);

    assertEquals("Story split is incorrect", expectedStorySplit, storySplit);
  }

  @Test
  void split_whenRemainingCapacityIsLessThanTheStoryThatWasSplitOnceAlready_thenTheStorySplits() {

    final Pair<? extends Story, ? extends Story> expectedFirstStorySplit = buildFirstExpectedStorySplitPair();
    final Pair<? extends Story, ? extends Story> storySplit = originalStory.split(lessThanEnoughEstimate);
    assertEquals("First story split is incorrect", expectedFirstStorySplit, storySplit);

    final Pair<? extends Story, ? extends Story> expectedSecondsStorySplit = buildSecondExpectedStorySplitPair();
    final Pair<? extends Story, ? extends Story> secondStorySplit = storySplit.getValue1().split(lessThanEnoughEstimate);
    assertEquals("Second story split is incorrect", expectedSecondsStorySplit, secondStorySplit);
  }

  @NotNull
  private Pair<? extends Story, ? extends Story> buildFirstExpectedStorySplitPair() {

    final Story expectedFirstStory = Story.builder().withId("1").withDescription("Business Story Part 1")
        .withPriority(1).withEstimate(lessThanEnoughEstimate).withPartNumber(1).withIsMultiIteration(true)
        .withOriginalDescription("Business Story").build();

    final Story expectedSecondStory = Story.builder().withId("1").withDescription("Business Story Part 2")
        .withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(7).withCt(7).withFt(7).build())
        .withPartNumber(2).withIsMultiIteration(true).withOriginalDescription("Business Story").build();

    return Pair.with(expectedFirstStory, expectedSecondStory);
  }

  @NotNull
  private static Pair<? extends Story, ? extends Story> buildSecondExpectedStorySplitPair() {

    final Story expectedThirdStory = Story.builder().withId("1").withDescription("Business Story Part 2")
        .withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(3).withCt(3).withFt(3).build())
        .withPartNumber(2).withIsMultiIteration(true).withOriginalDescription("Business Story").build();

    final Story expectedFourthStory = Story.builder().withId("1").withDescription("Business Story Part 3")
        .withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(4).withCt(4).withFt(4).build())
        .withPartNumber(3).withIsMultiIteration(true).withOriginalDescription("Business Story").build();

    return Pair.with(expectedThirdStory, expectedFourthStory);
  }
}