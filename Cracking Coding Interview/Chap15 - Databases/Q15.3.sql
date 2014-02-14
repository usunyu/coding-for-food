UPDATE Requests
SET Status = 'Closed'
WHERE AptID IN
	(SELECT AptID
	 FROM Apartments
	 WHERE BuildingID = 11)
