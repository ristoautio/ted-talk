# How to

- using Java 21

build and run tests

```
mvn clean install
```

run the application

```
./mvnw spring-boot:run
```

| Endpoint             | Description                      |
|----------------------|----------------------------------|
| GET /api/speakers    | search speakers with pagination  |
| GET /api/talks       | search ted talks with pagination |
| POST /api/talk       | Add a new talk                   |
| PATCH /api/talk/{id} | Update an existing talk          |

More detailed examples can be seen in the test, but the for example the speakers search 
by the view/like ratio can be called with curl like so
```
curl localhost:8080/api/speakers?size=10&sort=ratio,DESC
```

# Decisions

## Importing the data

Triggering the import now on start up so that the csv is passed to a service 
that takes the csv file. This way it would be also easy create alternative ways to 
provide the file for example via a controller that receives the file and passes it
to the service

I picked opencsv to parse the file. It could also have had validation added already
when reading the file but to save time added a custom mapper that skips the rows
that have errors

## DB

Used the in-memory database to save time, but something like postgres would have 
been a better fit 

With more time I would also have put the author in a different table and that would
have made it easier to create better search functionality

## Data management

Included a search endpoint for the talks 
For adding and editing there are now basic POST and PATCH endpoints

## Search

For the influence analysis I went with a simple search that gives the like/view ratio.
With this approach it would be easy to add better ratios or adjust how much weight
a view or like gets. It then also makes it possible to use the same search for doing
searches based on other criteria

In a real case I would have used database migrations and instead of the native queries
there could be views that could also take into account days since publication. I
started without the native queries but had some issues with the data projections so
to save time switched to native queries


## Missing

- There is no authorization in place but the management endpoint could have them
- db migrations (and a real database)
- no unit tests
