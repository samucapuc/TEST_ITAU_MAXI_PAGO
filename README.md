# testeItauMaxPago
Teste para vaga de desenvolvedor

Scripts:

CREATE TABLE CITY( 
`ID` BIGINT NOT NULL , 
`NAME` VARCHAR(100) NOT NULL , 
`LATITUDE` DOUBLE NOT NULL , 
`LONGITUDE` DOUBLE NOT NULL , 
PRIMARY KEY (`ID`), 
INDEX `IDX_CITY_NAME` (`NAME`(100)));

M�todo de calculo de dist�ncia
https://www.geodatasource.com