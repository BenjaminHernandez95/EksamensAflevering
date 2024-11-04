# Trip Planning Application


Overview
This API, built using Javalin, is designed for managing trips and their associated guides. 
It provides CRUD operations for trips and allows for assigning a guide to a specific trip. 
When retrieving a trip by its ID, the response also includes information about the guide leading that trip (if assigned).

Getting Started
Run the Application
Start the Javalin server on port 8080. The server is set up to automatically register all routes.

Endpoints
Here’s a quick reference for each available endpoint:

1. Get All Trips
   Endpoint: GET /trips
   Description: Fetches a list of all trips.
   Response: Returns a JSON array of all trips, each with basic trip details.

GET http://localhost:8080/trips

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:10:50 GMT
Content-Type: application/json
Content-Length: 527

[
{
"id": 2,
"startTime": "2024-12-01T10:00:00",
"endTime": "2024-12-01T16:00:00",
"startPosition": "Beach Area",
"name": "Beach Adventure",
"price": 150.0,
"category": "BEACH",
"guide": null
},
{
"id": 3,
"startTime": "2024-12-05T09:00:00",
"endTime": "2024-12-05T17:00:00",
"startPosition": "City Center",
"name": "City Exploration",
"price": 200.0,
"category": "CITY",
"guide": null
},
{
"id": 1,
"startTime": "2024-12-01T10:00:00",
"endTime": "2024-12-01T16:00:00",
"startPosition": "Beach Area",
"name": "Beach Adventure",
"price": 150.0,
"category": "BEACH",
"guide": null
}
]
Response file saved.
> 2024-11-04T121050.200.json

Response code: 200 (OK); Time: 8ms (8 ms); Content length: 527 bytes (527 B)




2. Get Trip by ID (with Guide Info)
   Endpoint: GET /trips/{id}
   Description: Fetches a specific trip by its ID. The response includes the guide information if a guide is assigned to the trip.
   Response: Returns a JSON object with trip details and guide information (guide ID, name, and years of experience).

GET http://localhost:8080/trips/1

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:15:15 GMT
Content-Type: application/json
Content-Length: 236

{
"id": 1,
"startTime": "2024-12-01T10:00:00",
"endTime": "2024-12-01T16:00:00",
"startPosition": "Beach Area",
"name": "Beach Adventure",
"price": 150.0,
"category": "BEACH",
"guide": {
"id": 1,
"firstName": "John",
"lastName": "Doe",
"yearsOfExperience": 5
}
}
Response file saved.
> 2024-11-04T121515.200.json

Response code: 200 (OK); Time: 6ms (6 ms); Content length: 236 bytes (236 B)




3. Create a New Trip
   Endpoint: POST /trips
   Description: Creates a new trip with the specified details.
   Request Body:
   {
   "startTime": "2024-12-01T10:00:00",
   "endTime": "2024-12-01T16:00:00",
   "startPosition": "Beach Area",
   "name": "Beach Adventure",
   "price": 150.0,
   "category": "BEACH"
   }
   Response: Returns the created trip with its assigned ID.
4. Update a Trip
   Endpoint: PUT /trips/{id}
   Description: Updates details of an existing trip by its ID.
   Request Body: Same structure as the "Create Trip" endpoint.
   Response: Returns the updated trip details.
5. Delete a Trip
   Endpoint: DELETE /trips/{id}
   Description: Deletes a specific trip by its ID.
   Response: Returns a 204 No Content status on success.
6. Add a Guide to a Trip
   Endpoint: PUT /trips/{tripId}/guides/{guideId}
   Description: Associates an existing guide with a specific trip.
   Response: Returns a 204 No Content status on success.
7. Populate Database with Sample Data
   Endpoint: POST /trips/populate
   Description: Populates the database with sample trips and guides for testing.
   Response: A success message confirming data has been added.

### 3.3.5
Using PUT to Assign a Guide to a Trip
PUT was chosen over POST because we are updating an existing trip by adding a relationship, rather than creating a new resource. PUT is better for this type of update.
