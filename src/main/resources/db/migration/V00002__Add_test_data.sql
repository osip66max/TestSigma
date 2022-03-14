INSERT INTO car (number, name)
VALUES ('М327ТС66', 'Кулаков Гавриил Владимирович'),
       ('Е921СТ96', 'Копылов Ибрагил Семенович');

INSERT INTO parking (number, occupancy)
VALUES (900, false),
       (116, true);

INSERT INTO time_price (time, price, car_id, parking_id)
VALUES (5, 96, 1, 1),
       (22, 98, 1, 2),
       (11, 96, 2, 1),
       (12, 98, 2, 2);