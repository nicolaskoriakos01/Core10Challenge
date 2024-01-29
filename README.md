# Core 10 QA Engineer Challenge - Nicolas Koriakos

Hello! 👋 Welcome to my Core 10 QA Engineer Challenge repository. This space showcases my skills and approach to quality assurance as part of the assessment provided by Core 10.

## About Me:

I am Nicolas Koriakos, a passionate Quality Assurance Engineer excited about ensuring the highest standards of software quality. This repository serves as a portfolio of my work on the challenges presented by Core 10.


## Set up

Before we begin, there are a couple of steps we should take to ensure everything is ready for action:

1 - You should have docker installed (this is to run the API provided by Core 10).

2 - Now that we have docker we should get the repo from the API we are testing.<br><br>
here you go: https://github.com/alexmgriffiths/compose-weather  now that you have it you just need to do a git clone on it :D

3 - For our final step the only thing you need to do is go to the folder (using your CMD) where you have the compose-weather repo and there run this command: <b> docker-compose up -d <b>

4 - if everything went right we should be able to start testing. <br><br>
a safe bet in order to check if everything is okay is trying to access the following link: http://localhost:8080/swagger/index.html you should be able to access it. <br><br>
if you aren't you can contact me and I'll be happy to help :)

# TEST PLAN

This is a one endpoint test plan so I decided to keep it short and to the point, based on the fact that we only need to execute a GET API most of the test cases are going to be positive because we don’t really have to do any data input besides the url itself.

# Background before test: 
It is expected to get a 200 response everytime we run the WeatherForecast endpoint.

<br>
On the fisr tab you have the test cases and on the second one the bugs.

 1 - <a href="https://docs.google.com/spreadsheets/d/1NO5AxRx33iBmbPRH-ndFqp2hico77aRNwyJrw28p6tU/edit#gid=0" target="_blank">Positive Test cases and bugs.</a> <br>
 2 - For the negative test cases I decided to try only one, this would be running an invalid endpoint: <br>

Endpoint -> http://localhost:8080/InvalidEndpoint <br>
Expected result -> Error 404 not found <br>
Actual result -> Error 404 not found <br>
Test result -> Pass <br>

# Test Automation

PRE-REQUISITES: JAVA INSTALLED(With JAVA_HOME Set Up), MAVEN INSTALLED

For this part I decided to use Java + RestAssured with cucumber and TestNG.
I decided to automate a single testcase where it validates the data. in this test case the data validated is the date and the temperature conversion.<br><br>
The validation on date is to check if any of the dates showed are from before the current date.<br><br>
The validation for temperature is to check if the conversion from Farenheit to celcius work correctly.<br><br>
The main goal of the automation is to show how I set up a rest assured endpoint using different tools and different utils classes to make it as scalable as posible, I also added comments to explain what I did in my code.<br>
If you have any question please let me know!

# HOW TO RUN IT

1 - you should clone this repo to your local machine. <br>
2 - Open the project on the IDE of your choice. <br>
3 - Navigate to the GET_WeatherForecast.feature file. <br>
4 - hit the run button. <br>

If this does not work please contact me and I will help you in any way possible.


