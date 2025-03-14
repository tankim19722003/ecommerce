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
('Emily Davis', 'tankim19723', '$2a$10$J.CXFDd2CAMjyysovBHXcufdLnNAPxURM5si7phOYqNAgdR2vZbRW', 'tankim1972', FALSE, '1992-02-28', 'avatar4.png', '0548868986'),
('Emily Davis', 'admin', '$2a$10$J.CXFDd2CAMjyysovBHXcufdLnNAPxURM5si7phOYqNAgdR2vZbRW', 'tanadmin123', FALSE, '1992-02-28', 'avatar4.png', '0548868987');;

-- Assign roles to users
INSERT INTO user_roles (user_id, role_id)
VALUES
((SELECT id FROM users WHERE account = 'johndoe'), (SELECT id FROM roles WHERE name = 'admin')),
((SELECT id FROM users WHERE account = 'alicesmith'), (SELECT id FROM roles WHERE name = 'user')),
((SELECT id FROM users WHERE account = 'bobjohnson'), (SELECT id FROM roles WHERE name = 'shop')),
((SELECT id FROM users WHERE account = 'emilydavis'), (SELECT id FROM roles WHERE name = 'user')),
((SELECT id FROM users WHERE account = 'tankim19723'), (SELECT id FROM roles WHERE name = 'user')),
((SELECT id FROM users WHERE account = 'admin'), (SELECT id FROM roles WHERE name = 'admin')),
((SELECT id FROM users WHERE account = 'tankim19723'), (SELECT id FROM roles WHERE name = 'shop')),
((SELECT id FROM users WHERE account = 'admin'), (SELECT id FROM roles WHERE name = 'shop'));


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


--INSERT INTO sub_categories (id, name, created_at, description) VALUES
--(1,'Electronics', '2024-03-03', 'Devices, gadgets, and accessories'),
--(2, 'Fashion', '2024-03-03', 'Clothing, footwear, and accessories'),
--(3, 'Home & Kitchen', '2024-03-03', 'Furniture, appliances, and kitchenware'),
--(4, 'Beauty & Personal Care', '2024-03-03', 'Cosmetics, skincare, and grooming products'),
--(5, 'Sports & Outdoors', '2024-03-03', 'Fitness, outdoor gear, and sportswear'),
--(6,'Books', '2024-03-03', 'Fiction, non-fiction, and educational materials'),
--(7, 'Toys & Games', '2024-03-03', 'Children’s toys, puzzles, and board games'),
--(8, 'Automotive', '2024-03-03', 'Car accessories, tools, and spare parts'),
--(9, 'Health & Wellness', '2024-03-03', 'Medical supplies, supplements, and fitness equipment'),
--(10,'Groceries', '2024-03-03', 'Food, beverages, and household essentials');

INSERT INTO categories (created_at, updated_at, id, description, name) VALUES
('2025-03-07', '2025-03-07', 2, 'Devices for office use', 'Office Equipment'),
('2025-03-07', '2025-03-07', 3, 'Mobile and communication devices', 'Smartphones'),
('2025-03-07', '2025-03-07', 4, 'Accessories for computers and laptops', 'Computer Accessories'),
('2025-03-07', '2025-03-07', 5, 'Home and entertainment electronics', 'Home Electronics'),
('2025-03-07', '2025-03-07', 6, 'Gaming consoles and accessories', 'Gaming'),
('2025-03-07', '2025-03-07', 7, 'Networking devices such as routers', 'Networking'),
('2025-03-07', '2025-03-07', 8, 'Storage devices like HDDs and SSDs', 'Storage'),
('2025-03-07', '2025-03-07', 9, 'Wearable technology and smart gadgets', 'Wearables'),
('2025-03-07', '2025-03-07', 10, 'Computer software and applications', 'Software'),
('2025-03-07', '2025-03-07', 11, 'Peripherals like printers and scanners', 'Peripherals');


INSERT INTO attributes (id, name) VALUES
(1, 'Color'),
(2, 'Size'),
(3, 'Material'),
(4, 'Brand'),
(5, 'Weight'),
(6, 'Dimensions'),
(7, 'Battery Life'),
(8, 'Warranty'),
(9, 'Compatibility'),
(10, 'Capacity');

--INSERT INTO category_attributes (id, attribute_id, category_id) VALUES
--(1, 1, 1), (2, 2, 1), (3, 3, 1), (4, 4, 1), (5, 5, 1),
--(6, 1, 2), (7, 2, 2), (8, 3, 2), (9, 4, 2), (10, 5, 2),
--(11, 1, 3), (12, 6, 3), (13, 7, 3), (14, 8, 3), (15, 9, 3),
--(16, 1, 4), (17, 3, 4), (18, 5, 4), (19, 7, 4), (20, 10, 4),
--(21, 2, 5), (22, 4, 5), (23, 6, 5), (24, 8, 5), (25, 9, 5),
--(26, 1, 6), (27, 2, 6), (28, 3, 6), (29, 4, 6), (30, 5, 6),
--(31, 6, 7), (32, 7, 7), (33, 8, 7), (34, 9, 7), (35, 10, 7),
--(36, 1, 8), (37, 3, 8), (38, 5, 8), (39, 7, 8), (40, 10, 8);



INSERT INTO shops
    (shop_name, description, created_at, updated_at, logo, village_id, specific_address, phone_number, user_id, email)
VALUES
    ('Tech Store', 'A store specializing in tech gadgets', '2024-03-04', '2024-03-04', 'tech_store_logo.png', 1, '123 Tech Street', '0123456789', 5, 'tankim1972@gmail.com');



INSERT INTO sub_categories (created_at, updated_at, category_id, id, description, name) VALUES
-- Office Equipment (category_id = 2)
('2025-03-07', '2025-03-07', 2, 1, 'Chairs for office use', 'Office Chairs'),
('2025-03-07', '2025-03-07', 2, 2, 'Desks and tables for office work', 'Office Desks'),
('2025-03-07', '2025-03-07', 2, 3, 'Printers and scanners', 'Printers & Scanners'),

-- Smartphones (category_id = 3)
('2025-03-07', '2025-03-07', 3, 4, 'Android smartphones', 'Android Phones'),
('2025-03-07', '2025-03-07', 3, 5, 'Apple iPhones', 'iPhones'),
('2025-03-07', '2025-03-07', 3, 6, 'Phone chargers and cables', 'Phone Accessories'),

-- Computer Accessories (category_id = 4)
('2025-03-07', '2025-03-07', 4, 7, 'Monitors and screens', 'Monitors'),
('2025-03-07', '2025-03-07', 4, 8, 'Computer keyboards', 'Keyboards'),
('2025-03-07', '2025-03-07', 4, 9, 'Computer mice and trackpads', 'Mice'),

-- Home Electronics (category_id = 5)
('2025-03-07', '2025-03-07', 5, 10, 'Smart televisions', 'Smart TVs'),
('2025-03-07', '2025-03-07', 5, 11, 'Speakers and home audio systems', 'Speakers'),
('2025-03-07', '2025-03-07', 5, 12, 'Streaming devices like Chromecast', 'Streaming Devices'),

-- Gaming (category_id = 6)
('2025-03-07', '2025-03-07', 6, 13, 'Gaming consoles like PlayStation', 'Gaming Consoles'),
('2025-03-07', '2025-03-07', 6, 14, 'Gaming keyboards and mice', 'Gaming Accessories'),
('2025-03-07', '2025-03-07', 6, 15, 'Virtual reality (VR) headsets', 'VR Headsets'),

-- Networking (category_id = 7)
('2025-03-07', '2025-03-07', 7, 16, 'Wi-Fi routers and modems', 'Wi-Fi Routers'),
('2025-03-07', '2025-03-07', 7, 17, 'Network switches and hubs', 'Network Switches'),
('2025-03-07', '2025-03-07', 7, 18, 'Ethernet cables and adapters', 'Networking Cables'),

-- Storage (category_id = 8)
('2025-03-07', '2025-03-07', 8, 19, 'External hard drives and SSDs', 'External HDDs'),
('2025-03-07', '2025-03-07', 8, 20, 'USB flash drives and memory cards', 'USB Drives'),
('2025-03-07', '2025-03-07', 8, 21, 'Cloud storage services', 'Cloud Storage'),

-- Wearables (category_id = 9)
('2025-03-07', '2025-03-07', 9, 22, 'Smartwatches and fitness trackers', 'Smartwatches'),
('2025-03-07', '2025-03-07', 9, 23, 'Wireless earbuds and headsets', 'Wireless Earbuds'),
('2025-03-07', '2025-03-07', 9, 24, 'VR and AR headsets', 'VR & AR Devices'),

-- Software (category_id = 10)
('2025-03-07', '2025-03-07', 10, 25, 'Operating systems like Windows and macOS', 'Operating Systems'),
('2025-03-07', '2025-03-07', 10, 26, 'Antivirus and security software', 'Security Software'),
('2025-03-07', '2025-03-07', 10, 27, 'Productivity software like Microsoft Office', 'Office Software');

INSERT INTO sub_category_attributes (id, attribute_id, sub_category_id)
VALUES
    (1, 1, 1),  (2, 1, 2),  (3, 1, 3),  (4, 2, 4),  (5, 2, 5),
    (6, 3, 6),  (7, 3, 7),  (8, 4, 8),  (9, 4, 9),  (10, 5, 10),
    (11, 6, 1),  (12, 6, 2),  (13, 7, 3),  (14, 7, 4),  (15, 8, 5),
    (16, 8, 6),  (17, 9, 7),  (18, 9, 8),  (19, 10, 9), (20, 10, 10);


INSERT INTO products (shop_id, sub_category_id, rating, total_sold, created_at, updated_at, description, name, thumbnail)
VALUES
    (1, 1, 4, 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Fast performance with long battery life', 'Samsung Galaxy S23', 'samsung_galaxy_s23.png'),
    (1, 2, 5, 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Latest Apple smartphone with advanced camera', 'iPhone 14 Pro Max', 'iphone_14_pro_max.png'),
    (1, 3, 3, 230, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Affordable smartphone with great features', 'Xiaomi Redmi Note 12', 'redmi_note_12.png'),
    (1, 4, 2, 90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Stylish and lightweight laptop', 'MacBook Air M2', 'macbook_air_m2.png'),
    (1, 5, 4, 150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Powerful gaming laptop', 'Asus ROG Strix G15', 'asus_rog_strix_g15.png'),
    (1, 6, 5, 300, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ultra HD smart TV with vibrant colors', 'Samsung 55-inch 4K Smart TV', 'samsung_55_4k_tv.png'),
    (1, 7, 3, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Affordable wireless earbuds with long battery life', 'JBL Tune 130NC TWS', 'jbl_tune_130nc.png'),
    (1, 8, 4, 75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Premium wireless noise-canceling headphones', 'Sony WH-1000XM5', 'sony_wh_1000xm5.png'),
    (1, 9, 5, 200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Compact and feature-rich smartwatch', 'Apple Watch Series 9', 'apple_watch_series_9.png'),
    (1, 10, 1, 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Basic fitness tracker with step counter', 'Xiaomi Mi Band 7', 'mi_band_7.png'),
    (1, 1, 3, 250, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Professional DSLR camera for photography', 'Canon EOS 90D', 'canon_eos_90d.png'),
    (1, 2, 5, 400, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Portable and lightweight tablet', 'Samsung Galaxy Tab S8', 'samsung_tab_s8.png'),
    (1, 3, 2, 50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Affordable gaming console with HD support', 'Nintendo Switch OLED', 'nintendo_switch_oled.png'),
    (1, 4, 4, 130, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'High-quality mechanical keyboard', 'Logitech G Pro X', 'logitech_g_pro_x.png'),
    (1, 5, 3, 90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ergonomic wireless mouse', 'Razer DeathAdder V2', 'razer_deathadder_v2.png'),
    (1, 6, 5, 180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Smart vacuum cleaner for home automation', 'iRobot Roomba i7', 'irobot_roomba_i7.png'),
    (1, 7, 1, 25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Powerful and stylish blender', 'Ninja Professional Blender', 'ninja_blender.png'),
    (1, 8, 2, 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Efficient and quiet washing machine', 'LG Front Load Washer', 'lg_washer.png'),
    (1, 9, 4, 145, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Smart air purifier with HEPA filter', 'Dyson Pure Cool Air Purifier', 'dyson_pure_cool.png'),
    (1, 10, 3, 110, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Portable and powerful coffee maker', 'Nespresso VertuoPlus', 'nespresso_vertuo.png'),
    (1, 1, 5, 350, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Best-selling wireless speaker with deep bass', 'Bose SoundLink Revolve+', 'bose_soundlink_revolve.png'),
    (1, 2, 2, 45, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Affordable wired gaming headset', 'HyperX Cloud Stinger', 'hyperx_cloud_stinger.png'),
    (1, 3, 4, 190, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Premium smart home security camera', 'Ring Video Doorbell 4', 'ring_video_doorbell.png'),
    (1, 4, 3, 70, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Stylish and comfortable smartwatch', 'Garmin Forerunner 955', 'garmin_forerunner_955.png'),
    (1, 5, 5, 220, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ultra-fast Wi-Fi 6 router', 'TP-Link Archer AX6000', 'tplink_archer_ax6000.png'),
    (1, 6, 4, 175, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'High-performance external SSD', 'Samsung T7 Shield 1TB', 'samsung_t7_shield.png'),
    (1, 7, 2, 55, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Stylish and durable backpack for travel', 'Samsonite Tectonic Lifestyle Backpack', 'samsonite_backpack.png'),
    (1, 8, 1, 35, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Comfortable and lightweight sneakers', 'Nike Air Zoom Pegasus 39', 'nike_pegasus_39.png'),
    (1, 9, 3, 125, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Elegant and minimalist wristwatch', 'Casio Edifice Chronograph', 'casio_edifice.png'),
    (1, 10, 5, 280, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Luxury perfume with floral scent', 'Chanel Coco Mademoiselle', 'chanel_coco_mademoiselle.png');

INSERT INTO product_attribute_value (id, product_id, sub_category_attribute_id, value) VALUES
(1, 1, 1, 'Black'),
(2, 1, 2, '6.1 inches'),
(3, 1, 3, 'Aluminum & Glass'),
(4, 1, 4, 'Samsung'),
(5, 1, 5, '168g'),
(31, 1, 2, '4.6 inches'),
(6, 2, 1, 'Silver'),
(7, 2, 2, '6.7 inches'),
(8, 2, 3, 'Stainless Steel'),
(9, 2, 4, 'Apple'),
(10, 2, 5, '206g'),
(11, 3, 1, 'Blue'),
(12, 3, 2, '6.5 inches'),
(13, 3, 3, 'Plastic & Glass'),
(14, 3, 4, 'Xiaomi'),
(15, 3, 5, '192g'),
(16, 4, 1, 'Gray'),
(17, 4, 2, '13.6 inches'),
(18, 4, 3, 'Aluminum'),
(19, 4, 4, 'Apple'),
(20, 4, 5, '1.24kg'),
(21, 5, 1, 'Black'),
(22, 5, 2, '15.6 inches'),
(23, 5, 3, 'Plastic'),
(24, 5, 4, 'Asus'),
(25, 5, 5, '2.3kg'),
(26, 6, 1, 'Black'),
(27, 6, 2, '55 inches'),
(28, 6, 3, 'LED Display'),
(29, 6, 4, 'Samsung'),
(30, 6, 5, '15kg');

INSERT INTO code_purposes (id, name, created_at, updated_at) VALUES
(1, 'Email confirmation', '2025-03-13', '2025-03-13'),
(2, 'User confirmation', '2025-03-13', '2025-03-13'),
 (3, 'Discount Code', '2025-03-13', '2025-03-13');

INSERT INTO user_codes (code, date_start, date_end, user_id, active, code_purpose_id)
VALUES (123456, NOW(), NOW() + INTERVAL '2 minutes', 2, FALSE, 1);


