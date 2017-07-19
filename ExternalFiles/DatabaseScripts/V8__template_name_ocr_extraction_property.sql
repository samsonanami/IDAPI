INSERT INTO `ocr_extraction_field`
(`ID`,
`FIELD`)
VALUES
(25,
'TemplateName');


INSERT INTO `resource_name_ocr_extraction_field`
(`ID`,
`resource_name_ID`,
`ocr_extraction_field_ID`)
VALUES
(42,1,25),
(43,3,25),
(44,4,25);
