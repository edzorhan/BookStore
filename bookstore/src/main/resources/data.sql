delete from members;
delete from books;
INSERT INTO Members(memberId, name, surname,address) VALUES
    ( '1','sss', 'orhan','kagithane'),
    ( '2','mahmut', 'orhan','kagithane'),
    ( '3','ahmet', 'orhan','kagithane'),
    ( '5','qq', 'orhan','kagithane');
INSERT INTO Books(bookId, book_name, author,price,stock_count) VALUES
                                                         ( '100','1984', 'George Orwell',8,124),
                                                         ( '101','Fahrenheit 451', 'Ray Bradbury',15,30),
                                                         ( '102','The Shining', 'Stephen King',12,63),
                                                         ( '103','The Hobbit','J.R.R Tolkien',20,121);
