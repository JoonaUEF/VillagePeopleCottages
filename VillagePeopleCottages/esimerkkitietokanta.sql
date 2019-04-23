/**
 * VillagePeopleCottages esimerkkitietokantasisällön luonti sql
 *
 * Author:  Lassi Puurunen
 * Created: Apr 23, 2019
 */


INSERT INTO Toimipiste (toimipiste_id, nimi, lahiosoite, postinro, postitoimipaikka, email, puhelinnro)
VALUES (1, 'VillagePeople Mökit Metsässä', 'Metsikkötie 5', '10000', 'METSÄMÖKKI', 'posti@metsa.fi', '012-1233654' );

INSERT INTO Toimipiste (toimipiste_id, nimi, lahiosoite, postinro, postitoimipaikka, email, puhelinnro)
VALUES (2, 'VillagePeople Laskettelukeskus', 'Mäkikatu 5', '40300', 'LASKETTELU', 'vastaava.henkilo@laskettelukeskus.fi', '145 678 9876' );



INSERT INTO Palvelu (palvelu_id, toimipiste_id, tyyppi, nimi, kuvaus, hinta, alv)
VALUES (1, 1, 0, 'Hirsimökki nro 1', 'Hirsimökkimajoitus keskellä metsää. Rauhallinen sijainti', 100, 24);

INSERT INTO Palvelu (palvelu_id, toimipiste_id, tyyppi, nimi, kuvaus, hinta, alv)
VALUES (2, 1, 0, 'Hirsimökki nro 2', 'Hirsimökkimajoitus keskellä metsää. Rauhallinen sijainti', 100, 24);

INSERT INTO Palvelu (palvelu_id, toimipiste_id, tyyppi, nimi, kuvaus, hinta, alv)
VALUES (3, 2, 0, 'Huoneistohotelli huone 123', 'Luksusmajoitus laskettelukeskuksen huoneistohotellissa.', 200, 48);

INSERT INTO Palvelu (palvelu_id, toimipiste_id, tyyppi, nimi, kuvaus, hinta, alv)
VALUES (4, 2, 0, 'Huoneistohotelli huone 321', 'Luksusmajoitus laskettelukeskuksen huoneistohotellissa.', 200, 48);

INSERT INTO Palvelu (palvelu_id, toimipiste_id, tyyppi, nimi, kuvaus, hinta, alv)
VALUES (5, 1, 1, 'Poroajelu', 'kesto 2 tuntia', 20, 4.8);

INSERT INTO Palvelu (palvelu_id, toimipiste_id, tyyppi, nimi, kuvaus, hinta, alv)
VALUES (6, 1, 1, 'Kalastusreissu', 'kesto 8 tuntia', 40, 9.6);

INSERT INTO Palvelu (palvelu_id, toimipiste_id, tyyppi, nimi, kuvaus, hinta, alv)
VALUES (7, 2, 1, 'Buffet-aamiainen', 'Tarjoillaan aamuisin 07-11 välillä', 20, 4.8);

INSERT INTO Palvelu (palvelu_id, toimipiste_id, tyyppi, nimi, kuvaus, hinta, alv)
VALUES (8, 2, 1, 'Buffet-illallinen', 'Tarjoillaan iltaisin 17-21 välillä', 40, 9.6);



INSERT INTO Asiakas (asiakas_id, etunimi, sukunimi, lahiosoite, postinro, postitoimipaikka, email, puhelinnro)
VALUES (1, 'Essi', 'Esimerkki', 'Asiakaskatu 12 b 45', '65432', 'Asiakkala', 'esimerkki@asiakas.fi', '123 1234 123');

INSERT INTO Asiakas (asiakas_id, etunimi, sukunimi, lahiosoite, postinro, postitoimipaikka, email, puhelinnro)
VALUES (2, 'Anssi', 'Asiakas', 'Esimerkkikatu 22 a', '65443', 'Esimerkkilä', 'anssi@esimerkki.fi', '321 4321 321');



INSERT INTO Varaus (varaus_id, asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm)
VALUES (1, 1, 1, '2019-01-01', '2019-01-05');

INSERT INTO Varaus (varaus_id, asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm)
VALUES (2, 2, 2, '2019-02-02', '2019-02-06');



INSERT INTO PalveluVaraus (palvelu_id, varaus_id, alku, loppu)
VALUES (1, 1, '2019-01-05 14:00:00', '2019-01-08 12:00:00');

INSERT INTO PalveluVaraus (palvelu_id, varaus_id, alku, loppu)
VALUES (6, 1, '2019-01-06 12:00:00', '2019-01-06 20:00:00');

INSERT INTO PalveluVaraus (palvelu_id, varaus_id, alku, loppu)
VALUES (3, 2, '2019-02-06 14:00:00', '2019-02-09 12:00:00');

INSERT INTO PalveluVaraus (palvelu_id, varaus_id, alku, loppu)
VALUES (7, 2, '2019-02-07 7:00:00', '2019-02-07 11:00:00');



INSERT INTO Lasku (lasku_id, varaus_id, asiakas_id, tila, nimi, lahiosoite, postinro, postitoimipaikka, summa, alv)
VALUES (1, 1, 1, 0, 'Essi Esimerkki', 'Asiakaskatu 12 b 45', '65432', 'Asiakkala', 1000, 240);

INSERT INTO Lasku (lasku_id, varaus_id, asiakas_id, tila, nimi, lahiosoite, postinro, postitoimipaikka, summa, alv)
VALUES (2, 2, 2, 1, 'Anssi Asiakas', 'Esimerkkikatu 22 a', '65443', 'Esimerkkilä', 2000, 480);