# GitHubCommitSampleApp

This app shows a list of recent commits in a Github repository.
Usage:
1. enter a Github username and repository on the input screen
2. Click Get Commits button to see list of recent commits from the master branch
3. The commit list fetches commits in batches of 30, and automatically fetches a new set of commits as you scroll to the bottom of the list.

TODO Improvements:

Click on an individual commit to see more commit info such as diff, entire commit message, etc

This Application is in MVVM Architecture - has one Main Activity which has two Fragments
1. RepoInputFragment
  Take GitHub username and Repository name and clicking on getCommits button validates the input and fetches the api response from the REST API using Retrofit API service.
  Validates the API response and onSuccess - goes to the second Fragment (CommitsListFragment), otherwise shows a toast with the error message
2. CommitListFragment
  Displays the Author, Message and Hash of API response from REST API in a Recycler View. When the user reaches the end of the screen, if there are more items in the list, it makes
  network call to fetch data from next page. 
  
  This Application uses Dagger2 for dependency Injection to build dependencies view Modules and Components.
  REST API used : https://api.github.com/repos/{user}/{repo}/commits?page=
  
  Primary Language : Java
  Technologies/ Libraries Used :
  MVVM Architectural pattern
  Dagger - for Dependency Injection
  Retrofit - for making Network calls to REST API
  RecyclerView
  Activities, Fragments
  ConstraintLayout, FrameLayout
