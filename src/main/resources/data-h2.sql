insert into drivers(id, type, first_name, last_name) values
(1, 'REGULAR', 'Szymon', 'Stanislawski'),
(2, 'VIP', 'Tomasz', 'Kwiatkowski'),
(3, 'VIP', 'Jakub', 'Nabrzuzka');

insert into cars (id, driver_id, brand, model) values
(1, 1, 'Saab', '9-3SS'),
(2, 2, 'Saab', '9-5 Aero'),
(3, 3, 'Saab', '9-5NG');
