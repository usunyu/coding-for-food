SELECT TenantName
FROM Tenants,
	(SELECT Tenants.TenantsID, COUNT(AptTenants.AptID) AS Count
	 FROM Tenants, AptTenants
	 WHERE Tenants.TenantsID = AptTenants.TenantsID
	 GROUP BY Tenants.TenantsID) T
WHERE Tenants.TenantsID = T.TenantsID AND Count > 1

SELECT TenantName
FROM Tenants
INNER JOIN
	(SELECT TenantsID
	 FROM AptTenants
	 GROUP BY TenantsID
	 HAVING COUNT(*) > 1) C
ON Tenants.TenantsID = C.TenantsID