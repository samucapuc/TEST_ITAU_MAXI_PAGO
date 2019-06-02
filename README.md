# Test performed for MaxiPago
Test for developer position
By Samuel Oliveira Chaves
06/01/2019

### Build Instructions:
1. Ensure that JAVA 8 is installed;
2. Tomcat 8.5 was used;
3. Run the scripts before the application runs;
4. Make sure that the application is pointing to the JDK 8;
5. Run maven install to generate .war file
6. Removes the version in the nomenclature (name) of .war

### Scripts:
```
CREATE DATABASE maxipago;
CREATE TABLE CITY (
	ID BIGINT NOT NULL,
	NAME VARCHAR (100) NOT NULL,
	LATITUDE DOUBLE NOT NULL,
	LONGITUDE DOUBLE NOT NULL,
	PRIMARY KEY (ID),
	INDEX IDX_CITY_NAME (NAME (100)));
```

### Distance calculation method
The method used was the **Vicenty** that the formula is based on the fact that the earth is an [Oblatos Spheroids](https://en.wikipedia.org/wiki/Spheroid#Oblate_spheroids), different from other formulas that are base on the earth as spheres or linear (in this case, ignoring reliefs). Therefore, the Vicenty method is more accurate and the calculation error is between 0.5 and 1 meter. Although this method is slower than the others, it is widely used for the terrestrial ellipsoid using the **WGS-84** **GPS** standard.

### Combination Method
We used a simple algorithm where the same function is executed several times (recursion), storing the city of the position (**I**) of the list of cities in a temporary array and whenever index (which started with zero value but is incremented whenever the temporary array is filled) is of the size of the temporary array, the algorithm includes in the list of combinations. This algorithm uses brute force with O(n2) performance to match, but on the other hand it is scalable and can be used to combine several cities and combinations.
