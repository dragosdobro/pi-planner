# pi-planner

# Automatic PI Planner of Features and Stories into Iterations for Safe Agile Projects

## Weekend hobby project

#### The application is not fancy

* it just takes all Features and all Stories in order of priority and plans them in the PI
  Iterations
* if a story does not fit, it gets split across Iterations
* it tries to fill each iteration to its Recommended Load (not the Total Capacity)
* also added a PI Planning correctness check, where we tally all the stories from the PI Planning +
  the Leftover Backlog (where we have a story with multiple parts we sum them up) against the
  stories from the Original Backlog.
* if the total estimate of the stories from the PI Planning + Unplanned Backlog DOES NOT EQUAL the
  total estimate of the Stories from the Original Backlog then we have a problem
* this way you have mathematical proof that the PI Planning is correct.
* any other bugs should surface in case of other weird stories
* all the data is inputted manually in
  the ```PiPlannerApplicationTests.java``` / ```PI32023PlanningTest.java``` / ```PI32023PlanningTest.java```
  unit tests
* to help you with inputting the data, I created a sample ```PI_Scope_Helper_Template.xlsx``` (you
  can export the stories from Jira/Rally with their description, priority and DEV/CT/FT estimations
  and the template helps you write the input data Java code, yes I am aware of the
  irony in using Excel to write Java :)) , if you like Excel so much, I challenge to translate
  this code into VBA and just use Excel :)) )
* on a serious note, I am considering porting this codebase to VBA, now that it is stable, as it will
  help business users that use Excel a lot -> might do just that in the future at some point (•\_•) ( •\_•)>
  ⌐■-■ (⌐■_■)
* to run the PI Planning just run
  the ```PiPlannerApplicationTests.java``` / ```PI32023PlanningTest.java``` / ```PI32023PlanningTest.java```
  class as a JUnit test or just build the project with Maven ```mvn clean install```
