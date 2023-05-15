package com.demo.piplanner.domain.valueobject;

import static org.junit.Assert.assertEquals;

import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StoryTest {

  private Story originalStory_10_7_8;
  private Estimate availableCapacity_11_18_19;
  private Estimate availableCapacity_10_17_18;
  private Estimate availableCapacity_4_7_8;
  private Estimate availableCapacity_3_0_0;
  private Estimate availableCapacity_7_7_8;
  private Estimate availableCapacity_3_2_2;
  private Estimate availableCapacity_3_8_9;
  private Estimate availableCapacity_12_8_10;
  private Estimate availableCapacity_12_13_11;

  @BeforeEach
  void setUp() {

    originalStory_10_7_8 = Story.builder().withId("1").withDescription("Business Story").withPriority(1)
        .withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(7).withFt(8).build())
        .build();

    availableCapacity_11_18_19 = Estimate.Builder.newBuilder().withDev(11).withCt(18).withFt(19).build();
    availableCapacity_10_17_18 = Estimate.Builder.newBuilder().withDev(10).withCt(17).withFt(18).build();
    availableCapacity_4_7_8 = Estimate.Builder.newBuilder().withDev(10).withCt(7).withFt(8).build();
    availableCapacity_3_2_2 = Estimate.Builder.newBuilder().withDev(3).withCt(2).withFt(2).build();
    availableCapacity_3_0_0 = Estimate.Builder.newBuilder().withDev(3).withCt(0).withFt(0).build();
    availableCapacity_7_7_8 = Estimate.Builder.newBuilder().withDev(7).withCt(7).withFt(8).build();
    availableCapacity_3_8_9 = Estimate.Builder.newBuilder().withDev(3).withCt(8).withFt(9).build();
    availableCapacity_12_8_10 = Estimate.Builder.newBuilder().withDev(12).withCt(8).withFt(10).build();
    availableCapacity_12_13_11 = Estimate.Builder.newBuilder().withDev(12).withCt(13).withFt(11).build();
    availableCapacity_4_7_8 = Estimate.Builder.newBuilder().withDev(4).withCt(7).withFt(8).build();

  }

  @Test
  void split_whenRemainingCapacityIsGreaterThenTheStory_thenTheStoryDoesNotSplit() {

    final Pair<? extends Story, ? extends Story> expectedStorySplit = Pair.with(
        originalStory_10_7_8, null);
    final Pair<? extends Story, ? extends Story> storySplit = originalStory_10_7_8.split(
        availableCapacity_11_18_19);

    assertEquals("Story split is incorrect", expectedStorySplit, storySplit);
  }

  @Test
  void split_whenRemainingCapacityIsEqualToTheStory_thenTheStoryDoesNotSplit() {

    final Pair<? extends Story, ? extends Story> expectedStorySplit = Pair.with(
        originalStory_10_7_8, null);
    final Pair<? extends Story, ? extends Story> storySplit = originalStory_10_7_8.split(
        availableCapacity_10_17_18);

    assertEquals("Story split is incorrect", expectedStorySplit, storySplit);
  }

  @Test
  void split_whenRemainingDevAndTestCapacityIsLessThanTheStory_thenTheStorySplitsHavingDevInTheFirstHalfAndDevAndTestInTheSecondHalf() {

    final Story story_3_0_0 = Story.builder().withId("1").withDescription("Business Story Part 1")
        .withPriority(1).withPartNumber(1).withIsMultiIteration(true)
        .withEstimate(Estimate.builder().withDev(3).withCt(0).withFt(0).build())
        .withOriginalDescription("Business Story").build();

    final Story story_7_7_8 = Story.builder().withId("1").withDescription("Business Story Part 2")
        .withPriority(1).withPartNumber(2).withIsMultiIteration(true)
        .withEstimate(Estimate.Builder.newBuilder().withDev(7).withCt(7).withFt(8).build())
        .withOriginalDescription("Business Story").build();


    final Pair<? extends Story, ? extends Story> expectedStorySplit = Pair.with(story_3_0_0, story_7_7_8);
    final Pair<? extends Story, ? extends Story> storySplit = originalStory_10_7_8.split(
        availableCapacity_3_2_2);

    assertEquals("Story split is incorrect", expectedStorySplit, storySplit);
  }

  @Test
  void split_whenRemainingDevCapacityIsLessThanTheStoryAndTestCapacityIsMoreThanTheStory_thenTheStorySplitsHavingDevInTheFirstHalfAndDevAndTestInTheSecondHalf() {

    final Story story_3_0_0 = Story.builder().withId("1").withDescription("Business Story Part 1")
        .withPriority(1).withPartNumber(1).withIsMultiIteration(true)
        .withEstimate(Estimate.builder().withDev(3).withCt(0).withFt(0).build())
        .withOriginalDescription("Business Story").build();

    final Story story_7_7_8 = Story.builder().withId("1").withDescription("Business Story Part 2")
        .withPriority(1).withPartNumber(2).withIsMultiIteration(true)
        .withEstimate(Estimate.Builder.newBuilder().withDev(7).withCt(7).withFt(8).build())
        .withOriginalDescription("Business Story").build();


    final Pair<? extends Story, ? extends Story> expectedStorySplit = Pair.with(story_3_0_0, story_7_7_8);

      final Pair<? extends Story, ? extends Story> storySplit = originalStory_10_7_8.split(
          availableCapacity_3_8_9);

      assertEquals("Story split is incorrect", expectedStorySplit, storySplit);
  }

  @Test
  void split_whenOriginalStory_10_7_8_IsSplitByAvailableCapacity_12_8_10_thenTheSplitIs_10_0_0_and_0_7_8() {

    final Story story_10_0_0 = Story.builder().withId("1").withDescription("Business Story Part 1")
        .withPriority(1).withPartNumber(1).withIsMultiIteration(true)
        .withEstimate(Estimate.builder().withDev(10).withCt(0).withFt(0).build())
        .withOriginalDescription("Business Story").build();

    final Story story_0_7_8 = Story.builder().withId("1").withDescription("Business Story Part 2")
        .withPriority(1).withPartNumber(2).withIsMultiIteration(true)
        .withEstimate(Estimate.Builder.newBuilder().withDev(0).withCt(7).withFt(8).build())
        .withOriginalDescription("Business Story").build();

      final Pair<? extends Story, ? extends Story> expectedStorySplit = Pair.with(story_10_0_0, story_0_7_8);

      final Pair<? extends Story, ? extends Story> storySplit = originalStory_10_7_8.split(
          availableCapacity_12_8_10);

      assertEquals("Story split is incorrect", expectedStorySplit, storySplit);

  }

  @Test
  void split_whenOriginalStory_10_7_8_IsSplitByAvailableCapacity_12_13_11_thenTheSplitIs_10_3_1_and_0_4_7() {

    final Story story_10_3_1 = Story.builder().withId("1").withDescription("Business Story Part 1")
        .withPriority(1).withPartNumber(1).withIsMultiIteration(true)
        .withEstimate(Estimate.builder().withDev(10).withCt(3).withFt(1).build())
        .withOriginalDescription("Business Story").build();

    final Story story_0_4_7 = Story.builder().withId("1").withDescription("Business Story Part 2")
        .withPriority(1).withPartNumber(2).withIsMultiIteration(true)
        .withEstimate(Estimate.Builder.newBuilder().withDev(0).withCt(4).withFt(7).build())
        .withOriginalDescription("Business Story").build();

    final Pair<? extends Story, ? extends Story> expectedStorySplit = Pair.with(story_10_3_1, story_0_4_7);

    final Pair<? extends Story, ? extends Story> storySplit = originalStory_10_7_8.split(
        availableCapacity_12_13_11);

    assertEquals("Story split is incorrect", expectedStorySplit, storySplit);

  }

  @Test
  void split_whenRemainingCapacityIsLessThanTheStoryThatWasSplitOnceAlready_thenTheStorySplits() {

    final Story story_3_0_0 = Story.builder().withId("1").withDescription("Business Story Part 1")
        .withPriority(1).withEstimate(availableCapacity_3_0_0).withPartNumber(1).withIsMultiIteration(true)
        .withOriginalDescription("Business Story").build();

    final Story story_7_7_8 = Story.builder().withId("1").withDescription("Business Story Part 2")
        .withPriority(1).withEstimate(availableCapacity_7_7_8)
        .withPartNumber(2).withIsMultiIteration(true).withOriginalDescription("Business Story").build();

    final Pair<? extends Story, ? extends Story> expectedFirstStorySplit = Pair.with(story_3_0_0, story_7_7_8);
    final Pair<? extends Story, ? extends Story> storySplit = originalStory_10_7_8.split(
        availableCapacity_3_2_2);
    assertEquals("First story split is incorrect", expectedFirstStorySplit, storySplit);

    final Story story_3_0_0_v2 = Story.builder().withId("1").withDescription("Business Story Part 2")
        .withPriority(1).withEstimate(availableCapacity_3_0_0).withPartNumber(2).withIsMultiIteration(true)
        .withOriginalDescription("Business Story").build();

    final Story story_4_7_8 = Story.builder().withId("1").withDescription("Business Story Part 3")
        .withPriority(1).withEstimate(availableCapacity_4_7_8)
        .withPartNumber(3).withIsMultiIteration(true).withOriginalDescription("Business Story").build();

    final Pair<? extends Story, ? extends Story> expectedSecondsStorySplit = Pair.with(story_3_0_0_v2, story_4_7_8);
    final Pair<? extends Story, ? extends Story> secondStorySplit = storySplit.getValue1().split(
        availableCapacity_3_2_2);
    assertEquals("Second story split is incorrect", expectedSecondsStorySplit, secondStorySplit);
  }
}