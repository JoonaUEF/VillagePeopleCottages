CREATE TABLE Toimipiste (
	toimipiste_id INT PRIMARY KEY AUTO_INCREMENT,
	nimi VARCHAR (40),
	lahiosoite VARCHAR (40),
	postitoimipaikka VARCHAR (30),
	postinro CHAR (5),
	email VARCHAR (50),
	puhelinnro VARCHAR (15));
	
CREATE TABLE Asiakas (
	asiakas_id INT PRIMARY KEY AUTO_INCREMENT,
	etunimi VARCHAR (20),
	sukunimi VARCHAR (40),
	lahiosoite VARCHAR (40),
	postitoimipaikka VARCHAR (30),
	postinro CHAR (5),
	email VARCHAR (50),
	puhelinnro VARCHAR (15));
	
CREATE TABLE Varaus (
	varaus_id INT PRIMARY KEY AUTO_INCREMENT,
	asiakas_id INT,
	toimipiste_id INT,
	varattu_pvm DATETIME,
	vahvistus_pvm DATETIME,
	FOREIGN KEY (toimipiste_id) REFERENCES Toimipiste (toimipiste_id) ON DELETE CASCADE,
	FOREIGN KEY (asiakas_id) REFERENCES Asiakas (asiakas_id) ON DELETE CASCADE
);

CREATE TABLE Lasku (
	lasku_id INT PRIMARY KEY AUTO_INCREMENT,
	varaus_id INT,
	asiakas_id INT,
        tila INT,
	nimi VARCHAR (60),
	lahiosoite VARCHAR (40),
	postitoimipaikka VARCHAR (30),
	postinro CHAR (5),
	summa DOUBLE NOT NULL,
	alv DOUBLE NOT NULL,
        paivays DATETIME,
        toimipiste_id INT,
	FOREIGN KEY (varaus_id) REFERENCES Varaus (varaus_id) ON DELETE CASCADE,
	FOREIGN KEY (asiakas_id) REFERENCES Asiakas (asiakas_id) ON DELETE CASCADE
 	);
	
CREATE TABLE Palvelu (
	palvelu_id INT PRIMARY KEY AUTO_INCREMENT,
	toimipiste_id INT,
	nimi VARCHAR (40),
	tyyppi INT,
	kuvaus VARCHAR (255),
	hinta DOUBLE NOT NULL,
	alv DOUBLE NOT NULL,
	FOREIGN KEY (toimipiste_id) REFERENCES Toimipiste (toimipiste_id) ON DELETE CASCADE
	);
	
CREATE TABLE PalveluVaraus (
	varaus_id INT,
	palvelu_id INT,
	alku DATETIME,
    	loppu DATETIME,
	PRIMARY KEY (palvelu_id, varaus_id),
	FOREIGN KEY (varaus_id) REFERENCES Varaus (varaus_id) ON DELETE CASCADE,
	FOREIGN KEY (palvelu_id) REFERENCES Palvelu (palvelu_id) ON DELETE CASCADE
	 );

CREATE INDEX Toimipiste_nimi_index
 ON Toimipiste(nimi);

CREATE INDEX Asiakas_sukunimi_index
 ON Asiakas(sukunimi); 
 
CREATE INDEX Asiakas_etunimi_index
 ON Asiakas(etunimi); 

CREATE INDEX Lasku_nimi_index
 ON Lasku(nimi); 

CREATE UNIQUE INDEX Lasku_varaus_id_index
 ON Lasku(varaus_id); 
 
CREATE INDEX Palvelu_nimi_index
 ON Palvelu(nimi);

CREATE INDEX vp_varaus_id_index
 ON Varauksen_palvelut(varaus_id);
 
CREATE INDEX vp_palvelu_id_index
 ON Varauksen_palvelut(palvelu_id);
 
CREATE INDEX  palvelu_toimipiste_id_index
 ON Palvelu(toimipiste_id);
 
CREATE INDEX Lasku_asiakas_id_index
 ON Lasku(asiakas_id);
 
CREATE INDEX  varaus_toimipiste_id_index
 ON Varaus(toimipiste_id);
 
CREATE INDEX  varaus_asiakas_id_index
 ON Varaus(asiakas_id);
