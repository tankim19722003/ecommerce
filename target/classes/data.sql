INSERT INTO categories (name, created_at, description)
VALUES
('Electronics', '2024-02-18', 'All kinds of electronic devices'),
('Fashion', '2024-02-18', 'Clothing, shoes, and accessories'),
('Home & Kitchen', '2024-02-18', 'Appliances, furniture, and decor'),
('Books', '2024-02-18', 'Novels, textbooks, and magazines'),
('Sports', '2024-02-18', 'Sporting goods and fitness equipment');

INSERT INTO roles (name)
VALUES
('admin'),
('user'),
('shop');

INSERT INTO users (fullname, account, password, address, email, gender, birth_date, avatar, phone_number)
VALUES
('John Doe', 'johndoe', 'hashed_password_1', '123 Main St', 'johndoe@example.com', TRUE, '1990-05-15', 'avatar1.png', '0123456789'),
('Alice Smith', 'alicesmith', 'hashed_password_2', '456 Elm St', 'alicesmith@example.com', FALSE, '1995-08-22', 'avatar2.png', '0987654321'),
('Bob Johnson', 'bobjohnson', 'hashed_password_3', '789 Oak St', 'bobjohnson@example.com', TRUE, '1988-11-10', 'avatar3.png', '0345678912'),
('Emily Davis', 'emilydavis', 'hashed_password_4', '567 Pine St', 'emilydavis@example.com', FALSE, '1992-02-28', 'avatar4.png', '0765432109');

-- Assign roles to users
INSERT INTO user_roles (user_id, role_id)
VALUES
((SELECT id FROM users WHERE account = 'johndoe'), (SELECT id FROM roles WHERE name = 'admin')),
((SELECT id FROM users WHERE account = 'alicesmith'), (SELECT id FROM roles WHERE name = 'user')),
((SELECT id FROM users WHERE account = 'bobjohnson'), (SELECT id FROM roles WHERE name = 'shop')),
((SELECT id FROM users WHERE account = 'emilydavis'), (SELECT id FROM roles WHERE name = 'user'));

INSERT INTO shops (name, description, created_at, logo, address, phone_number, status, user_id)
VALUES
('Tech Haven', 'A store for all tech gadgets', '2024-02-18', 'tech_haven_logo.png', '123 Tech Street', '0123456789', 'active',
(SELECT id FROM users WHERE account = 'bobjohnson'));
