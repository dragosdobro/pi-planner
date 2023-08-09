package com.demo.piplanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.demo.piplanner.domain.valueobject.Backlog;
import com.demo.piplanner.domain.valueobject.Estimate;
import com.demo.piplanner.domain.valueobject.Feature;
import com.demo.piplanner.domain.valueobject.Iteration;
import com.demo.piplanner.domain.valueobject.ProductIncrement;
import com.demo.piplanner.domain.valueobject.Story;
import com.google.common.collect.Lists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PiPlannerApplicationTests {

  private Backlog backlog;

  private ProductIncrement productIncrement;

  @BeforeEach
  void setUp() {
    backlog = Backlog.builder()
        .withFeatures(Lists.newArrayList(
            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("1").withDescription("1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withParent("1").build() ,
                        Story.builder().withId("2").withDescription("2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withParent("1").build()))
                .withId("1").withDescription("1").withPriority(1).build() ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("3").withDescription("3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withParent("2").build() ,
                        Story.builder().withId("4").withDescription("4").withPriority(4).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withParent("2").build()))
                .withId("2").withDescription("2").withPriority(2).build() ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("5").withDescription("5").withPriority(5).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withParent("3").build() ,
                        Story.builder().withId("6").withDescription("6").withPriority(6).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withParent("3").build()))
                .withId("3").withDescription("3").withPriority(3).build()
        ))
        .withName("Macallan Team Backlog").build();

    productIncrement = ProductIncrement.builder().withName("Macallan Team PI 3").withIterations(
        Lists.newArrayList(
            Iteration.builder().withNumber(1).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withCapacity(Estimate.builder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).build(),
            Iteration.builder().withNumber(2).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withCapacity(Estimate.builder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).build(),
            Iteration.builder().withNumber(3).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).withCapacity(Estimate.builder().withDev(new BigDecimal("10")).withCt(new BigDecimal("10")).withFt(new BigDecimal("10")).build()).build()
        )
    ).build();
  }

  @Test
  void doPIPlanning() {

    productIncrement.planBacklogInIterations(backlog);

    System.out.println(productIncrement);
    System.out.println(backlog);

    assertTrue(productIncrement.planningCorrectnessCheck(), "Backlog versus PI Planning estimates DO NOT MATCH");
  }

}
