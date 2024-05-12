# This is Android app that allows users to read all recipes. The app is built using Clean Architecture with an MVVM design pattern for architecture.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
- [Koin](https://insert-koin.io/docs/quickstart/android/) - is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Jetpack Navigation](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - To make the application startup faster, reduce network traffic or handle simple data completely offline. ex:- ID or Password of user
- [Voyager](https://voyager.adriel.cafe/) - To solve the problem of duplicate navigation code in our Android and iOS projects by providing a shared navigation module.
- [Firebase](https://firebase.google.com/) - To allow to storage of data.


## UI by using a XML
<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/6cf52b63-334a-4a37-97fe-0ad0ce4222d4" width = "200" height = "400">  <img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/526a7345-5f0f-4560-bdcf-71f5b3da6daf" width = "200" height = "400">

Here I mean a password, and email equals to null because the user does not add his own data.

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/d7baa21a-6ce4-408c-84bd-6624431046db" width = "200" height = "400">

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/72985c39-5553-437e-b76c-72fd204e25f5" width = "200" height = "400">

Here the user added his data, and password equal to value but did not understand because done saved by using encrypt.

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/760ba9e7-c330-43b1-b467-4e72ec01d478" width = "200" height = "400">

Here when the user clicks on the password show an original password, by using decrypt.

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/39851fdb-294d-4470-84a9-3192eb791f18" width = "200" height = "400">

Here when the user exits from the app and restarts again show the encrypted password.

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/35000a01-e67e-4786-9a5f-fbc0f0fb9c51" width = "200" height = "400"> 


## UI by using a Jetpack Compose
<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/d17770f1-9362-477f-b7e7-28ffe5ebe66a" width = "200" height = "400">  

Here I mean the original password used by using XML.

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/cd2d62f3-9565-4a5c-99fb-6446db51bf11" width = "200" height = "400">

Here the user added his data, and password equal to value but did not understand because done saved by using encrypt.

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/77a4a4f3-960f-4ae8-92f7-92a62b9aad5f" width = "200" height = "400">

Here when the user clicks on the password show an original password below in the red box, by using decrypt.

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/7b987ace-781a-46fb-9a85-ea3679b47b87" width = "200" height = "400">

Here when the user exits from the app and restarts again show the encrypted password.

<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/bcb1e465-b791-440a-ad57-fa3a69c27660" width = "200" height = "400">


## Dividing packages by using a Clean Architecture
<img src = "https://github.com/Mustafa-Muhamed-Mansour/Recipes/assets/53982895/c5ddc5fd-4b8c-46eb-a5f2-da811b1b40bd" width = "200" height = "400">
