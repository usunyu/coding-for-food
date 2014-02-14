SELECT BuildingName, ISNULL(Count2, 0) AS Count
FROM Buildings
LEFT JOIN
	(SELECT Buildings.BuildingID, COUNT(Count1) AS Count2
	 FROM Buildings,
	 	(SELECT T1.AptID, BuildingID, Count1
	 	 FROM Apartments,
	 	 	(SELECT Apartments.AptID, COUNT(Requests.AptID) AS Count1
	 	 	 FROM Apartments, Requests
	 	 	 WHERE Apartments.AptID = Requests.AptID
	 	 	 AND Status = 'Open'
	 	 	 GROUP BY Apartments.AptID) T1
	 	 WHERE Apartments.AptID = T1.AptID) T2
	 WHERE Buildings.BuildingID = T2.BuildingID
	 GROUP BY Buildings.BuildingID) T3
ON Buildings.BuildingID = T3.BuildingID

SELECT BuildingName, ISNULL(Count, 0) AS Count
FROM Buildings
LEFT JOIN
	(SELECT Apartments.BuildingID, COUNT(*) AS Count
	 FROM Apartments INNER JOIN Requests
	 ON Apartments.AptID = Requests.AptID
	 WHERE Requests.Status = 'Open'
	 GROUP BY Apartments.BuildingID) ReqCounts
ON ReqCounts.BuildingID = Buildings.BuildingID
