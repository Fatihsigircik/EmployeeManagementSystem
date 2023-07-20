-- Departments tablosuna örnek kayıtlar ekleme
INSERT INTO department (department_name, create_date) VALUES ('İnsan Kaynakları', CURRENT_TIMESTAMP);
INSERT INTO department (department_name, create_date) VALUES ('Finans', CURRENT_TIMESTAMP);
INSERT INTO department (department_name, create_date) VALUES ('Bilgi İşlem', CURRENT_TIMESTAMP);

-- Employees tablosuna örnek kayıtlar ekleme
INSERT INTO employee (name, surname, position, salary, department_id, create_date) VALUES ('Ali', 'Ak', 'Müdür', 5000.0, 1, CURRENT_TIMESTAMP);
INSERT INTO employee (name, surname, position, salary, department_id, create_date) VALUES ('Veli', 'Pak', 'Bilişim Uzmanı', 4000.0, 3, CURRENT_TIMESTAMP);
INSERT INTO employee (name, surname, position, salary, department_id, create_date) VALUES ('Mehmet', 'Tak', 'Muhasebesi', 3500.0, 2, CURRENT_TIMESTAMP);