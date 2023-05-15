# pi-planner
Automatic PI Planner of Features and Stories into Iterations for Safe Agile Projects

Weekend hobby project:

The application is not fancy:
-	it just takes all Features and all Stories in order of priority and plans them in the PI Iterations
-	if a story does not fit, it gets split across Iterations
-	it tries to fill each iteration to its Recommended Load (not the Total Capacity)
-	all the data is inputted manually in the PiPlannerApplicationTests.java class
-	to help you with inputting the data, I created a sample PI_Scope_Helper_Template.xlsx
(you can export the stories from each iteration with their description and priority)
