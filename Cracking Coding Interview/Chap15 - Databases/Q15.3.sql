/*
Building #11 is undergoing a major renovation. 
Implement a query to close all requests from apartments in this building.
*/

UPDATE Requests
SET Status = 'Closed'
WHERE AptID IN
	(SELECT AptID
	 FROM Apartments
	 WHERE BuildingID = 11)
