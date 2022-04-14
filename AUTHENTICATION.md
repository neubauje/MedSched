# Overview

This project is a basic template that provides the following capabilities out of the box:

* authentication

## Authentication

The authentication provider provided allows you to develop code in Spring MVC without having to develop your own authentication/authorization framework. The `AuthProvider` provided defines several methods that can be used from various parts of your application. This includes but is not limited to:

* Get current logged in user
* Create new user
* Log in as user

See `AuthProvider.java` for a full description of how the methods can be used in your application.

### Set Up

A `SessionAuthProvider` class is included with this project to implement the `AuthProvider` interface. As such, the following items need to be configured.

#### Database

A sql script is provided to create the users table at `schema.sql`. If you need to modify the table
structure, make sure to update the `User` model and the associated data access classes.

### Usage

There is an HttpSession variable named `user` that you can use to determine login status or get access to any of the properites on the User model.

#### Direct access to the authentication system

You can access the `AuthProvider` by allowing it to be injected into your controllers:

```java
@Autowired
private AuthProvider auth;
```

Once you have an instance of the `AuthProvider`, you can invoke these methods on it:

* `getCurrentUser()` - returns the current logged in user (null if they are not)
* `signIn(String username, String password)` - validates the credentials and authenticates the user
* `logOff()` - logs the user out of the system
* `changePassword(String existingPassword, String newPassword)` - validates the user's existing credentials and changes their password
* `register(String username, String password)` - creates a new user with the provided credentials and role

If you want to restrict access to a specific controller or controller action, you can call the method `userHasRole(String[] roles)` to see if the currently logged in user has any of the roles defined. If not, it returns a false, and you can define what to do at that point.

```java
if( ! auth.userHasRole(["admin", "editor"]) ) { // If user doesn't have the admin or editor role
    throw new UnauthorizedException();
}
```
