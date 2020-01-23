INSERT INTO `user` (id, first_name, last_name, phone, email, date_joined, card_number, username, password, account_status)
VALUES (3, 'Anton', 'Shevchenko', '123-456-78-90', 'admin@gmail.com', '2020-01-20 18:38:25', 1000000000, 'anton', '$2a$11$avyZ9vqFQCaEufoT2npVku2uoAX3ibp/37d7H1DHu9460mG04TSMW', 'CUSTOMER');

INSERT INTO `hall` (id, title, area, image_path, `type`) VALUES (1, 'Large Hall', 500,' img/hall_large.jpg', 'LARGE'),
(2, 'Medium Hall', 200,' img/hall_medium.jpg', 'MEDIUM'), (3, 'Small Hall', 100, 'img/hall_small.jpg', 'SMALL');

INSERT INTO `exposition` (id, title, description, price, image_path, start_date, end_date, hall_id)
VALUES (1, 'exposition 1', 'exposition description 1', 19.99, 'img/exposition.jpg', '2020-02-01', '2020-02-08', 1),
(2, 'exposition 2', 'exposition description 2', 29.99, 'img/exposition.jpg', '2020-02-09', '2020-02-16', 1),
(3, 'exposition 3', 'exposition description 3', 39.99, 'img/exposition.jpg', '2020-02-17', '2020-02-24', 1),
(4, 'exposition 4', 'exposition description 4', 49.99, 'img/exposition.jpg', '2020-02-02', '2020-02-09', 2),
(5, 'exposition 5', 'exposition description 5', 59.99, 'img/exposition.jpg', '2020-02-10', '2020-02-17', 2),
(6, 'exposition 6', 'exposition description 6', 69.99, 'img/exposition.jpg', '2020-02-18', '2020-02-25', 2),
(7, 'exposition 7', 'exposition description 7', 79.99, 'img/exposition.jpg', '2020-02-03', '2020-02-10', 3),
(8, 'exposition 8', 'exposition description 8', 89.99, 'img/exposition.jpg', '2020-02-11', '2020-02-18', 3),
(9, 'exposition 9', 'exposition description 9', 99.99, 'img/exposition.jpg', '2020-02-19', '2020-02-26',3);