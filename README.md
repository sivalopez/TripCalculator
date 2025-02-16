# Trip Calculator
## Problem
Given an input file in CSV format containing list of taps (ON and OFF) in separate lines, this project creates an output file containing trips made by customers.
### Assumptions
- Input data is complete and valid.
- ON tap may or may not have a matching OFF tap.
- An OFF tap should always have a matching ON tap. This is ignored.
- Cancelled trips are when customer taps OFF at the same stop.
- INCOMPLETE trips get written to CSV with blank Finished, DurationSecs, ToStopId.
### Input
Sample input file: ``TripCalculator/taps.csv``
### Output
Output file for the example input file: ``TripCalculator/trips.csv``
### Quickstart
``mvn clean compile assembly:single``

``java -jar target/TripCalculator-1.0-SNAPSHOT-jar-with-dependencies.jar``
### Tests 
``mvn test``
