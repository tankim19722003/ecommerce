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

INSERT INTO users (fullname, account, password, email, gender, birth_date, avatar, phone_number)
VALUES
('John Doe', 'johndoe', 'hashed_password_1', 'johndoe@example.com', TRUE, '1990-05-15', 'avatar1.png', '0123456789'),
('Alice Smith', 'alicesmith', 'hashed_password_2', 'alicesmith@example.com', FALSE, '1995-08-22', 'avatar2.png', '0987654321'),
('Bob Johnson', 'bobjohnson', 'hashed_password_3', 'bobjohnson@example.com', TRUE, '1988-11-10', 'avatar3.png', '0345678912'),
('Emily Davis', 'emilydavis', 'hashed_password_4', 'emilydavis@example.com', FALSE, '1992-02-28', 'avatar4.png', '0765432109'),
('Emily Davis', 'tankim19723', '$2a$10$J.CXFDd2CAMjyysovBHXcufdLnNAPxURM5si7phOYqNAgdR2vZbRW', 'tankim1972', FALSE, '1992-02-28', 'avatar4.png', '0548868986');;

-- Assign roles to users
INSERT INTO user_roles (user_id, role_id)
VALUES
((SELECT id FROM users WHERE account = 'johndoe'), (SELECT id FROM roles WHERE name = 'admin')),
((SELECT id FROM users WHERE account = 'alicesmith'), (SELECT id FROM roles WHERE name = 'user')),
((SELECT id FROM users WHERE account = 'bobjohnson'), (SELECT id FROM roles WHERE name = 'shop')),
((SELECT id FROM users WHERE account = 'emilydavis'), (SELECT id FROM roles WHERE name = 'user')),
((SELECT id FROM users WHERE account = 'tankim19723'), (SELECT id FROM roles WHERE name = 'user'));

INSERT INTO shops (name, description, created_at, logo, address, phone_number, status, user_id)
VALUES
('Tech Haven', 'A store for all tech gadgets', '2024-02-18', 'tech_haven_logo.png', '123 Tech Street', '0123456789', 'active',
(SELECT id FROM users WHERE account = 'bobjohnson'));

INSERT INTO provinces (id, name) VALUES
(1, 'An Giang'),
(2, 'Bà Rịa - Vũng Tàu'),
(3, 'Bắc Giang'),
(4, 'Bắc Kạn'),
(5, 'Bạc Liêu'),
(6, 'Bắc Ninh'),
(7, 'Bến Tre'),
(8, 'Bình Định'),
(9, 'Bình Dương'),
(10, 'Bình Phước'),
(11, 'Bình Thuận'),
(12, 'Cà Mau'),
(13, 'Cần Thơ'),
(14, 'Cao Bằng'),
(15, 'Đà Nẵng'),
(16, 'Đắk Lắk'),
(17, 'Đắk Nông'),
(18, 'Điện Biên'),
(19, 'Đồng Nai'),
(20, 'Đồng Tháp'),
(21, 'Gia Lai'),
(22, 'Hà Giang'),
(23, 'Hà Nam'),
(24, 'Hà Nội'),
(25, 'Hà Tĩnh'),
(26, 'Hải Dương'),
(27, 'Hải Phòng'),
(28, 'Hậu Giang'),
(29, 'Hòa Bình'),
(30, 'Hưng Yên'),
(31, 'Khánh Hòa'),
(32, 'Kiên Giang'),
(33, 'Kon Tum'),
(34, 'Lai Châu'),
(35, 'Lâm Đồng'),
(36, 'Lạng Sơn'),
(37, 'Lào Cai'),
(38, 'Long An'),
(39, 'Nam Định'),
(40, 'Nghệ An'),
(41, 'Ninh Bình'),
(42, 'Ninh Thuận'),
(43, 'Phú Thọ'),
(44, 'Phú Yên'),
(45, 'Quảng Bình'),
(46, 'Quảng Nam'),
(47, 'Quảng Ngãi'),
(48, 'Quảng Ninh'),
(49, 'Quảng Trị'),
(50, 'Sóc Trăng'),
(51, 'Sơn La'),
(52, 'Tây Ninh'),
(53, 'Thái Bình'),
(54, 'Thái Nguyên'),
(55, 'Thanh Hóa'),
(56, 'Thừa Thiên Huế'),
(57, 'Tiền Giang'),
(58, 'TP. Hồ Chí Minh'),
(59, 'Trà Vinh'),
(60, 'Tuyên Quang'),
(61, 'Vĩnh Long'),
(62, 'Vĩnh Phúc'),
(63, 'Yên Bái');

INSERT INTO districts (id, name, province_id) VALUES
-- An Giang
(1, 'Thành phố Long Xuyên', 1),
(2, 'Thành phố Châu Đốc', 1),
(3, 'An Phú', 1),
(4, 'Châu Phú', 1),
(5, 'Châu Thành', 1),
(6, 'Chợ Mới', 1),
(7, 'Phú Tân', 1),
(8, 'Thoại Sơn', 1),
(9, 'Tịnh Biên', 1),
(10, 'Tri Tôn', 1),

-- Bà Rịa - Vũng Tàu
(11, 'Thành phố Vũng Tàu', 2),
(12, 'Thành phố Bà Rịa', 2),
(13, 'Châu Đức', 2),
(14, 'Côn Đảo', 2),
(15, 'Đất Đỏ', 2),
(16, 'Long Điền', 2),
(17, 'Tân Thành', 2),
(18, 'Xuyên Mộc', 2),

-- Bắc Giang
(19, 'Thành phố Bắc Giang', 3),
(20, 'Hiệp Hòa', 3),
(21, 'Lạng Giang', 3),
(22, 'Lục Nam', 3),
(23, 'Lục Ngạn', 3),
(24, 'Sơn Động', 3),
(25, 'Tân Yên', 3),
(26, 'Việt Yên', 3),
(27, 'Yên Dũng', 3),
(28, 'Yên Thế', 3),

-- Hà Nội
(29, 'Ba Đình', 24),
(30, 'Hoàn Kiếm', 24),
(31, 'Tây Hồ', 24),
(32, 'Long Biên', 24),
(33, 'Cầu Giấy', 24),
(34, 'Đống Đa', 24),
(35, 'Hai Bà Trưng', 24),
(36, 'Hoàng Mai', 24),
(37, 'Thanh Xuân', 24),
(38, 'Sóc Sơn', 24),
(39, 'Đông Anh', 24),
(40, 'Gia Lâm', 24),
(41, 'Nam Từ Liêm', 24),
(42, 'Thanh Trì', 24),
(43, 'Hoài Đức', 24),
(44, 'Quốc Oai', 24),
(45, 'Thanh Oai', 24),
(46, 'Chương Mỹ', 24),
(47, 'Thạch Thất', 24),
(48, 'Ba Vì', 24),
(49, 'Phúc Thọ', 24),
(50, 'Đan Phượng', 24),
(51, 'Mê Linh', 24),
(52, 'Thị xã Sơn Tây', 24),

-- TP. Hồ Chí Minh
(53, '1', 58),
(54, '2', 58),
(55, '3', 58),
(56, '4', 58),
(57, '5', 58),
(58, '6', 58),
(59, '7', 58),
(60, '8', 58),
(61, '9', 58),
(62, '10', 58),
(63, '11', 58),
(64, '12', 58),
(65, 'Bình Tân', 58),
(66, 'Bình Thạnh', 58),
(67, 'Gò Vấp', 58),
(68, 'Phú Nhuận', 58),
(69, 'Tân Bình', 58),
(70, 'Tân Phú', 58),
(71, 'Bình Chánh', 58),
(72, 'Cần Giờ', 58),
(73, 'Củ Chi', 58),
(74, 'Hóc Môn', 58),
(75, 'Nhà Bè', 58),

-- Đà Nẵng
(76, 'Hải Châu', 15),
(77, 'Thanh Khê', 15),
(78, 'Sơn Trà', 15),
(79, 'Ngũ Hành Sơn', 15),
(80, 'Liên Chiểu', 15),
(81, 'Hòa Vang', 15),
(82, 'Hoàng Sa', 15),

(83, 'Bình Thủy', 13),
(84, 'Cái Răng', 13),
(85, 'Ninh Kiều', 13),
(86, 'Ô Môn', 13),
(87, 'Thốt Nốt', 13),
(88, 'Cờ Đỏ', 13),
(89, 'Phong Điền', 13),
(90, 'Thới Lai', 13),
(91, 'Vĩnh Thạnh', 13);

INSERT INTO villages (id, district_id, name) VALUES
-- Xã thuộc Thành phố Long Xuyên
(1, 1, 'Mỹ Bình'),
(2, 1, 'Mỹ Hòa'),
(3, 1, 'Mỹ Phước'),

-- Xã thuộc Thành phố Châu Đốc
(4, 2, 'Vĩnh Mỹ'),
(5, 2, 'Vĩnh Nguơn'),
(6, 2, 'Châu Phú A'),

-- Xã thuộc Châu Phú
(7, 4, 'Bình Mỹ'),
(8, 4, 'Khánh Hòa'),
(9, 4, 'Mỹ Phú'),

-- Xã thuộc Chợ Mới
(10, 6, 'An Thạnh Trung'),
(11, 6, 'Hội An'),
(12, 6, 'Long Giang'),

-- Xã thuộc Thành phố Vũng Tàu
(13, 11, 'Thắng Nhất'),
(14, 11, 'Thắng Tam'),
(15, 11, 'Rạch Dừa'),

-- Xã thuộc Thành phố Bà Rịa
(16, 12, 'Hòa Long'),
(17, 12, 'Phước Hưng'),
(18, 12, 'Tân Hưng'),

-- Xã thuộc Hiệp Hòa
(19, 20, 'Đoan Bái'),
(20, 20, 'Mai Trung'),
(21, 20, 'Thanh Vân'),

-- Xã thuộc Tây Hồ (Hà Nội)
(22, 31, 'Nhật Tân'),
(23, 31, 'Quảng An'),
(24, 31, 'Tứ Liên'),

-- Xã thuộc Hoàn Kiếm (Hà Nội)
(25, 30, 'Chương Dương'),
(26, 30, 'Hàng Bạc'),
(27, 30, 'Hàng Buồm'),

-- Xã thuộc Quận 1 (TP. Hồ Chí Minh)
(28, 53, 'Bến Nghé'),
(29, 53, 'Bến Thành'),
(30, 53, 'Cô Giang'),

-- Xã thuộc Quận 3 (TP. Hồ Chí Minh)
(31, 55, 'Võ Thị Sáu'),
(32, 55, 'Phường 10'),
(33, 55, 'Phường 11'),

-- Xã thuộc Hải Châu (Đà Nẵng)
(34, 76, 'Hòa Cường Bắc'),
(35, 76, 'Hòa Cường Nam'),
(36, 76, 'Hải Châu 1'),

-- Xã thuộc Bình Thủy (Cần Thơ)
(37, 83, 'An Thới'),
(38, 83, 'Bình Thủy'),
(39, 83, 'Long Hòa'),

-- Xã thuộc Cái Răng (Cần Thơ)
(40, 84, 'Hưng Phú'),
(41, 84, 'Lê Bình'),
(42, 84, 'Tân Phú');

