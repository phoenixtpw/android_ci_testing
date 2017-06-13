# Android CI Testing

# Introduction

This project is to test continuous integration (CI) in Android based on several service providers. This project has following purposes to achieve:

 - Self testing (unit test, functional test via simulator)
 - Notification (Slack notification for the test result in particular channel)
 - Deploy base on branches (development branch to Fabric, master branch to Google Play)
 
Currently we focus on cloud solution providers, and following are the achievable services:

 - Travis CI (with supported slack plugin and use Faselane to deploy)
 - CircleCI (with supported slack plugin and use Faselane to deploy)
 
 
# Service Flow

Generally speaking, these services are all followed the procedures below:

 - Set up virtual machine base environment (Ruby with Android studio installed)
 - Download/update the required SDK platforms, SDK tools, etc.
 - Set up the simulator and wait until its running
 - Running tests based on our code
 - Generate the signed build and deploy to the designated service (development branch to Fabric, master branch to Google Play)
 - Notify users based on the setting (email, slack, etc..)
 
User needs to create/ modify the .yml file and put it into the repository's root folder. As soon as the service detects the corresponding .yml file everytime when committed, it automatically activates the CI service to deal with it. Detailed settings in specific service as below.


# Travis CI Setting

Travis CI detects ".travis.yml" file to activate the service. Following is a successful setting example.

![Travis CI yml setting example](/readme_img/travis_ci_yml_setting.png)
 
 Setting points:
 
 - Although Travis CI automatically accepts the license for user, it's still needed to have the corresponding build tool mentioned/ pre-installed (Eg. build-tools-25.0.3) to follow your app's gradle setting. Otherwise, it will fail with the following error: License for package Android SDK Build-Tools xxxxx not accepted.
 - As we only requires fastlane for Google Play deployment on master branch, we can have a if-else condition in before_install tag. However, since fastlane requires ruby 2.0.0 and above to operate the service, and Travis CI's pre-installed ruby version for Android runner is 1.9.3, it's required to have at least ruby 2.0.0 pre-installed here as well. Otherwise, it will fail when trying to deploy the build at the end of the running. 
 - Travis CI has slack plug-in for notification service. Please follow this link for further operation and put the corresponding channel and token in the yml file: https://my.slack.com/services/new/travis
 - For fastlane deployment setting, please follow this to setup: https://docs.fastlane.tools/getting-started/android/setup/#collect-your-google-credentials .Please check [Google Play Deployment] section below when the 1st time you're trying to deploy the APK to Google Play.
 
 For more details regarding Travis CI setting, please check the official documentation for further info: https://docs.travis-ci.com/user/languages/android/


# CircleCI Setting

CircleCI detects "circle.yml" file to activate the service. Following is a successful setting example.

![CircleCI yml setting example](/readme_img/circleci_yml_setting.png)
 
 Setting points:
 
 - Since fastlane requires ruby 2.0.0 and above to operate the service, we need to mention it in the ruby version tag.
 - Circle has slack plug-in for notification service. Please follow this link for further operation: https://circleci.com/blog/slack-integration/
 - For fastlane deployment setting, please follow this to setup: https://docs.fastlane.tools/getting-started/android/setup/#collect-your-google-credentials .Please check [Google Play Deployment] section below when the 1st time you're trying to deploy the APK to Google Play.
 - Since CircleCI doesn't automatically generate a production build for you which Travis CI does, the ./gradlew assembleRelease is needed before using Faselane service for deployment.
 
 For more details regarding CircleCI setting, please check the official documentation for further info: https://circleci.com/docs/1.0/android/
 
 #  Google Play Deployment
 
 Since Google Play service updated the work flow, it's required to have the 1st time manually APK deployment now. Otherwise, there will be no package name generated in Google Play and Fastlane's supply service will get failed due to unable to find the matched package name in Google Play.
