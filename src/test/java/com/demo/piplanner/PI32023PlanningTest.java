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

class PI32023PlanningTest {

  private Backlog backlog;

  private ProductIncrement productIncrementWithoutParkedCapacity;
  private ProductIncrement productIncrementWith12MdParkedCapacity;
  private ProductIncrement productIncrementWith5MdParkedCapacity;

  @BeforeEach
  void setUp() {
    backlog = Backlog.builder()
        .withFeatures(Lists.newArrayList(
            Feature.builder().withChildren(
                    Lists.newArrayList(

                        Story.builder().withId("US1501556").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2")).withCt(new BigDecimal("1.5")).withFt(new BigDecimal("2")).build()).withParent("F207075").build() ,
                        Story.builder().withId("DE501524").withDescription("Super relevant description 3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("5")).withCt(new BigDecimal("1")).withFt(new BigDecimal("0")).build()).withParent("F207075").build() ,
                        Story.builder().withId("DE222535").withDescription("Super relevant description 3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("3")).withCt(new BigDecimal("2")).withFt(new BigDecimal("2")).build()).withParent("F207075").build() ,
                        Story.builder().withId("DE511048").withDescription("Super relevant description 4").withPriority(4).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("3")).withCt(new BigDecimal("1")).withFt(new BigDecimal("0")).build()).withParent("F207075").build() ,
                        Story.builder().withId("DE512928").withDescription("Super relevant description 5").withPriority(5).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("3")).withCt(new BigDecimal("3")).withFt(new BigDecimal("0")).build()).withParent("F207075").build() ,
                        Story.builder().withId("DE511263").withDescription("Super relevant description 6").withPriority(6).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0.5")).withFt(new BigDecimal("1")).build()).withParent("F207075").build() ,
                        Story.builder().withId("DE90753").withDescription("Super relevant description 8").withPriority(8).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0.5")).withCt(new BigDecimal("1")).withFt(new BigDecimal("1")).build()).withParent("F207075").build() ,
                        Story.builder().withId("US1531981").withDescription("Super relevant description 9").withPriority(9).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("3")).withCt(new BigDecimal("1")).withFt(new BigDecimal("2")).build()).withParent("F207075").build() ,
                        Story.builder().withId("US1552135").withDescription("Super relevant description 13").withPriority(13).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F207075").build()

                    ))
                .withId("F207075").withDescription("Super relevant description 7").withPriority(7).build() ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1568267").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("0")).withFt(new BigDecimal("1")).build()).withParent("F223467").build()

                    ))
                .withId("F223467").withDescription("Super relevant description 7").withPriority(7).build() ,


            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1568267").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("0")).withFt(new BigDecimal("1")).build()).withParent("F223467").build()

                    ))
                .withId("F223467").withDescription("Super relevant description 12").withPriority(12).build()  ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1543255").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("5")).withCt(new BigDecimal("3")).withFt(new BigDecimal("0")).build()).withParent("F215879").build()

                    ))
                .withId("F215879").withDescription("Super relevant description 25").withPriority(25).build()  ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1566031").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2")).withCt(new BigDecimal("1")).withFt(new BigDecimal("3")).build()).withParent("F230034").build()

                    ))
                .withId("F230034").withDescription("Super relevant description 30").withPriority(30).build() ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1562092").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("4.5")).withCt(new BigDecimal("3")).withFt(new BigDecimal("2")).build()).withParent("F185294").build(),
                        Story.builder().withId("US1524445").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0")).withFt(new BigDecimal("1")).build()).withParent("F185294").build()

                    ))
                .withId("F185294").withDescription("Super relevant description 37").withPriority(37).build() ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US725554").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("6")).withCt(new BigDecimal("3")).withFt(new BigDecimal("3")).build()).withParent("F229870").build()

                    ))
                .withId("F229870").withDescription("Super relevant description 62").withPriority(62).build() ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1569532").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0")).withFt(new BigDecimal("2")).build()).withParent("F228522").build()

                    ))
                .withId("F228522").withDescription("Super relevant description 103").withPriority(103).build(),

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1506813").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("3")).withCt(new BigDecimal("0")).withFt(new BigDecimal("2")).build()).withParent("F228524").build()

                    ))
                .withId("F228524").withDescription("Super relevant description 104").withPriority(104).build(),

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1463780").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("0")).withFt(new BigDecimal("4")).build()).withParent("F228844").build() ,
                        Story.builder().withId("US1543247").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("8")).withCt(new BigDecimal("0")).withFt(new BigDecimal("4")).build()).withParent("F228844").build()

                    ))
                .withId("F228844").withDescription("Super relevant description 106").withPriority(106).build() ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1544158").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F228845").build() ,
                        Story.builder().withId("US1544172").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("4")).withCt(new BigDecimal("2")).withFt(new BigDecimal("2")).build()).withParent("F228845").build()

                    ))
                .withId("F228845").withDescription("Super relevant description 107").withPriority(107).build()  ,


            Feature.builder().withChildren(
                    Lists.newArrayList(

                        Story.builder().withId("US1452681").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("0")).withFt(new BigDecimal("3")).build()).withParent("F228843").build()

                    ))
                .withId("F228843").withDescription("Super relevant description 108").withPriority(108).build() ,

            Feature.builder().withChildren(
                    Lists.newArrayList(
                        Story.builder().withId("US1555089").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("20")).withFt(new BigDecimal("0")).build()).withParent("F232329").build() ,
                        Story.builder().withId("US1555130").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("5")).withFt(new BigDecimal("0")).build()).withParent("F232329").build()

                    ))
                .withId("F232329").withDescription("Super relevant description 117").withPriority(117).build()
        ))
        .withName("Macallan Team Backlog").build();

    productIncrementWithoutParkedCapacity = ProductIncrement.builder().withName("Macallan Team PI 3").withIterations(
        Lists.newArrayList(
            Iteration.builder().withNumber(1).withCapacity(Estimate.builder().withDev(new BigDecimal("22")).withCt(new BigDecimal("17")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("18")).withCt(new BigDecimal("11")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(2).withCapacity(Estimate.builder().withDev(new BigDecimal("16")).withCt(new BigDecimal("12")).withFt(new BigDecimal("6")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("13")).withCt(new BigDecimal("7")).withFt(new BigDecimal("5")).build()).build(),
            Iteration.builder().withNumber(3).withCapacity(Estimate.builder().withDev(new BigDecimal("19")).withCt(new BigDecimal("14")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("15")).withCt(new BigDecimal("9")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(4).withCapacity(Estimate.builder().withDev(new BigDecimal("21")).withCt(new BigDecimal("7")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("17")).withCt(new BigDecimal("5")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(5).withCapacity(Estimate.builder().withDev(new BigDecimal("24")).withCt(new BigDecimal("13")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("19")).withCt(new BigDecimal("11")).withFt(new BigDecimal("13")).build()).build()
        )
    ).build();

    productIncrementWith12MdParkedCapacity = ProductIncrement.builder().withName("Macallan Team PI 3").withIterations(
        Lists.newArrayList(
            Iteration.builder().withNumber(1).withCapacity(Estimate.builder().withDev(new BigDecimal("22")).withCt(new BigDecimal("17")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("6")).withCt(new BigDecimal("11")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(2).withCapacity(Estimate.builder().withDev(new BigDecimal("16")).withCt(new BigDecimal("12")).withFt(new BigDecimal("6")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("1")).withCt(new BigDecimal("7")).withFt(new BigDecimal("5")).build()).build(),
            Iteration.builder().withNumber(3).withCapacity(Estimate.builder().withDev(new BigDecimal("19")).withCt(new BigDecimal("14")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("3")).withCt(new BigDecimal("9")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(4).withCapacity(Estimate.builder().withDev(new BigDecimal("21")).withCt(new BigDecimal("7")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("5")).withCt(new BigDecimal("5")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(5).withCapacity(Estimate.builder().withDev(new BigDecimal("24")).withCt(new BigDecimal("13")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("19")).withCt(new BigDecimal("11")).withFt(new BigDecimal("13")).build()).build()
        )
    ).build();

    productIncrementWith5MdParkedCapacity = ProductIncrement.builder().withName("Macallan Team PI 3").withIterations(
        Lists.newArrayList(
            Iteration.builder().withNumber(1).withCapacity(Estimate.builder().withDev(new BigDecimal("22")).withCt(new BigDecimal("17")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("13")).withCt(new BigDecimal("11")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(2).withCapacity(Estimate.builder().withDev(new BigDecimal("16")).withCt(new BigDecimal("12")).withFt(new BigDecimal("6")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("8")).withCt(new BigDecimal("7")).withFt(new BigDecimal("5")).build()).build(),
            Iteration.builder().withNumber(3).withCapacity(Estimate.builder().withDev(new BigDecimal("19")).withCt(new BigDecimal("14")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("10")).withCt(new BigDecimal("9")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(4).withCapacity(Estimate.builder().withDev(new BigDecimal("21")).withCt(new BigDecimal("7")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("12")).withCt(new BigDecimal("5")).withFt(new BigDecimal("13")).build()).build(),
            Iteration.builder().withNumber(5).withCapacity(Estimate.builder().withDev(new BigDecimal("24")).withCt(new BigDecimal("13")).withFt(new BigDecimal("16")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("19")).withCt(new BigDecimal("11")).withFt(new BigDecimal("13")).build()).build()
        )
    ).build();
  }

  @Test
  void planWithoutParkedCapacity() {
    productIncrementWithoutParkedCapacity.planBacklogInIterations(backlog);

    System.out.println(productIncrementWithoutParkedCapacity);
    System.out.println(backlog);

    assertTrue(productIncrementWithoutParkedCapacity.planningCorrectnessCheck(), "Backlog versus PI Planning estimates DO NOT MATCH");
  }

  @Test
  void planWith12MdParkedCapacity() {
    productIncrementWith12MdParkedCapacity.planBacklogInIterations(backlog);

    System.out.println(productIncrementWith12MdParkedCapacity);
    System.out.println(backlog);

    assertTrue(productIncrementWith12MdParkedCapacity.planningCorrectnessCheck(), "Backlog versus PI Planning estimates DO NOT MATCH");
  }

  @Test
  void planWith5MdParkedCapacity() {
    productIncrementWith5MdParkedCapacity.planBacklogInIterations(backlog);

    System.out.println(productIncrementWith5MdParkedCapacity);
    System.out.println(backlog);

    assertTrue(productIncrementWith5MdParkedCapacity.planningCorrectnessCheck(), "Backlog versus PI Planning estimates DO NOT MATCH");
  }

}
