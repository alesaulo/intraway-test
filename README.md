# intraway-test

Hi!

Welcome to the Intraway exercise. 

This exercise has the feature of letting the user specify which numbers convert to words. 
application.properties allows to accomplish the above:

com.intraway.exercise.multiples=3-Fizz,5-Buzz
.
.
.

By default comes Fizz for number 3 and Buzz for number 5, but this can be replaced by other number-word pairs. 

Modularization has been maximized as much as possible, yielding the following flow of execution:

1) Request comes
2) "Controller" class takes charge
3) It invokes
   a) Calculator service (which does the calculation)
   b) Then a formatrer module is invoked in order to format the results given by Calculator service
   c) Finally persists the formatted results and returns them

Notes:

1) Calculator service reads application.properties file in order to know which numbers convert to words
2) Controller checks that max number is greater than min, otherwise it throws BadRequest exception.

Package comes with unit and integration tests. 

Thanks and enjoy! :D 


