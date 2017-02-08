# Database service

## API Endpoints

### /sdelab/person [GET]

Get the list of people in the Database

```json
[
   {
      "id":1,
      "birthdate":"1992-05-06",
      "city":"Trento,it",
      "name":"Nagy Sándor Tibor",
      "stepGoal":6000
   },
   {
      "id":2,
      "birthdate":"1945-03-07",
      "city":"Budapest,hu",
      "name":"Other Test Name",
      "stepGoal":3000
   },
   {
      "id":3,
      "birthdate":"1990-05-19",
      "city":"Debrecen,hu",
      "name":"Teszt Remote",
      "stepGoal":4500
   }
]
```

### /sdelab/person/{id}  [GET, PUT]

Get the details and all the steps related to a person.

```json
{
   "id":1,
   "birthdate":"1992-05-06",
   "city":"Trento,it",
   "name":"Nagy Sándor Tibor",
   "stepGoal":6000,
   "steps":[
      {
         "id":1,
         "date":"2017-01-25",
         "number":6019,
         "personId":1
      },
      {
         "id":2,
         "date":"2017-01-26",
         "number":4000,
         "personId":1
      },
      {
         "id":6,
         "date":"2017-01-30",
         "number":4000,
         "personId":1
      },
      {
         "id":11,
         "date":"2017-02-08",
         "number":3000,
         "personId":1
      }
   ]
}
```

When you use the PUT method to change the profile only send the profile data without the steps, steps will be updated by the steps endpoint.

### /sdelab/steps/{id}  [GET, PUT]

Get the details of a step:

```json
{
   "id":1,
   "date":"2017-01-25",
   "number":6019,
   "personId":1
}
```

## Database information

I used MySQL as my database since SQLite is not good for Heroku as with every application reset the database would be gone.  
Since the default solution offered for the free version of Heroku did not sit well with me, I installed and set-up a MySQL server on my home server with the following details for this project:

**Host**: *salaander.hopto.org*  
**User**: *'introsde'@'%'*  
**Password**: *...*    
**Database**: *introsde*  

The database model looks like this:

![Image of the DB model](http://salaander.hu/sde/sde_db.png)

**Problems to solve:**

* JPA caches the results, so if there is a data update outside the Database Service process it won't be reflected for a time.

## Copyright

&copy; Sándor Tibor Nagy as the final project for Introduction to Service Design and Engineering course 2016/2017 at [UNITN](http://www.unitn.it/)