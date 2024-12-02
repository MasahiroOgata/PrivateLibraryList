CREATE TABLE library.books (
    id INT AUTO_INCREMENT NOT NULL,
    user_id INT NOT NULL,
    book_title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    purchased_date DATE NOT NULL,
    finished_date DATE,
    series_id INT,
    vol_num INT,
    publisher_id INT NOT NULL,
    media TINYINT NOT NULL,
    is_disposed TINYINT NOT NULL DEFAULT 0,
    create_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)