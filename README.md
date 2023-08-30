## SpringBoot Kotlin Modules
This is not the best structure. This is a good basic structure to use early in the project when productivity is important.

Remember, as your software grows, your structure must grow too.

## Environment
Spring Boot: `3.1.2`

Kotlin Version: `1.9.0`

Java version: `17`

## Run Example
**1. Build application**
```bash
./gradlew :app:build
```

**2. Run application on Docker**
```bash
docker compose up
```

**3. Explore Rest APIs**
#### Home Page

| Method | Url            | Decription | Sample Valid Request Body | 
|--------|----------------| ---------- | --------------------------- |
| GET    | localhost:8080 | Home Page |  |


#### Users

| Method | Url        | Decription | Sample Valid Request Body |
|--------|------------| ---------- | --------------------------- |
| GET    | /user/all  | Get all users |  |
| POST    | /user/save | Save a user | [JSON](#save) |

## Sample Valid JSON Request Bodys

##### <a id="save">Save a user -> /user/save </a>
```json
{
  "name": "Example",
  "email": "example@gmail.com"
}
```

## **Modules**

### App
It is the only executable module in the project. It is structured to have domains to maximize initial development productivity.

It is also responsible for providing APIs and setting up frameworks for services.

### Core
Each submodule of this module is responsible for one domain service.

This must make the modular structure grow with the growth of the service.

#### core:common
This is the module that contains the most common parts, it is the foundation for most of the other modules

The settings and configuration of the project are located here

#### core:model

Define all the models used in the project, these models are business models or return data types

<br/>

### Storage
Submodules of this module are responsible for integrating with the various storages.

#### storage:database
This module shows an example of connecting to `MySql` using `Spring-Data-JPA`.

<br/>

### Support
Submodules of this module are responsible for additional support.

#### support:logging
This module supports logging of service and has a dependency added for distributed tracing support.

It also includes dependencies to support `Sentry`.

#### support:monitoring
This module supports monitoring of services.

<br/>

## Dependency Management
All dependency versioning is done through `gradle.properties` file.

If you want to add a new dependency, put the version in `gradle.properties` and load it in `build.gradle.kts`.

<br/>

## Runtime Profiles

### dev
This profile exists for deploying Development environments.

### staging
This profile exists for deploying Staging environments.

<br/>

## Test Tasks & Tags

### test
This is a collection of test-tasks that we want to run on `CI`.

If you want to change the settings, modify the `build.gradle.kts` file.

### unitTest
This is a group of tests that typically have no dependencies, are fast to run, and test a single feature.

## Recommended Preferences

### Git Hook
This setting makes run `lint` on every commit.

```
$ git config core.hookspath .githooks
```

### IntelliJ IDEA
This setting makes it easier to run the `test code` out of the box.

```
// Gradle Build and run with IntelliJ IDEA
Build, Execution, Deployment > Build Tools > Gradle > Run tests using > IntelliJ IDEA	
```

---

## Supported By
<div align="center"><a href="https://jb.gg/OpenSourceSupport"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.png" alt="JetBrains Logo (Main) logo." width="240"></a></div>
