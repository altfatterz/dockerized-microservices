### Getting started

1. Clone the repository

   ```
   $ git clone https://github.com/altfatterz/dockerized-microservices
   ```

2. Start docker

3. Build the project and create docker images

   ```
   $ mvn clean package
   ```

4. Verify docker images created:

   ```
   $ docker images

   REPOSITORY                   TAG                 IMAGE ID            CREATED             SIZE
   altfatterz/mongodb-sample    latest              5572c82d29bc        6 seconds ago       211.3 MB
   altfatterz/mongodb           latest              f8252bb4083b        12 seconds ago      401 MB
   altfatterz/mysql-sample      latest              7e5a1caca131        34 seconds ago      235.6 MB
   altfatterz/mysql             latest              2b4c7bc5b1ee        58 seconds ago      383.4 MB
   mongo                        3.3                 aa57b208cc5d        11 days ago         401 MB
   mysql                        5.7.15              18f13d72f7f0        2 weeks ago         383.4 MB
   frolvlad/alpine-oraclejdk8   slim                f8103909759b        2 weeks ago         167.1 MB
   ```

5. Start up the `mysql-sample` service

   ```
   $ cd mysql-sample
   $ docker-compose up
   ```

6. Verify that the containers are created and started

   ```
   $ docker ps

   CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS              PORTS                                            NAMES
   bf168fb3aa4e        altfatterz/mysql-sample   "./start.sh"             11 seconds ago      Up 10 seconds       0.0.0.0:8000->8000/tcp, 0.0.0.0:8080->8080/tcp   service-using-mysql-datastore
   9b3224b1fc0a        altfatterz/mysql          "docker-entrypoint.sh"   12 seconds ago      Up 11 seconds       0.0.0.0:3306->3306/tcp                           mysql-datastore
   ```

7. Verify that the service is up

   ```
   http :8080/health
   
   {
       "db": {
           "database": "MySQL",
           "hello": 1,
           "status": "UP"
       },
       "diskSpace": {
           "free": 62444187648,
           "status": "UP",
           "threshold": 10485760,
           "total": 63375708160
       },
       "status": "UP"
   }
   ```

8. Call the service

   ```
   http :8080
   
   [
       {   
           "firstName": "Han",
           "id": 1,
           "lastName": "Solo"
       }
   ]
   ```
