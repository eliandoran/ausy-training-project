DROP FUNCTION IF EXISTS `generate_random_first_name`;
DROP FUNCTION IF EXISTS `generate_random_last_name`;

delimiter $$
CREATE FUNCTION `generate_random_first_name`() RETURNS varchar(100)
BEGIN
	RETURN (
		SELECT `first_name`
		FROM (
			SELECT 'Alex'     AS `first_name` UNION SELECT 'Adam '     AS `first_name` UNION
			SELECT 'Corina'   AS `first_name` UNION SELECT 'Cosmin'    AS `first_name` UNION
			SELECT 'David'    AS `first_name` UNION SELECT 'Dana'      AS `first_name` UNION
            SELECT 'Neculai'  AS `first_name` UNION SELECT 'Pompiliu'  AS `first_name` UNION
            SELECT 'Marian'   AS `first_name` UNION SELECT 'Marilena'  AS `first_name` UNION
            SELECT 'Mădălina' AS `first_name` UNION SELECT 'Sebastian' AS `first_name` UNION
            SELECT 'George'   AS `first_name` UNION SELECT 'Horațiu'   AS `first_name`) AS `random_first_names`
		ORDER BY RAND()
		LIMIT 1
	);
END$$

delimiter $$
CREATE FUNCTION `generate_random_last_name`() RETURNS varchar(100)
BEGIN
	RETURN (
		SELECT `last_name`
		FROM (
			SELECT 'Șerban'     AS `last_name` UNION SELECT 'Nicolescu'    AS `last_name` UNION
			SELECT 'Dumitru'    AS `last_name` UNION SELECT 'Lungu'        AS `last_name` UNION
			SELECT 'Bălan'      AS `last_name` UNION SELECT 'Constantin'   AS `last_name` UNION
            SELECT 'Albu'       AS `last_name` UNION SELECT 'Lupei'        AS `last_name` UNION
            SELECT 'Ardelean'   AS `last_name` UNION SELECT 'Dalca'        AS `last_name` UNION
            SELECT 'Petrescu'   AS `last_name` UNION SELECT 'Albescu'      AS `last_name` UNION
            SELECT 'Ionesco'    AS `last_name` UNION SELECT 'Gabor'        AS `last_name` UNION
            SELECT 'Iliescu'    AS `last_name` UNION SELECT 'Alexandrescu' AS `last_name` UNION
            SELECT 'Popescu'    AS `last_name` UNION SELECT 'Petran'       AS `last_name` UNION
            SELECT 'Adam'       AS `last_name` UNION SELECT 'Vladimirescu' AS `last_name`) AS `random_last_names`
		ORDER BY RAND()
		LIMIT 1
	);
END$$