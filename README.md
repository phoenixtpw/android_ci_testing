Android CI Testing

Introduction

This project is to test continuous integration (CI) in Android based on several service providers. This project has following purposes to achieve:

 - Self testing (unit test, functional test via simulator)
 - Notification (Slack notification for the test result in particular channel)
 - Deploy base on branches (development branch to Fabric, master branch to Google Play)
 
Currently we focus on cloud solution providers, and following are the achievable services:

 - Travis CI (with supported slack plugin and use Faselane to deploy)
 - CircleCI (with supported slack plugin and use Faselane to deploy)
 
 
Service Setup

Generally speaking, these services are all followed the procedures below:

 - Set up virtual machine base environment (Ruby with Android studion installed)
 - Download/update the required SDK platforms, SDK tools, etc.
 - Set up the simulator and wait until its running
 - Running tests based on our code
 - Generate the signed build and deploy to the designated service (development branch to Fabric, master branch to Google Play)
 - Nodify users based on the setting (email, slack, etc..)
 
User needs to create/ modify the .yml file and put it into the repository's root folder. 
 
 
 
