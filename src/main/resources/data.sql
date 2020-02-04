INSERT INTO `user` (id, first_name, last_name, phone, email, date_joined, card_number, username, password, account_status)
VALUES (3, 'Anton', 'Shevchenko', '123-456-78-90', 'd1853327@urhen.com', '2020-01-20 18:38:25', 1000000000, 'anton', '$2a$11$avyZ9vqFQCaEufoT2npVku2uoAX3ibp/37d7H1DHu9460mG04TSMW', 'ADMIN');

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

INSERT INTO `payment` (id, total, payment_date, user_id)
VALUES (2, 279.95, '2020-01-22 19:14:33', 3),
(3, 179.96, '2020-01-22 19:19:21', 3),
(4, 69.99, '2020-01-22 19:25:47', 3),
(5, 59.99, '2020-01-22 19:34:49', 3),
(6, 129.97, '2020-01-28 22:43:27', 3),
(7, 669.90, '2020-01-29 02:31:01', 3),
(8, 669.90, '2020-01-29 02:38:55', 3),
(9, 289.95, '2020-02-01 14:05:06', 3),
(10, 289.95, '2020-02-01 14:06:46', 3),
(11, 289.95, '2020-02-01 14:09:20', 3),
(12, 289.95, '2020-02-01 14:11:11', 3);

INSERT INTO `ticket` (id, `date`, quantity, exposition_id, payment_id) VALUES
(2, '2020-02-03', 3, 3, 2),
(3, '2020-01-22', 2, 7, 2),
(4, '2020-01-24', 2, 2, 3),
(5, '2020-01-24', 2, 5, 3),
(6, '2020-02-03', 1, 6, 4),
(7, '2020-01-24', 1, 5, 5),
(8, '2020-01-29', 2, 2, 6),
(9, '2020-02-01', 1, 6, 6),
(10, '2020-01-29', 1, 2, 7),
(11, '2020-01-31', 3, 5, 7),
(12, '2020-02-04', 4, 6, 7),
(13, '2020-02-01', 2, 8, 7),
(14, '2020-01-29', 1, 2, 8),
(15, '2020-01-31', 3, 5, 8),
(16, '2020-02-05', 4, 6, 8),
(17, '2020-02-01', 2, 8, 8),
(18, '2020-02-02', 3, 4, 9),
(19, '2020-02-18', 2, 6, 9),
(20, '2020-02-08', 3, 4, 10),
(21, '2020-02-21', 2, 6, 10),
(22, '2020-02-08', 3, 4, 11),
(23, '2020-02-21', 2, 6, 11),
(24, '2020-02-08', 3, 4, 12),
(25, '2020-02-21', 2, 6, 12);