# Blogger

[![Build Status](https://api.travis-ci.org/RadheSravan/blogger.svg?branch=master)](https://travis-ci.org/RadheSravan/blogger)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This is a simple blog like application based on Spring Boot. The application has been deployed on Heroku and can be accessed on [this](https://technocrat.herokuapp.com) url.

## Getting Started

Following these instructions, you can have a local instance of the project up and running. The commands specified as part of these instructions are very much specific to Linux machines. However with minor tweaks, they can as well be used in others.

### Prerequisites

The local instance of this project will be run as a docker image. Hence it is required to have docker and docker compose installed. Detailed instructions on installation of these can be found in their respective guides 

Docker - https://www.docker.com/community-edition
Docker Compose - https://docs.docker.com/compose/install/

### Cloning the project

Clone this project by running the following command in a terminal

`git clone https://github.com/RadheSravan/blogger.git`

After this navigate to the root directory of the project i.e. **blogger**

### Running the application 

Before running the application, an environment variable **PROFILE** needs to be set with the value as **development**. This is in order for the Spring service to understand to use the local instance of PostgreSQL database for persistence. In Linux based systems, this can be done by running the following command in terminal

`export PROFILE=development`

In the root directory of the project, run the following command 

`./gradlew buildDocker`

This command pulls in all the required dependencies from the respective repositories, builds the source code and creates a docker image of the application based on the steps provided in the **Dockerfile**. Once the docker image for the application has been created, it can then be run in a container. Since the application uses PostgreSQL for persistence, an entry for the same has been made in **docker-compose.yml**. However if required the application can also be configured to use the in-memory HyperSQL database by commenting out the jpa and datasource related configurations from **application.yml**.

Run the following command to bring up the containers for both the services: **blogger** and **bloggerDB**

`docker-compose up -d`

The -d option is to run the command as a daemon process. This command pulls in the docker image for PostgreSQL when run for the first time and brings up the containers for both the services. It might take a while before the Spring context for the application starts. This can be checked in the docker compose logs by running the following command

`docker-compose logs`

Once the application has been started it can be accessed at http://localhost:8081 which should redirect to the login page of the application.