delete from members;
delete from books;
delete from orders;
INSERT INTO Members(memberId, name, surname,address) VALUES
    ( '1','Ediz', 'Orhan','kagithane'),
    ( '2','Mert', 'Yildiz','besiktas'),
    ( '3','Batuhan', 'Yilmaz','kadikoy'),
    ( '4','Ceren','Saygin','sariyer');
INSERT INTO Books(bookId, book_name, author,price,stock_count) VALUES
                                                         ( '100','1984', 'George Orwell',8,124),
                                                         ( '101','Fahrenheit 451', 'Ray Bradbury',15,30),
                                                         ( '102','The Shining', 'Stephen King',12,63),
                                                         ( '103','The Hobbit','J.R.R Tolkien',20,121);
