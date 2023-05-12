package com.demo.piplanner.domain.valueobject;

import com.google.common.collect.Lists;

import org.junit.jupiter.api.Test;

import java.util.List;

public class PrintTest {

  @Test
  void printTestOfDomainValueObjects() {
    final List<Story> stories = Lists.newArrayList(
        Story.builder().withId("1").withDescription("1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("1").build() ,
        Story.builder().withId("2").withDescription("2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("1").build() ,
        Story.builder().withId("3").withDescription("3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("2").build() ,
        Story.builder().withId("4").withDescription("4").withPriority(4).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("2").build() ,
        Story.builder().withId("5").withDescription("5").withPriority(5).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("3").build() ,
        Story.builder().withId("6").withDescription("6").withPriority(6).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("3").build()
    );

    System.out.println(stories);

    final List<? extends Story> features = Lists.newArrayList(

        Feature.builder().withChildren(
                Lists.newArrayList(
                    Story.builder().withId("1").withDescription("1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("1").build() ,
                    Story.builder().withId("2").withDescription("2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("1").build()))
            .withId("1").withDescription("1").withPriority(1).build() ,

        Feature.builder().withChildren(
                Lists.newArrayList(
                    Story.builder().withId("3").withDescription("3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("2").build() ,
                    Story.builder().withId("4").withDescription("4").withPriority(4).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("2").build()))
            .withId("2").withDescription("2").withPriority(2).build() ,

        Feature.builder().withChildren(
                Lists.newArrayList(
                    Story.builder().withId("5").withDescription("5").withPriority(5).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("3").build() ,
                    Story.builder().withId("6").withDescription("6").withPriority(6).withEstimate(Estimate.Builder.newBuilder().withDev(10).withCt(10).withFt(10).build()).withParent("3").build()))
            .withId("3").withDescription("3").withPriority(3).build()
    );

    System.out.println(features);

    final Backlog backlog = Backlog.builder().withFeatures(features).withName("Macallan Team Backlog").build();

    System.out.println(backlog);

  }

}
