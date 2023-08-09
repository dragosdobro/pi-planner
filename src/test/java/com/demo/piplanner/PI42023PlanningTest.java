package com.demo.piplanner;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.demo.piplanner.domain.valueobject.Backlog;
import com.demo.piplanner.domain.valueobject.Estimate;
import com.demo.piplanner.domain.valueobject.Feature;
import com.demo.piplanner.domain.valueobject.Iteration;
import com.demo.piplanner.domain.valueobject.ProductIncrement;
import com.demo.piplanner.domain.valueobject.Story;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PI42023PlanningTest {

  private Backlog backlog;

  private ProductIncrement productIncrementWithoutParkedCapacity;

  private ProductIncrement productIncrementWithKTCapacity;

  @BeforeEach
  void setUp() {
    backlog = Backlog.builder()
        .withFeatures(newArrayList(
            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1138796").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F207076").build() ,
                        Story.builder().withId("US1544120").withDescription("Super relevant description 3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("6")).withCt(new BigDecimal("3")).withFt(new BigDecimal("1")).build()).withParent("F207076").build() ,
                        Story.builder().withId("DE506581").withDescription("Super relevant description 4").withPriority(4).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0")).withFt(new BigDecimal("1")).build()).withParent("F207076").build() ,
                        Story.builder().withId("US1640122").withDescription("Super relevant description 5").withPriority(5).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("3")).withCt(new BigDecimal("2")).withFt(new BigDecimal("0")).build()).withParent("F207076").build() ,
                        Story.builder().withId("US1645384").withDescription("Super relevant description 6").withPriority(6).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F207076").build() ,
                        Story.builder().withId("DE507451").withDescription("Super relevant description 7").withPriority(7).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("0")).withFt(new BigDecimal("2")).build()).withParent("F207076").build() ,
                        Story.builder().withId("DE541813").withDescription("Super relevant description 8").withPriority(8).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("4")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F207076").build() ,
                        Story.builder().withId("DE512928_SPIKE").withDescription("Super relevant description 11").withPriority(11).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0.5")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F207076").build()

                    ))
                .withId("F207076").withDescription("Super relevant description 5").withPriority(5).build() ,

            Feature.builder().withChildren(
                    newArrayList(
                        Story.builder().withId("US000000").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2.5")).withCt(new BigDecimal("0")).withFt(new BigDecimal("2.5")).build()).withParent("F240944").build()
                    ))
                .withId("F240944").withDescription("Super relevant description 11").withPriority(11).build() ,

            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1629709").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0.5")).withCt(new BigDecimal("0.5")).withFt(new BigDecimal("0")).build()).withParent("F234455").build()


                    ))
                .withId("F234455").withDescription("Super relevant description 17").withPriority(17).build() ,

            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1650097").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0.5")).withCt(new BigDecimal("3")).withFt(new BigDecimal("0")).build()).withParent("F239317").build()

                    ))
                .withId("F239317").withDescription("Super relevant description 19").withPriority(19).build() ,

            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1612710").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1.5")).withCt(new BigDecimal("1")).withFt(new BigDecimal("0")).build()).withParent("F235629").build()

                    ))
                .withId("F235629").withDescription("Super relevant description 41").withPriority(41).build(),

            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1648224").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("4")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F242122").build()

                    ))
                .withId("F242122").withDescription("Super relevant description 49").withPriority(49).build() ,


            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1648232").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F242125").build()

                    ))
                .withId("F242125").withDescription("Super relevant description 50").withPriority(50).build() ,


            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1660606").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F242126").build()

                    ))
                .withId("F242126").withDescription("Super relevant description 51").withPriority(51).build() ,


            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1555130").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("3")).withCt(new BigDecimal("2")).withFt(new BigDecimal("0")).build()).withParent("F237552").build() ,
                        Story.builder().withId("US1638530").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("7.5")).withFt(new BigDecimal("0")).build()).withParent("F237552").build()

                    ))
                .withId("F237552").withDescription("Super relevant description 52").withPriority(52).build() ,


            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1601884").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0.5")).withCt(new BigDecimal("0.5")).withFt(new BigDecimal("0")).build()).withParent("F232628").build() ,
                        Story.builder().withId("US1618334").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0.5")).withCt(new BigDecimal("0.5")).withFt(new BigDecimal("0")).build()).withParent("F232628").build() ,
                        Story.builder().withId("US1654052").withDescription("Super relevant description 3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("0")).withFt(new BigDecimal("3")).build()).withParent("F232628").build() ,
                        Story.builder().withId("US1660660").withDescription("Super relevant description 4").withPriority(4).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("0")).withCt(new BigDecimal("0")).withFt(new BigDecimal("2")).build()).withParent("F232628").build()

                    ))
                .withId("F232628").withDescription("Super relevant description 53").withPriority(53).build() ,


            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1645694").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0")).withFt(new BigDecimal("2")).build()).withParent("F185295").build() ,
                        Story.builder().withId("US1655181").withDescription("Super relevant description 3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2.5")).withCt(new BigDecimal("0")).withFt(new BigDecimal("1")).build()).withParent("F185295").build()

                    ))
                .withId("F185295").withDescription("Super relevant description 58").withPriority(58).build() ,

            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1618218_SPIKE").withDescription("Super relevant description 3").withPriority(3).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F207570").build()

                    ))
                .withId("F207570").withDescription("Super relevant description 67").withPriority(67).build() ,


            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1662872").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F236269").build()

                    ))
                .withId("F236269").withDescription("Super relevant description 81").withPriority(81).build(),


            Feature.builder().withChildren(
                    newArrayList(

                        Story.builder().withId("US1657948").withDescription("Super relevant description 1").withPriority(1).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("2")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F243644").build() ,
                        Story.builder().withId("US1657998").withDescription("Super relevant description 2").withPriority(2).withEstimate(Estimate.Builder.newBuilder().withDev(new BigDecimal("1")).withCt(new BigDecimal("0")).withFt(new BigDecimal("0")).build()).withParent("F243644").build()

                    ))
                .withId("F243644").withDescription("Super relevant description 104").withPriority(104).build()

        ))
        .withName("Macallan Team Backlog").build();

    productIncrementWithoutParkedCapacity = ProductIncrement.builder().withName("Macallan Team PI 4").withIterations(
        newArrayList(
            Iteration.builder().withNumber(1).withCapacity(Estimate.builder().withDev(new BigDecimal("17.8")).withCt(new BigDecimal("9.4")).withFt(new BigDecimal("0.8")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("14.2")).withCt(new BigDecimal("7.5")).withFt(new BigDecimal("0.6")).build()).build(),
            Iteration.builder().withNumber(2).withCapacity(Estimate.builder().withDev(new BigDecimal("21.4")).withCt(new BigDecimal("11.5")).withFt(new BigDecimal("2.4")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("17.1")).withCt(new BigDecimal("9.2")).withFt(new BigDecimal("1.9")).build()).build(),
            Iteration.builder().withNumber(3).withCapacity(Estimate.builder().withDev(new BigDecimal("20.2")).withCt(new BigDecimal("5.1")).withFt(new BigDecimal("6.4")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("16.1")).withCt(new BigDecimal("4")).withFt(new BigDecimal("5.1")).build()).build(),
            Iteration.builder().withNumber(4).withCapacity(Estimate.builder().withDev(new BigDecimal("22.1")).withCt(new BigDecimal("1.9")).withFt(new BigDecimal("6.4")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("17.6")).withCt(new BigDecimal("1.5")).withFt(new BigDecimal("5.1")).build()).build(),
            Iteration.builder().withNumber(5).withCapacity(Estimate.builder().withDev(new BigDecimal("18.9")).withCt(new BigDecimal("5.1")).withFt(new BigDecimal("6.4")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("15.2")).withCt(new BigDecimal("4")).withFt(new BigDecimal("5.1")).build()).build()
        )
    ).build();


    productIncrementWithKTCapacity = ProductIncrement.builder().withName("Macallan Team PI 4").withIterations(
        newArrayList(
            Iteration.builder().withNumber(1).withCapacity(Estimate.builder().withDev(new BigDecimal("17.8")).withCt(new BigDecimal("9.4")).withFt(new BigDecimal("0.8")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("10.2")).withCt(new BigDecimal("7.5")).withFt(new BigDecimal("0.6")).build()).build(),
            Iteration.builder().withNumber(2).withCapacity(Estimate.builder().withDev(new BigDecimal("21.4")).withCt(new BigDecimal("11.5")).withFt(new BigDecimal("2.4")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("13.1")).withCt(new BigDecimal("9.2")).withFt(new BigDecimal("1.9")).build()).build(),
            Iteration.builder().withNumber(3).withCapacity(Estimate.builder().withDev(new BigDecimal("20.2")).withCt(new BigDecimal("5.1")).withFt(new BigDecimal("6.4")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("12.1")).withCt(new BigDecimal("4")).withFt(new BigDecimal("5.1")).build()).build(),
            Iteration.builder().withNumber(4).withCapacity(Estimate.builder().withDev(new BigDecimal("22.1")).withCt(new BigDecimal("1.9")).withFt(new BigDecimal("6.4")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("13.6")).withCt(new BigDecimal("1.5")).withFt(new BigDecimal("5.1")).build()).build(),
            Iteration.builder().withNumber(5).withCapacity(Estimate.builder().withDev(new BigDecimal("18.9")).withCt(new BigDecimal("5.1")).withFt(new BigDecimal("6.4")).build()).withRecommendedLoad(Estimate.builder().withDev(new BigDecimal("12.2")).withCt(new BigDecimal("4")).withFt(new BigDecimal("5.1")).build()).build()
        )
    ).build();
  }


  @Test
  void productIncrementWithoutParkedCapacity() {
    productIncrementWithoutParkedCapacity.planBacklogInIterations(backlog);

    System.out.println(productIncrementWithoutParkedCapacity);
    System.out.println(backlog);

    assertTrue(productIncrementWithoutParkedCapacity.planningCorrectnessCheck(), "Backlog versus PI Planning estimates DO NOT MATCH");
  }

  @Test
  void productIncrementWithKTParkedCapacity() {
    productIncrementWithKTCapacity.planBacklogInIterations(backlog);

    System.out.println(productIncrementWithKTCapacity);
    System.out.println(backlog);

    assertTrue(productIncrementWithKTCapacity.planningCorrectnessCheck(), "Backlog versus PI Planning estimates DO NOT MATCH");
  }

}
