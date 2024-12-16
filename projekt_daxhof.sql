insert into projekt_daxhof.patients (
	firstNamePatients
    , lastNamePatients
    , svnPatients
    , birthDatePatients
    , streetPatients
    , streetNumberPatients
    , postalCodePatients
    , cityPatients
    , idGender
    , idNationality
    , idInsurance
    )
values (
	'Marie'
    , 'Hofer'
    , '5845 190304'
    , '2004-03-19'
    , 'Rinneggerstraße'
    , 11
    , 8061
    , 'St. Radegund'
    , 1
    , 1
    , 1
    );
    
INSERT INTO `projekt_daxhof`.`gender`
(`genderPatients`)
VALUES
('female');

INSERT INTO `projekt_daxhof`.`gender`
(`genderPatients`)
VALUES
('male');

INSERT INTO `projekt_daxhof`.`gender`
(`genderPatients`)
VALUES
('divers');

INSERT INTO `projekt_daxhof`.`insurance`
(`insurancePatients`)
VALUES
('ÖGK');

INSERT INTO `projekt_daxhof`.`insurance`
(`insurancePatients`)
VALUES
('SVS');

INSERT INTO `projekt_daxhof`.`insurance`
(`insurancePatients`)
VALUES
('BVAEB');

INSERT INTO `projekt_daxhof`.`insurance`
(`insurancePatients`)
VALUES
('Others');

INSERT INTO `projekt_daxhof`.`nationality`
(`nationalityPatients`)
VALUES
('Austria');

INSERT INTO `projekt_daxhof`.`nationality`
(`nationalityPatients`)
VALUES
('Germany');

INSERT INTO `projekt_daxhof`.`nationality`
(`nationalityPatients`)
VALUES
('Switzerland');

INSERT INTO `projekt_daxhof`.`nationality`
(`nationalityPatients`)
VALUES
('Liechtenstein');
